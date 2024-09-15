import zio.http._
import zio._
import zio.http.ChannelEvent.{ExceptionCaught, Read, UserEvent, UserEventTriggered}
import DBInterface._
import BinaryDataManager._
import gameClasses._
import zio.http.WebSocketChannel
import gameClasses.Plants
import io.netty.handler.codec.http.websocketx.WebSocketChunkedInput
import SocketEvent._

object App {
    
    var authorizedSockets: scala.collection.mutable.Map[WebSocketChannel, String] = scala.collection.mutable.Map.empty
    var socketToGame: scala.collection.mutable.Map[WebSocketChannel, Game] = scala.collection.mutable.Map.empty
    
    def getOnline() = (for {
      _ <- Console.printLine(s"${authorizedSockets.count((_,_) => true)}?${socketToGame.count((_,_) => true)}")
    } yield ())

    def getState(socket: WebSocketChannel): String = {
      val state = socketToGame(socket).getState()
      state;
    }

    def preSend(socket: WebSocketChannel, data: String): UIO[Unit] = {
      val code = socketToGame(socket).lastOperation
      if ((code == GE.SocketWrongFormat) || (code == GE.SocketUnknown)) {
        CA.pushUnknown(UnknownEventLog.fromUnknown(data, authorizedSockets(socket)))
      }
      ZIO.unit
    }

    def banUser(username: String) = {
      var shouldFind = true;
      authorizedSockets.map((socket, name) => {
        if (name == username)
        {
          socketToGame(socket).onBan();
          authorizedSockets.remove(socket);
          BinaryDataManager.writeGame(socketToGame(socket), username);
          socketToGame.remove(socket);
          shouldFind = false;
        }
      })
      if (shouldFind)
      {
        try
        {
          val game = BinaryDataManager.readGame(username)
          game.CLIENT_INFO.banned = true;
          BinaryDataManager.writeGame(game, username)
        }
        catch
        {
          case e: Exception => 
        }
      }
    }

    def unbanUser(username: String) = {
      try
        {
          val game = BinaryDataManager.readGame(username)
          game.CLIENT_INFO.banned = false;
          BinaryDataManager.writeGame(game, username)
        }
        catch
        {
          case e: Exception => 
        }
    }

    def unsafeDisconnect(socket: WebSocketChannel) = {
      authorizedSockets.remove(socket);
      socketToGame.remove(socket);
    }

    def reportSocketError(socket: WebSocketChannel, data: String) = socketToGame(socket).lastOperation = GE.SocketWrongFormat;

    def disconnected(socket: WebSocketChannel): UIO[Unit] = ZIO.succeed {
      BinaryDataManager.writeGame(socketToGame(socket), authorizedSockets(socket));
      authorizedSockets.remove(socket)
      socketToGame.remove(socket)
    }

    def connected(socket: WebSocketChannel, data: Option[String]) = {
      val username = data.getOrElse(null)
      if (username == null) false
      else
      {
        try
        {
          val tg_id = username.toLong
          authorizedSockets(socket) = username
          val player = DBInterface.playerByName(username)
          if (player == None) {
            socketToGame(socket) = new Game(tg_id)
            socketToGame(socket).firstInit()
            DBInterface.addUser(User(username))
            BinaryDataManager.writeGame(socketToGame(socket), username);
            socketToGame(socket).onConnect()
          }
          else {
            socketToGame(socket) = BinaryDataManager.readGame(username)
            if (!socketToGame(socket).CLIENT_INFO.banned) socketToGame(socket).onConnect()
            else unsafeDisconnect(socket)
        }}
        catch
          case _: Exception => println(username)
      }
    }

