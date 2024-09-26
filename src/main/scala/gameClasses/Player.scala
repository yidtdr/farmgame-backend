package gameClasses

import java.time.Instant
import Boosters._

class Player extends Serializable{
  object Inventory extends ItemsContainer;
  var money = 10000;
  var netWorth = 0;
  var spin: Spin = null;
  var orders: Array[Order] = null;

  object Businesses extends Serializable
  {
    var businesses: Array[Option[Business]] = Array.fill(BusinessManager.businessSettings.amount)(None)
  }

  object Stats extends Serializable
  {
    var ordersCompleted: Int = 0;
    var buildingsPlaced: Map[String, Int] = BuildingManager.typeByName.keySet.map(building => building -> 0).toMap
  }
  
  object Deals extends Serializable
  {
    var boughtDeals: Set[String]  = Set.empty
    var boosters: Array[BoosterJSON] = Array.empty
  }

  object ActiveBoosters extends Serializable
  {
    var OrderMoney: Option[OrderMoney]      = None
    var OrderItems: Option[OrderItems]      = None
    var WorkSpeed: Option[WorkSpeed]        = None
    var GrowSpeed: Option[GrowSpeed]    		= None
  }

  object Wallet extends Serializable
  {
    var tokenBalance: Long  = 200999900L
    var usdtBalance: Long   = 600000000L
    var tonBalance: Long    = 100000000L
  }
}