import gameClasses._
import zio.json.JsonEncoder
import zio.json.DeriveJsonEncoder
import zio.json.ast.Json

case class ItemJSON(name: String)
object ItemJSON {
  implicit val encoder: JsonEncoder[ItemJSON] = DeriveJsonEncoder.gen[ItemJSON]
}

case class ItemsContainerJSON(map: Map[String, Int])
object ItemsContainerJSON {
  implicit val encoder: JsonEncoder[ItemsContainerJSON] = DeriveJsonEncoder.gen[ItemsContainerJSON]
}

case class PlayerJSON(money: Int, SeedsInventory: ItemsContainerJSON, ItemInventory: ItemsContainerJSON)
object PlayerJSON {
  implicit val encoder: JsonEncoder[PlayerJSON] = DeriveJsonEncoder.gen[PlayerJSON]
}

case class TileJSON(occupied: Boolean, place: String)
object TileJSON {
  implicit val encoder: JsonEncoder[TileJSON] = DeriveJsonEncoder.gen[TileJSON]
}

case class WorldJSON(tileArray: Array[Array[TileJSON]])
object WorldJSON {
  implicit val encoder: JsonEncoder[WorldJSON] = DeriveJsonEncoder.gen[WorldJSON]
}

case class GameJSON(dataType: String, player: PlayerJSON, world: WorldJSON)
object GameJSON {
  def fromGame(game: Game): GameJSON = GameJSON(
    "game-session",
    PlayerJSON(
      game.player.money, 
      ItemsContainerJSON(game.player.SeedsInventory.items.map { case (item, count) => item -> count }),
      ItemsContainerJSON(game.player.ItemInventory.items.map { case (item, count) => item -> count })
    ),
    WorldJSON(game.world.tileArray.map(_.map(tile => TileJSON(tile.occupied, if (tile.place != null) tile.place.getName() else "none"))))
  )

  implicit val gameDataEncoder: JsonEncoder[GameJSON] = DeriveJsonEncoder.gen[GameJSON]
}

case class OperationJSON(dataType: String, code: Int)
object OperationJSON
{
  def fromCode(code: Int): OperationJSON = OperationJSON("result-code", code)
  implicit val gameDataEncoder: JsonEncoder[OperationJSON] = DeriveJsonEncoder.gen[OperationJSON]
}
