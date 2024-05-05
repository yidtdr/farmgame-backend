package gameClasses

import zio._
import zio.json._
import java.nio.file.{Files, Path}
import _root_.`<empty>`.ENVars

class Item() {
}

case class ItemINFO(name: String)
object ItemINFO{
  implicit val decoder: JsonDecoder[ItemINFO] = DeriveJsonDecoder.gen[ItemINFO]
}

object ItemManager
{
	var items: Map[String, ItemINFO] = Map.empty;

	for (name <- ENVars.ASSETS.ITEMS.keySet)
	{
		items = items.updated(name, nameToLoadItem(name))
	}

	def nameToItem(name: String): ItemINFO =
	{
		items(name)
	}

	def loadItem(path: Path): ItemINFO =
	{
		val bytes = Files.readAllBytes(path)
		val item = (new String(bytes, "UTF-8")).fromJson[ItemINFO].left.map(new Exception(_))
		item.getOrElse(null)
	}

	def nameToLoadItem(name: String): ItemINFO =
		loadItem(ENVars.ASSETS.SEEDS(name))
}