    def regenerate(socket: WebSocketChannel) =                            socketToGame(socket).onRegenerate()
    def use(socket: WebSocketChannel, data: Tuple3[String, Int, Int]) =   if (data._1 != null) socketToGame(socket).onUse(data._1, data._2, data._3) else reportSocketError(socket, s"use/${data._1}/${data._2}/${data._3}")
    def collect(socket: WebSocketChannel, data: Tuple2[Int, Int]) =       socketToGame(socket).onCollect(data._1, data._2)
    def buy(socket: WebSocketChannel, data: Tuple2[String, Int]) =        if ((data._1 != null) && (data._2 > 0)) socketToGame(socket).onBuy(data._1, data._2) else reportSocketError(socket, s"buy/${data._1}/${data._2}")
    def place(socket: WebSocketChannel, data: Tuple3[String, Int, Int]) = if ((data._1 != null) && (BuildingManager.buildingExists(data._1))) socketToGame(socket).onPlace(data._1, data._2, data._3) else reportSocketError(socket, s"place/${data._1}/${data._2}/${data._3}")
    def move(socket: WebSocketChannel, data: Tuple4[Int, Int, Int, Int])= socketToGame(socket).onMove(data._1, data._2, data._3, data._4)
    def spinwheel(socket: WebSocketChannel) =                             socketToGame(socket).onSpin()
    def completeOrder(socket: WebSocketChannel, data: Int) =              socketToGame(socket).onCompleteOrder(data)
    def rerollOrder(socket: WebSocketChannel, data: Int) =                socketToGame(socket).onRerollOrder(data)
    def orderOperation(socket: WebSocketChannel, data: Tuple2[String, Int]) = data._1 match
        case "complete" =>                                                completeOrder(socket, data._2)
        case "reroll" =>                                                  rerollOrder(socket, data._2) 
        case _ =>                                                         matchEvent(socket, Unknown(null))
    def upgrade(socket: WebSocketChannel, data: Tuple2[Int, Int]) =       socketToGame(socket).onUpgrade(data._1, data._2)
    def claimDeposit(socket: WebSocketChannel, data: Int) =               socketToGame(socket).onClaimDeposit(data)
    def registerWithdraw(socket: WebSocketChannel, data: Long) =          socketToGame(socket).onRegisterWithdraw(data)
    def purchaseSlot(socket: WebSocketChannel, data: (Int, Int)) =        socketToGame(socket).onPurchaseSlot(data._1, data._2)
    def purchaseDeal(socket: WebSocketChannel, data: String) =            socketToGame(socket).onPurchaseDeal(data)
    def activateBooster(socket: WebSocketChannel, data: Int) =            socketToGame(socket).onActivateBooster(data)
    def upgradeInventory(socket: WebSocketChannel) =                      socketToGame(socket).onInventoryUpgrade()
    def collectBusiness(socket: WebSocketChannel, data: Int) =            socketToGame(socket).onCollectBusiness(data)
    def purchaseBusiness(socket: WebSocketChannel, data: Int) =           socketToGame(socket).onPurchaseBusiness(data)
    def upgradeBusiness(socket: WebSocketChannel, data: Int) =            socketToGame(socket).onUpgradeBusiness(data)
    def activateBusiness(socket: WebSocketChannel, data: (Int, Int)) =    socketToGame(socket).onActivateBusiness(data._1, data._2)
    def businessOperation(socket: WebSocketChannel, data: Tuple3[String, Int, Int]) = data._1 match
        case "collect" =>                                                 collectBusiness(socket, data._2)
        case "activate" =>                                                activateBusiness(socket, (data._2, data._3)) 
        case "upgrade" =>                                                 upgradeBusiness(socket, data._2) 
        case "buy" =>                                                     purchaseBusiness(socket, data._2) 
        case _ =>                                                         matchEvent(socket, Unknown(null))
    def handleUnknown(socket: WebSocketChannel, data: String) =           reportSocketError(socket, data)

    def matchEvent(socket: WebSocketChannel, event: SocketEvent): UIO[Unit] = ZIO.succeed {
        event match
            case Connect(data) =>               connected(socket, data)
            case Regeneration(data) =>          regenerate(socket)
            case Use(data) =>                   use(socket, data)
            case Collect(data) =>               collect(socket, data)
            case Upgrade(data) =>               upgrade(socket, data)
            case Buy(data) =>                   buy(socket, data)
            case Place(data) =>                 place(socket, data)
            case Move(data) =>                  move(socket, data)
            case OrderOperation(data) =>        orderOperation(socket, data)
            case SpinWheel(data) =>             spinwheel(socket)
            case ClaimDeposit(data) =>          claimDeposit(socket, data)
            case RegisterWithdraw(data) =>      registerWithdraw(socket, data)
            case PurchaseSlot(data) =>          purchaseSlot(socket, data)
            case PurchaseDeal(data) =>          purchaseDeal(socket, data)
            case ActivateBooster(data) =>       activateBooster(socket, data)
            case InvUpgrade(data) =>            upgradeInventory(socket)
            case BusinessEV(data) =>            businessOperation(socket, data)
            case Unknown(data) =>               handleUnknown(socket, data)
    }

    def operateThroughPacket(socket: WebSocketChannel, packet: String): UIO[Unit] = for {
      event <- stringToEventZIO(packet)
      _ <- matchEvent(socket, event)
      _ <- preSend(socket, packet)
    } yield ()
}
