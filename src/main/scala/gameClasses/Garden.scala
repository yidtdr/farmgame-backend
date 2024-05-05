package gameClasses

import java.time.{Instant, Duration}
import zio._
import zio.json._
import java.nio.file.{Files, Path}
import _root_.`<empty>`.ENVars
import _root_.`<empty>`.GE
import java.sql.Date
import zio.http.Header.IfRange.DateTime

case class GardenINFO(price: Int, name: String)

object GardenINFO
{
	implicit val decoder: JsonDecoder[GardenINFO] = DeriveJsonDecoder.gen[GardenINFO]
}

class Garden() extends Buildable
{
	var curplant: String = null
	var plantTimeStamp = Instant.now();
	var growTimeStamp = Instant.now();

	def use(data: String): Int =
	{
		plant(data)
	}

	def plant(newplant: String): Int =
	{
		curplant = newplant
		plantTimeStamp = Instant.now()
		growTimeStamp = plantTimeStamp.plusSeconds(Plants.getTimeToGrow(newplant))
		GE.OK
	}
	def collect(): Int =
	{
		val curTime = Instant.now();
		curplant = null
		plantTimeStamp = curTime
		growTimeStamp = curTime
		GE.OK
	}

	def getName(): String =
	{
		"garden"
	}
}

object GardenManager
{
	val path = ENVars.ASSETS.garden;
	val bytes = Files.readAllBytes(path)
	val gardenINFO = (new String(bytes, "UTF-8")).fromJson[GardenINFO].left.map(new Exception(_)).getOrElse(null)
	if (gardenINFO == null) println("[FATAL ERROR] Cannot read gardenINFO")

	def collect(garden: Garden, player: Player): Int =
	{
		val curTime = Instant.now();
		val curplant = garden.curplant;
		if (curplant != null)
		{
			if (garden.growTimeStamp.isBefore(curTime))
			{
				garden.collect();
				player.SeedsInventory.addAmount(curplant, Plants.getGrowAmount(curplant))
				GE.OK
			}
			else
			{
				GE.PlantNotGrown
			}
		}
		else
		{
			GE.PlantNotPlanted
		}
	}

	def use(garden: Garden, data: String, player: Player): Int =
	{
		if (Plants.plantDefined(data))
		{
		if (garden.curplant == null)
		{
			if (player.SeedsInventory.getAmount(data).getOrElse(0) < 1)
			{
				GE.NotEnoughBuildingConditions
			}
			else
			{
				player.SeedsInventory.addAmount(data, -1);
				garden.use(data);
				GE.OK
			}
		}
		else
		{
			GE.CannotUseBuilding
		}
		}
		else
		{
			GE.SocketWrongFormat
		}
	}

	def getPrice(): Int =
	{
		gardenINFO.price
	}
}