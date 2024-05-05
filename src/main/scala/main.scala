import zio._

import zio.http.ChannelEvent.{ExceptionCaught, Read, UserEvent, UserEventTriggered}
import zio.http._
import zio.http.codec.PathCodec.string
import scala.compiletime.ops.boolean
import zio.schema.validation.Bool
import zio.http.Status.Ok
import zio.http.Header.Connection.Close

object WebApp extends ZIOAppDefault {
    val socketApp: WebSocketApp[Any] =
    Handler.webSocket { channel =>  
      channel.receiveAll {
        case Read(WebSocketFrame.Text(string))                =>
          {
            App.matchEvent(channel, stringToEvent(string))
            channel.send(Read(WebSocketFrame.Text(App.getState(channel)))) *>
            channel.send(Read(WebSocketFrame.Text(App.getLastOperation(channel))))
          }

        case ExceptionCaught(cause)                           =>
          Console.printLine(s"Channel error!: ${cause.getMessage}")

        case _ =>
          ZIO.unit
      }
    }
    override val run = 
      {
        Server.serve(socketApp.toHttpAppWS).provide(Server.defaultWithPort(8000))
      }
}