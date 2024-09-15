package serialization

import java.io._
import java.time.Instant
import java.math.BigInteger

object SHelper {
  def writeString(out: DataOutputStream, str: String): Unit = {
    if (str != null) {
      out.writeBoolean(true)
      out.writeUTF(str)
    } else {
      out.writeBoolean(false)
    }
  }

  def readString(in: DataInputStream): String = {
    if (in.readBoolean()) in.readUTF() else null
  }

  def writeOptionInt(out: DataOutputStream, value: Option[Int]): Unit = {
    out.writeBoolean(value.isDefined)
    value.foreach(out.writeInt)
  }

  def readOptionInt(in: DataInputStream): Option[Int] = {
    if (in.readBoolean()) Some(in.readInt()) else None
  }

  def writeOptionLong(out: DataOutputStream, value: Option[Long]): Unit = {
    out.writeBoolean(value.isDefined)
    value.foreach(out.writeLong)
  }

  def readOptionLong(in: DataInputStream): Option[Long] = {
    if (in.readBoolean()) Some(in.readLong()) else None
  }

  def writeInstant(out: DataOutputStream, instant: Instant): Unit = {
    out.writeLong(instant.getEpochSecond())
    out.writeInt(instant.getNano())
  }

  def readInstant(in: DataInputStream): Instant = {
    Instant.ofEpochSecond(in.readLong(), in.readInt())
  }

  def writeBigInteger(out: DataOutputStream, value: BigInteger): Unit = {
    val byteArray = value.toByteArray
    out.writeInt(byteArray.length)
    out.write(byteArray)
  }

  def readBigInteger(in: DataInputStream): BigInteger = {
    val length = in.readInt()
    val byteArray = new Array[Byte](length)
    in.readFully(byteArray)
    new BigInteger(byteArray)
  }

  def writeMapString(map: Map[String, Int], dos: DataOutputStream): Unit = {
    dos.writeInt(map.size)
    for ((key, value) <- map) {
      // Write the string key (length and content)
      writeString(dos, key)
      
      // Write the integer value
      dos.writeInt(value)
    }
  }

  def readMapString(dis: DataInputStream): Map[String, Int] = {
    val size = dis.readInt()
    val map = scala.collection.mutable.Map[String, Int]() // Use a mutable map

    var left = size;
    while (left > 0)
    {
      val key = readString(dis)

      // Read the integer value
      val value = dis.readInt()

      map += (key -> value)                    // Add the pair to the map
      left -= 1;
    }

    map.toMap  // Convert back to an immutable map if needed
  }



  def writeSpinSlotsArray(dos: DataOutputStream, spin: sSpin): Unit = {
    val array = spin.items
    val size = array.size

    dos.writeInt(size)

    var index = 0
    while (index < size) {
      writeString(dos, array(index)._1)
      dos.writeInt(array(index)._2)
      index += 1
    }
  }


  def readSpinSlotsArray(dis: DataInputStream): Array[(String, Int)] = {
    val size = dis.readInt()
    val array = new Array[(String, Int)](size)

    var index = 0
    while (index < size) {
      val _s = readString(dis)
      val _i = dis.readInt()
      array.update(index, (_s, _i))
      index += 1
    }

    array
  }

  def writeOrder(dos: DataOutputStream, order: sOrder) = {
    dos.writeBoolean(order.completed) // completed
    writeMapString(order.items, dos) // items
    dos.writeInt(order.price) // price
    writeInstant(dos, order.startTimeStamp) // start time stamp
    dos.writeInt(order.tokenPrice) // tokenPrice
  }

  def readOrder(dis: DataInputStream): sOrder = {
    val completed = dis.readBoolean()
    val items = readMapString(dis)
    val price = dis.readInt()
    val startTimeStamp = readInstant(dis)
    val tokenPrice = dis.readInt()

    sOrder(items, price, tokenPrice, completed, startTimeStamp)
  }
}