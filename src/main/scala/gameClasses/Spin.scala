package gameClasses

import _root_.`<empty>`.GE
import _root_.`<empty>`.ENVars
import scala.util.Random
import ENVars.ASSETS.itemlist
import java.time.{Instant, Duration}

class Spin(wheelItems: Array[(String, Int)], dropItem: (String, Int)) extends Serializable
{
  val items: Array[(String, Int)] = wheelItems;
  val drop = dropItem;
  var activated = false;
  val generateTimeStamp = Instant.now()
}

object SpinManager
{
  val random = Random;
  def regenerate(player: Player): Int =
  {
    val spin = player.spin;
    if (spin == null)
    {
      val i = generateSpin(player)
      i
    }
    else
    {
      val curtime = Instant.now();
      val spintime = spin.generateTimeStamp.plusSeconds(ENVars.GAME_SETTINGS.SPIN.timeToSpin);
      if (spintime.isBefore(curtime))
      {
        generateSpin(player)
      }
      else
      {
        GE.SpinNotReady
      }
    }
  }
  def generateSpin(player: Player): Int =
  {
    val inventory = player.Inventory
    var wheelItems: Array[(String, Int)] = Array.fill[(String, Int)](6)(null);
    val amount = ENVars.GAME_SETTINGS.SPIN.spinItems;
    var itemset = inventory.items.keySet;
    if (itemset.size < 3)
    {
      player.spin = generateBasicSpin()
      GE.OK
    }
    else
    {
      val itemsamount = random.nextInt(4) + 2;
      var iter = 0;
      while (iter < itemsamount)
      {
        val randitem = random.nextInt(itemset.size - 1)
        val randamount = random.nextInt(10) + 1
        wheelItems(iter) = (itemset.iterator.drop(randitem).next(), randamount)
        iter+=1;
      }
      while (iter < amount)
      {
        val randamount = random.nextInt(50)
        wheelItems.update(iter, ("money", randamount))
        iter += 1;
      }
      val dropItem = wheelItems(0)
      player.spin = Spin(wheelItems, dropItem)
      GE.OK
    }
  }
  def generateBasicSpin(): Spin =
  {
    val amount = ENVars.GAME_SETTINGS.SPIN.spinItems;
    var wheelItems: Array[(String, Int)] = Array.fill[(String, Int)](6)(null);
    var iter = 0;
    while (iter < amount)
    {
      val randamount = random.nextInt(10)
      wheelItems.update(iter, ("wheat", randamount))
      iter+=1;
    }
    val dropItem = wheelItems(0)
    Spin(wheelItems, dropItem)
  }
  def spinWheel(player: Player): Int = 
  {
    val spin = player.spin;
    if (spin == null)
      GE.SpinNotGenerated
    else
    {
      if (!spin.activated)
      {
        val (dropItem, dropAmount) = spin.drop
        dropItem match {
        case "money" =>
          player.money += dropAmount
        case item => player.Inventory.addAmount(item, dropAmount)
        case null => println("wtf")  // handle unexpected drop item if necessary
        }
        player.spin.activated = true;
        GE.OK
      }
      else
      {
        GE.SpinNotReady
      }
    }
  }
}
