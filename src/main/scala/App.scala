import zio.http._
import zio._
import DBInterface._
import BinaryDataManager._
import gameClasses._
import gameClasses.Plants

object App {
    
    var authorizedSockets: scala.collection.mutable.Map[WebSocketChannel, String] = scala.collection.mutable.Map.empty
    var socketToGame: scala.collection.mutable.Map[WebSocketChannel, Game] = scala.collection.mutable.Map.empty
    
    def getState(socket: WebSocketChannel): String = 
    {
      socketToGame(socket).getState()
    }

    def getLastOperation(socket: WebSocketChannel): String =
    {
      socketToGame(socket).getLastOperation()
    }

    def reportSocketError(socket: WebSocketChannel) =
    {
      socketToGame(socket).lastOperation = GE.SocketWrongFormat;
    }

    def connected(socket: WebSocketChannel, username: String) =
    {
      authorizedSockets(socket) = username;
      val player = DBInterface.playerByName(username);
      if (player == None)
      {
        socketToGame(socket) = new Game
        DBInterface.addUser(User(username))
        BinaryDataManager.writeGame(socketToGame(socket), username);
      }
      else
      {
        socketToGame(socket) = BinaryDataManager.readGame(username)
      }
    }
    
    def use(socket: WebSocketChannel, data: Tuple3[String, Int, Int]) =
    {
      val item = data._1;
      val x = data._2;
      val y = data._3;
      if (item != null)
      {
        socketToGame(socket).onUse(item, x, y)
      }
      else
      {
        reportSocketError(socket)
      }
    }

    def collect(socket: WebSocketChannel, data: Tuple2[Int, Int]) =
    {
      val x = data._1;
      val y = data._2;
      socketToGame(socket).onCollect(x, y)
    }
    
    def buy(socket: WebSocketChannel, data: Tuple2[String, Int]) =
    {
      val item = data._1;
      val amount = data._2;
      if ((item != null) && (amount > 0))
      {
        socketToGame(socket).onBuy(item, amount)
      }
      else
      {
        reportSocketError(socket);
      }
    }

    def place(socket: WebSocketChannel, data: Tuple3[String, Int, Int]) =
    {
      val item = data._1;
      println(item)
      val x = data._2;
      val y = data._3;
      if ((item != null) && (BuildingManager.buildingExists(item)))
      {
        socketToGame(socket).onPlace(item, x, y)
      }
      else
      {
        reportSocketError(socket);
      }
    }

    def matchEvent(socket: WebSocketChannel, event: SocketEvent): Boolean =
    {
        event match
            case Connect(data)                  => 
              connected(socket, data)
            case Use(data)                   =>
              use(socket, data)
            case Collect(data)                  =>
              collect(socket, data)
            case Buy(data)                      => 
              buy(socket, data)
            case Place(data)                    =>
              place(socket, data)
            case Unknown(data)                  =>
              {
                socketToGame(socket).lastOperation = GE.SocketUnknown;
                println("[ERROR] Unknown socket event generated")
              }
        
        BinaryDataManager.writeGame(socketToGame(socket), authorizedSockets(socket));
        true
    }
}
