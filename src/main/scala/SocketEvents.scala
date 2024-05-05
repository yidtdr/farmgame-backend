sealed trait SocketEvent {val data: Any}
case class Connect(data: String) extends SocketEvent
case class Use(data: Tuple3[String, Int, Int]) extends SocketEvent
case class Collect(data: Tuple2[Int, Int]) extends SocketEvent
case class Buy(data: Tuple2[String, Int]) extends SocketEvent
case class Place(data: Tuple3[String, Int, Int]) extends SocketEvent
case class Unknown(data: String) extends SocketEvent

def stringToEvent(message: String): SocketEvent =
  message match {
    case (s"connect/username&=${text}") => Connect(text)
    case (s"use/${name}/${x}/${y}") => try Use(Tuple3(name, x.toInt, y.toInt)) catch _ => Unknown(null)
    case (s"collect/${x}/${y}") => try Collect(Tuple2(x.toInt, y.toInt)) catch _ => Unknown(null)
    case (s"buy/${name}/${amount}") => try Buy(Tuple2(name, amount.toInt)) catch _ => Unknown(null)
    case (s"place/${name}/${x}/${y}") => try Place(Tuple3(name, x.toInt, y.toInt)) catch _ => Unknown(null)
    case _ => Unknown(null);
}