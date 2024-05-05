package gameClasses

import _root_.`<empty>`.GE

class World extends Serializable{
  val tileArray: Array[Array[Tile]] = Array.fill(100, 100)(new Tile)
  def place(building: String, x: Int, y: Int): Int =
  {
    tileArray(x)(y).place(building)
  }

  def getBuilding(x: Int, y: Int): Buildable =
  {
    tileArray(x)(y).place
  }

  def validatePlace(x: Int, y: Int): Int =
  {
    if (((x < 100) && (y < 100)) && ((x > 0) && (y > 0)))
    {
      if (tileArray(x)(y).isOccupied())
      {
        GE.NotAbleToPlace
      }
      else
      {
        GE.OK
      }
    }
    else
    {
      GE.GameEventOutOfBoundaries
    }
  }
  def validateUse(x: Int, y: Int): Int =
  {
    if (tileArray(x)(y).isOccupied())
    {
      GE.OK
    }
    else
    {
      GE.BuildingNotPlaced
    }
  }
}
