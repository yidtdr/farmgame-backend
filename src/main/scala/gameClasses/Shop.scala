package gameClasses
import _root_.`<empty>`.GE
import _root_.`<empty>`.ENVars


object Shop {
	def buy(name: String, amount: Int, player: Player): Int =
	{
		val item = Plants.nameToPlant(name);
		if (item == null)
		{
			GE.SocketWrongFormat
		}
		if (amount > 0)
		{
			if (player.money >= (amount * item.seed.price))
			{
				player.money -= (amount * item.seed.price)
				player.SeedsInventory.addAmount(item.name, amount * item.seed.amount)
				GE.OK
			}
			else
			{
				GE.NotEnoughMoney
			}
		}
		else
		{
			GE.InvalidAmount
		}
	}
}
