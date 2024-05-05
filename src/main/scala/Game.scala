import gameClasses._
import zio.json._

class Game extends Serializable{

  val player: Player = Player();
  val world: World = World();
  var lastOperation: Int = 0;
  def getState(): String =
  {
    s"${GameJSON.fromGame(this).toJson}"
  }
  def getLastOperation(): String =
  {
    s"${OperationJSON.fromCode(lastOperation).toJson}"
  }
  def onUse(item: String, x: Int, y: Int) =
  {
    if ((world.validateUse(x, y) == GE.OK))
      lastOperation = BuildingManager.use(player, world.getBuilding(x,y), item)
    else 
      lastOperation = GE.BuildingNotPlaced
  }
  def onCollect(x: Int, y: Int) =
  {
    if ((world.validateUse(x, y) == GE.OK))
      lastOperation = BuildingManager.collect(player, world.getBuilding(x,y))
    else 
      lastOperation = GE.BuildingNotPlaced
  }
  def onBuy(item: String, amount: Int) =
  {
    lastOperation = Shop.buy(item, amount, player)
  }
  def onPlace(building: String, x: Int, y: Int) =
  {
    if ((world.validatePlace(x, y) == GE.OK) && (BuildingManager.verifyPurchase(player, building) == GE.OK))
    {
      lastOperation = world.place(building, x, y);
    }
    else
    {
      lastOperation = GE.NotAbleToPlace
    }
  }
}
