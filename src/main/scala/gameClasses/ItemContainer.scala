package gameClasses

class ItemsContainer extends Serializable{
    var items: Map[String, Int] = Map.empty;

    def getAmount(item: String): Option[Int] = {
        items.get(item)
    }

    def addAmount(item: String, amount: Int) =
    {
        if (items.isDefinedAt(item))
        {
            val newValue = items(item) + amount
            items = items.updated(item, newValue)
        }
        else
        {
            items = items.updated(item, amount)
        }
    }
}