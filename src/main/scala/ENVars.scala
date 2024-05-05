import com.typesafe.config.ConfigFactory
import java.nio.file.{Files, Path}

object ENVars {
	val readResult = ConfigFactory.parseFile(new java.io.File("configuration.conf"));
	val debug = readResult.getBoolean("debug");
	object DB
	{
		val dbHost = readResult.getString("DB.dbHost");
		val dbPort = readResult.getString("DB.dbPort");
		val dbName = readResult.getString("DB.dbName");
		val dbUser = readResult.getString("DB.dbUser");
		val dbPass = readResult.getString("DB.dbPass");
	}
	object ASSETS
	{
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
		val garden = Path.of(s"resources/buildings/garden.json")
		
	}
}