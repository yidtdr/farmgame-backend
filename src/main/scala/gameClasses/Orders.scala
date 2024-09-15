package gameClasses

import scala.util.Random
import _root_.`<empty>`.GE
import _root_.`<empty>`.ENVars
import java.time.Instant

class Order(orderItems: Map[String, Int], orderPrice: Int, timeStamp: Instant, orderTokenPrice: Int) extends Serializable
{
	val items = orderItems
	val price = orderPrice
	val tokenPrice = orderTokenPrice
	var completed = false
	val startTimeStamp = timeStamp
}

object OrderManager
{
	val random = Random
	def getListSize(player: Player): Int =
	{
		Math.min(Math.round(Math.log10(player.netWorth+1)).toInt, 5) + 1;
	}
	def regenerate(player: Player): Int =
	{
		if (player.orders == null)
		{
			val orderListSize = getListSize(player);
			generateOrderList(player, orderListSize);
		}
		else
		{
			val keysetSize = player.Inventory.items.keySet.size;
			if (getListSize(player) > player.orders.size)
			{
				if (keysetSize > 2) player.orders = player.orders.appended(generateOrder(player, Instant.now()))
			}
			GE.OK
		}
	}
	def completeOrder(player: Player, orderID: Int): Int =
	{
		if (orderID < player.orders.size)
		{
			if (!player.orders(orderID).completed)
			{
				val itemBoosterPercentage = BoosterManager.getPercentage(player.ActiveBoosters.OrderItems)
				var ableToComplete = true;
				val order = player.orders(orderID)
				order.items.foreach(item => 
					{
						val amount = Math.round(item._2 * (1 - itemBoosterPercentage * 0.01)).toInt
						if (player.Inventory.getAmount(item._1).getOrElse(0) < amount){
								ableToComplete = false;
							}
					})
				if (order.startTimeStamp.isAfter(Instant.now()))
				{
					ableToComplete = false
				}
				if (ableToComplete) {
					player.orders(orderID).items.foreach(item => {
							val itemName = item._1;
							val amount = Math.round(item._2 * (1 - itemBoosterPercentage * 0.01)).toInt
							player.Inventory.addAmount(itemName, -amount)
						})
					val moneyBoosterPercentage = BoosterManager.getPercentage(player.ActiveBoosters.OrderMoney)
					val moneyReward = Math.round(order.price * (1 + moneyBoosterPercentage * 0.01)).toInt
					val tokenReward = Math.round(order.tokenPrice * (1 + moneyBoosterPercentage * 0.01)).toInt
					player.orders(orderID).completed = true;
					player.money += moneyReward;
					player.Stats.ordersCompleted += 1;
					player.netWorth += moneyReward;
					player.Wallet.tokenBalance += tokenReward;
					if (player.Inventory.items.keySet.size < 3)
					{
						player.orders.update(orderID, generateStartingOrder(ENVars.GAME_SETTINGS.ORDERS.ordersRegenerationTime))
					}
					else
					{
						player.orders.update(orderID, generateOrder(player, Instant.now().plusSeconds(ENVars.GAME_SETTINGS.ORDERS.ordersRegenerationTime)))
					}
					GE.OK
				}
				else
				{
					GE.OrderNotEnoughItemsToComplete
				}
			}
			else
			{
				GE.OrderAlreadyCompleted
			}
		}
		else
		{
			GE.GameEventOutOfBoundaries
		}
	}
	def generateOrder(itemset: Set[String], timeStamp: Instant, netWorth: Int): Order =
	{
		var orderItems: Map[String, Int] = Map.empty;
		for (key <- itemset)
		{
			orderItems = orderItems.updated(key, 0)
		}
		var orderPrice = 0;
		var orderTokenPrice = 0;
		val orderItemsAmount = random.nextInt(itemset.size) + 1;
		var iter = 0;
		while (iter < orderItemsAmount)
		{
			val randItem = random.nextInt(itemset.size - 1)
			val randItemName = (itemset.iterator.drop(randItem).next())
			val amountBoundaries = ItemManager.nameToOrderAmountLimits(randItemName)
			val randAmount = Math.round((random.nextInt(amountBoundaries._2 - amountBoundaries._1 + 1) + amountBoundaries._1)).toInt // [!!!] * log100(networth)
			val newValue = orderItems(randItemName) + randAmount
			orderItems = orderItems.updated(randItemName, randAmount)
			orderPrice += Math.floor(ItemManager.nameToPrice(randItemName) * randAmount * (1 + random.nextDouble())).toInt;
			orderTokenPrice += ItemManager.nameToTokenPrice(randItemName) * randAmount
			iter+=1;
		}
		Order(orderItems, orderPrice, timeStamp, orderTokenPrice)
	}
	def generateOrder(player: Player, timeStamp: Instant): Order =
	{
		val inventory = player.Inventory
		var itemset = inventory.items.keySet;
		generateOrder(itemset, timeStamp, player.netWorth)
	}
	def generateStartingOrder(): Order =
	{
		val wheatMap = Map[String, Int](("wheat", 3))
		Order(wheatMap, 15, Instant.now(), 10)
	}
	def generateStartingOrder(delay: Int): Order =
	{
		val wheatMap = Map[String, Int](("wheat", 3))
		Order(wheatMap, 15, Instant.now().plusSeconds(delay), 10)
	}
	def generateOrderList(player: Player, orderListSize: Int): Int =
	{
		val inventory = player.Inventory
		var itemset = inventory.items.keySet;
		var orderNumber = 0;
		val ordersArray: Array[Order] = Array.fill[Order](orderListSize)(null);
		
		if (itemset.size > 1)
		{
			while (orderNumber < orderListSize)
			{
				ordersArray(orderNumber) = generateOrder(itemset, Instant.now(), player.netWorth);
				orderNumber+=1;
			}

			player.orders = ordersArray
			GE.OK
		}
		else
		{
			while (orderNumber < orderListSize)
			{
				ordersArray(orderNumber) = generateStartingOrder();
				orderNumber+=1;
			}

			player.orders = ordersArray
			GE.OK
		}
	}
	def rerollOrder(player: Player, orderID: Int): Int =
	{
		if ((orderID >= 0) && (orderID < player.orders.size))
		{
			if (player.orders(orderID).startTimeStamp.isBefore(Instant.now()))
			{
				if (!player.orders(orderID).completed)
				{
					if (player.Inventory.items.size > 2)
					{
						player.orders.update(orderID, generateOrder(player, Instant.now().plusSeconds(ENVars.GAME_SETTINGS.ORDERS.ordersRerollTime)))
					}
					else
					{
						player.orders.update(orderID,generateStartingOrder(ENVars.GAME_SETTINGS.ORDERS.ordersRerollTime));
					}
					GE.OK
				}
				else
				{
					GE.OrderAlreadyCompleted
				}
			} else GE.OrdersNotReady
		}
		else
		{
			GE.GameEventOutOfBoundaries
		}
	}
}