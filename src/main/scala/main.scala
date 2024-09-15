import zio._
import zio.http._
import zio.http.ChannelEvent.UserEvent._
import zio.http.ChannelEvent.UserEventTriggered._
import zio.http.ChannelEvent.{ExceptionCaught, Read, UserEvent, UserEventTriggered}

object WebApp extends ZIOAppDefault {
  val socketApp: WebSocketApp[Any] =
    Handler.webSocket { channel                                             =>
      channel.receiveAll {
        case Read(WebSocketFrame.Text(string))                              =>
          App.operateThroughPacket(channel, string)                         *>
            channel.send(Read(WebSocketFrame.Text(App.getState(channel))))

        case UserEventTriggered(event)                                      => 
            event match
              case HandshakeTimeout                                         =>
                channel.shutdown          
              case HandshakeComplete                                        =>
                ZIO.unit

        case ChannelEvent.unregistered                                      =>
          App.disconnected(channel).ensuring(channel.shutdown)

        case ExceptionCaught(cause)                                         =>
          Console.printLine(s"Channel error!: ${cause.getMessage}")

        case _ =>
          ZIO.unit
      }
    }

  override val run =
    CA.setup()
    for {
      server <- Server.serve(socketApp.toHttpAppWS).provide(Server.defaultWithPort(8000)).fork
      _ <- CA.commandListener *> server.interrupt
    } yield ()
}
