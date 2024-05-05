package gameClasses

import zio._
import zio.json._
import java.nio.file.{Files, Path}
import java.time.{Instant, Duration}
import _root_.`<empty>`.ENVars
import _root_.`<empty>`.GE
import java.io.Serial
import java.util.Queue
import gameClasses.BakeryManager.getBakery
import scala.collection.mutable


case class WorkTypeINFO(timeToFinish: Int, items: Map[String, Int], products: Map[String, Int])
case class BakeryINFO(name: String, price: Int, maxSlots: Int, workTypes: Map[String, WorkTypeINFO])

object WorkTypeINFO
{
  implicit val decoder: JsonDecoder[WorkTypeINFO] = DeriveJsonDecoder.gen[WorkTypeINFO]
}

object BakeryINFO
{
  implicit val decoder: JsonDecoder[BakeryINFO] = DeriveJsonDecoder.gen[BakeryINFO]
}

class Slot(workType: WorkTypeINFO, workTypeName: String) extends Serializable
{
	val name = workTypeName;
	val workStartTimeStamp = Instant.now();
	val workEndTimeStamp = workStartTimeStamp.plusSeconds(workType.timeToFinish)
}

class Bakery(name: String) extends Buildable
{
	val bakeryType = name;
	//var slots: Array[Slot] = Array.fill(BakeryManager.getMaxSlots(bakeryType))(new Slot(null, null))
	var slots: mutable.Queue[Slot] = mutable.Queue[Slot]()
	var lastEmptySlot: Int = 0;
	def collect(): Int = 
	{
		lastEmptySlot-=1
		GE.OK
	}
	def use(data: String): Int =
	{
		slots.enqueue(new Slot(BakeryManager.getBakery(this.getName()).workTypes(data), data))
		lastEmptySlot+=1
		GE.OK
	}
	def getName(): String =
	{
		bakeryType
	}
}

object BakeryManager
{
	var bakeriesINFO: Map[String, BakeryINFO] = Map.empty

	for (name <- ENVars.ASSETS.BAKERIES.keySet)
	{
    	bakeriesINFO = bakeriesINFO.updated(name, nameToLoadBakery(name))
    }

	def nameToLoadBakery(name: String): BakeryINFO =
		loadBakery(ENVars.ASSETS.BAKERIES(name))

	def loadBakery(path: Path): BakeryINFO =
	{
		val bytes = Files.readAllBytes(path)
		val bakery = (new String(bytes, "UTF-8")).fromJson[BakeryINFO].left.map(new Exception(_))
		bakery.getOrElse(null)
	}

	def getPrice(building: String): Int =
	{
		bakeriesINFO(building).price
	}

	def getMaxSlots(building: String): Int =
	{
		bakeriesINFO(building).maxSlots
	}

	def getBakery(name: String): BakeryINFO =
	{
		bakeriesINFO(name)
	}

	def use(bakery: Bakery, data: String, player: Player): Int =
	{
		val bakeryinfo = getBakery(bakery.getName())
		val worktypeinfo = bakeryinfo.workTypes.get(data).getOrElse(null)

		if (worktypeinfo != null)
		{
		var validated = true;
		for ((item, amount) <- worktypeinfo.items){
			if (player.SeedsInventory.getAmount(item).getOrElse(0) < amount)
			{
				validated = false
			}
		}

		if (bakery.lastEmptySlot >= bakeryinfo.maxSlots)
		{
			validated = false
		}

		if (validated)
		{
			for ((item, amount) <- getBakery(bakery.getName()).workTypes(data).items){
				player.SeedsInventory.addAmount(item, -amount)
			}
			bakery.use(data)
			GE.OK
		}
		else
		{
			GE.NotEnoughBuildingConditions
		}
		}
		else
		{
			GE.SocketWrongFormat
		}
	}

	def collect(bakery: Bakery, player: Player): Int =
	{
		val curtime = Instant.now();
		val slot = try bakery.slots(0) catch _ => null
		if (slot == null)
		{
			GE.WorkNotStarted
		}
		else if (slot.name == null)
		{
			GE.InternalServerLogicError
		}
		else if (slot.workEndTimeStamp.isAfter(curtime))
		{
			GE.WorkNotReady
		}
		else
		{
			val bakeryinfo = getBakery(bakery.getName())
			val worktypeinfo = bakeryinfo.workTypes(slot.name)
			for ((product, amount) <- worktypeinfo.products)
			{
				player.ItemInventory.addAmount(product, amount)
			}
			bakery.collect()
			bakery.slots.dequeue()
			GE.OK
		}

	}
}