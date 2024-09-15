import com.typesafe.config.ConfigFactory
import java.nio.file.{Files, Path}

object ENVars {
	val readResult = ConfigFactory.parseFile(new java.io.File("configuration.conf"));
	val debug = readResult.getBoolean("debug");
	val botApiToken = readResult.getString("botToken")
	object DB
	{
		val dbHost = readResult.getString("DB.dbHost");
		val dbPort = readResult.getString("DB.dbPort");
		val dbName = readResult.getString("DB.dbName");
		val dbUser = readResult.getString("DB.dbUser");
		val dbPass = readResult.getString("DB.dbPass");
	}
	object GAME_SETTINGS
	{
			/* MAP */
		object MAP
		{
			object SIZE
			{
				val x = 40;
				val y = 40;
			}
		}
			/* SPIN */
		object SPIN
		{
			val spinItems = 6;
			val timeToSpin = 30;
		}

			/* ORDERS */
		object ORDERS
		{
			val ordersCompletionsTillReroll = 3;
			val ordersRerollTime = 30;
			val ordersAmountMin = 3;
			val ordersAmountMax = 5;
			val ordersRegenerationTime = 150;
		}

			/* TRANSACTIONS */
		object TRANSACTIONS
		{
			val maxPurchasedSlots = 2;
			val purchasedSlotsPrice = Array(1000, 2000);
		}

		object BUSINESSES
		{
			val cycleTime = 30;
		}
	}
	object ASSETS
	{
		val ambar = Path.of(s"resources/buildings/ambar.json")
		val business = Path.of(s"resources/business/business.json")
		val dealsFolder = "resources/deals"
		val itemlist = readResult.getStringList("ASSETS.ITEMS.list")
		var ITEMS: Map[String, Path] = Map.empty;
		itemlist.forEach((item) => {
			ITEMS = ITEMS.updated(item, Path.of(s"resources/items/${item}.json"));
		})

		val slist = readResult.getStringList("ASSETS.SEEDS.list")
		var SEEDS: Map[String, Path] = Map.empty;
		slist.forEach((item) => {
			SEEDS = SEEDS.updated(item, Path.of(s"resources/plants/${item}.json"));
		})

		val blist = readResult.getStringList("ASSETS.BUILDINGS.BAKERY.list")
		var BAKERIES: Map[String, Path] = Map.empty;
		blist.forEach((item) => {
			BAKERIES = BAKERIES.updated(item, Path.of(s"resources/buildings/${item}.json"));
		})

		val clist = readResult.getStringList("ASSETS.BUILDINGS.CORRAL.list")
		var CORRALS: Map[String, Path] = Map.empty;
		clist.forEach((item) => {
			CORRALS = CORRALS.updated(item, Path.of(s"resources/buildings/${item}.json"));
		})

		val bushlist = readResult.getStringList("ASSETS.BUILDINGS.BUSH.list")
		var BUSHES: Map[String, Path] = Map.empty;
		bushlist.forEach((item) => {
			BUSHES = BUSHES.updated(item, Path.of(s"resources/buildings/${item}.json"));
		})
		
		val garden = Path.of(s"resources/buildings/garden.json")
		
	}
}