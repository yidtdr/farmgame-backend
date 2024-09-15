import Console.{RESET, RED, BOLD, GREEN, WHITE}
import Console._
import zio._
import java.time.Instant
import zio.stream.compression.FlushMode
import scala.collection.mutable.Queue
import gameClasses.BakeryManager.use
import gameClasses.BusinessManager

val SKIP = "\u001b[2J"
val LSTART = s"${RESET}${BOLD}${WHITE}${SKIP}"

case class UnknownEventLog(timestamp: Long, causingString: String, user: String)
object UnknownEventLog
{
	def fromUnknown(data: String, username: String): UnknownEventLog = UnknownEventLog(Instant.now().getEpochSecond(), data, username)
}


sealed trait ConsoleCommands
object ConsoleCommands
{
	case object Stop 												extends ConsoleCommands
	case object Status 											extends ConsoleCommands
	case object Flush 											extends ConsoleCommands
	case class 	Ban(username: String) 			extends ConsoleCommands
	case class 	Unban(username: String) 		extends ConsoleCommands
	case object ReloadDeals 					      extends ConsoleCommands
	case object Unknown											extends ConsoleCommands
}

import ConsoleCommands._

object CA
{

	def stop() 			= println(s"${LSTART}Shutting down...${RESET}")
	def status() 		= BusinessManager.debug()
	def unknown()   = println(s"${LSTART}${RED}Unknown command...${RESET}")
	def setup() 		= println(s"${LSTART}")


	def printUnknown(unknown: UnknownEventLog) = println(s"${RED}User: '${unknown.user}'	|	Message: '${unknown.causingString}'	|	Timestamp: ${unknown.timestamp}${RESET}")

	val unknownEventsQueue: Queue[UnknownEventLog] = Queue.empty

	  /**
   * Will push unknown into queue for printing in console.
   * Capacity of queue is only 100 unknowns so old elements will get deleted
   *
   * @param unknown
   *   Unknown event wrapped into `UnknownEventLog`
   */
	def pushUnknown(unknown: UnknownEventLog) = unknownEventsQueue.enqueue(unknown)

	def flushUnknownQueue() = {setup(); unknownEventsQueue.dequeueAll(_ => true).foreach((el) => printUnknown(el))}

	def ban(username: String) =
	{
		setup()
		println(s"Banning user: '${username}'")
		try
			App.banUser(username);
			println(s"${GREEN}Banned successfully!")
		catch
			case e: Exception=> println(s"${RED}Problem occured while banning user '${username}'")
	}

	def unban(username: String) =
	{
		setup()
		println(s"Unbanning user: '${username}'")
		try
			App.unbanUser(username);
			println(s"${GREEN}Unbanned successfully!")
		catch
			case e: Exception=> println(s"${RED}Problem occured while unbanning user '${username}'")
	}

	def reloadDeals() = {
		println(s"${LSTART}${CYAN}Reloading Deals...${RESET}")
		import gameClasses.Shop
		Shop.reloadDeals()
		println(s"${LSTART}${CYAN}Done...${RESET}")
	}

	def matchCommand(input: String): ConsoleCommands = input match {
		case "stop"									=> Stop
		case "status" 							=> Status
		case "flush"								=> Flush
		case s"ban ${username}"			=> Ban(username)
		case s"unban ${username}"		=> Unban(username)
		case s"reload deals"				=> ReloadDeals
		case _											=> Unknown
	}

	def handleCommand(input: String): UIO[ConsoleCommands] = {
		val command = matchCommand(input)
		command match {
			case Stop 									=> stop()
			case Status 								=> status()
			case Flush 									=> flushUnknownQueue()
			case Unknown 								=> unknown()
			case Ban(username) 					=> ban(username)
			case Unban(username) 				=> unban(username)
			case ReloadDeals						=> reloadDeals()
		}

		ZIO.succeed { command }
	}

	val commandListener: ZIO[Any, Throwable, ConsoleCommands] = 
	{
    ZIO
      .attempt(scala.io.StdIn.readLine())
      .flatMap(handleCommand(_))
      .repeatWhile(_ != Stop)
  }
}