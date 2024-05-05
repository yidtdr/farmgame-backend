package gameClasses

import _root_.`<empty>`.ENVars
import _root_.`<empty>`.GE

enum BuildingType:
	case garden, bakery

abstract class Buildable() extends Serializable{
	def collect(): Int
	def use(data: String): Int
	def getName(): String
}

object BuildingManager{
	var typeByName: Map[String, BuildingType] = Map.empty
	ENVars.ASSETS.blist.forEach((building) => {
		typeByName = typeByName.updated(building, BuildingType.bakery)
	})
	typeByName = typeByName.updated("garden", BuildingType.garden)

	def buildingExists(name: String): Boolean =
	{
		typeByName.isDefinedAt(name)
	}

	def getBuildingType(building: String): BuildingType =
	{
		typeByName(building);
	}

	def getBuildingByName(building: String): Buildable =
	{
		val buildingType = getBuildingType(building)
		buildingType match
			case BuildingType.bakery => Bakery(building)
			case BuildingType.garden => Garden()
		
	}

	def getPrice(building: String): Int =
	{
		val buildingType = getBuildingType(building)
		buildingType match
			case BuildingType.garden =>
				GardenManager.getPrice();
			case BuildingType.bakery =>
				BakeryManager.getPrice(building);
	}

	def verifyPurchase(player: Player, building: String): Int =
	{
		if (player.money >= getPrice(building))
		{
			GE.OK
		}
		else
		{
			GE.NotEnoughMoney
		}
	}

	def use(player: Player, building: Buildable, data: String): Int =
	{
		if (building.isInstanceOf[Garden]) {
			use(player, building.asInstanceOf[Garden], data)
		} else if (building.isInstanceOf[Bakery]) {
			use(player, building.asInstanceOf[Bakery], data)
		} else {
			GE.InternalClassMatchingError
		}
	}

	def collect(player: Player, building: Buildable): Int =
	{
		if (building.isInstanceOf[Garden]) {
			collect(player, building.asInstanceOf[Garden])
		} else if (building.isInstanceOf[Bakery]) {
			collect(player, building.asInstanceOf[Bakery])
		} else {
			GE.InternalClassMatchingError
		}
	}


			//	[goto management for garden]		
			
	def use(player: Player, building: Garden, data: String): Int =
	{
		GardenManager.use(building, data, player);
	}
	def collect(player: Player, building: Garden): Int =
	{
		GardenManager.collect(building, player);
	}


			//	[goto management for bakery]		

	def use(player: Player, building: Bakery, data: String): Int =
	{
		BakeryManager.use(building, data, player);
	}
	def collect(player: Player, building: Bakery): Int =
	{
		BakeryManager.collect(building, player);
	}
}