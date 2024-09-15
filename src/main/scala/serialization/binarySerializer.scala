package serialization

import java.io._
import java.nio.ByteBuffer
import java.nio.file.{Files, Paths}
import java.time.Instant
import java.math.BigInteger

object BSerializer
{
    def writeGame(game: sGame) = {
        val dir = s"sessions/${game.CLIENT_INFO.tgID}"
        new java.io.File(dir).mkdirs()
        
        writePlayer(game.player, dir)
    }

    def readGame(tgID: String) = {
        val dir = s"sessions/${tgID}"

        readPlayer(dir)
    }

    def readPlayer(dir: String) = {
        val fis = new FileInputStream(s"${dir}/player/info.bin")
        val dis = new DataInputStream(fis)

        val money = dis.readInt()
        val netWorth = dis.readInt()

        dis.close()
        fis.close()

        readInventory(dir)
        readSpin(dir)
        readOrders(dir)
    }

    def readInventory(dir: String) = {
        val fis = new FileInputStream(s"${dir}/player/inventory.bin")
        val dis = new DataInputStream(fis)

        val map = SHelper.readMapString(dis)
        val capacity = dis.readInt()
        val itemsAmount = dis.readInt()
        val level = dis.readInt()

        dis.close()
        fis.close()
    }

    def readSpin(dir: String) = {
        val fis = new FileInputStream(s"${dir}/player/spin.bin")
        val dis = new DataInputStream(fis)

        val spin = SHelper.readSpinSlotsArray(dis)
        val dropName = SHelper.readString(dis)
        val dropAmount = dis.readInt()
        val activated = dis.readBoolean()
        val generateTimeStamp = SHelper.readInstant(dis)

        dis.close()
        fis.close()
    }

    def readOrders(dir: String) = {
        val fis = new FileInputStream(s"${dir}/player/orders.bin")
        val dis = new DataInputStream(fis)

        val size = dis.readInt()
        val array = new Array[sOrder](size)
        var index = 0
        while (index < size) {
            val order = SHelper.readOrder(dis)
            array.update(index, order)
            index += 1
        }   

        dis.close()
        dis.close()
    }

    def writePlayer(player: sPlayer, dir: String) = {
        val file = new java.io.File(s"$dir/player")
        file.mkdir()
        val fos = new FileOutputStream(file.getPath() + "/info.bin")
        val dos = new DataOutputStream(fos)

        dos.writeInt(player.money)
        dos.writeInt(player.netWorth)

        dos.close()
        fos.close()

        writeInventory(player, dir)
        if (player.spin != null) writeSpin(dir, player)
        if (player.orders != null) writeOrders(dir, player)
    }

    def writeInventory(player: sPlayer, dir: String) = {
        val file = new java.io.File(s"$dir/player")
        file.mkdir()
        val fos = new FileOutputStream(file.getPath() + "/inventory.bin")
        val dos = new DataOutputStream(fos)

        SHelper.writeMapString(player.Inventory.items, dos)
        dos.writeInt(player.Inventory.capacity)
        dos.writeInt(player.Inventory.itemsAmount)
        dos.writeInt(player.Inventory.level)

        dos.close()
        fos.close()
    }

    def writeSpin(dir: String, player: sPlayer) = {
        val file = new java.io.File(s"$dir/player")
        file.mkdir()
        val fos = new FileOutputStream(file.getPath() + "/spin.bin")
        val dos = new DataOutputStream(fos)

        SHelper.writeSpinSlotsArray(dos, player.spin)
        SHelper.writeString(dos, player.spin.drop._1)
        dos.writeInt(player.spin.drop._2)
        dos.writeBoolean(player.spin.activated)
        SHelper.writeInstant(dos, player.spin.generateTimeStamp)

        dos.close()
        fos.close()
    }

    def writeOrders(dir: String, player: sPlayer) = {
        val file = new java.io.File(s"$dir/player")
        file.mkdir()
        val fos = new FileOutputStream(file.getPath() + "/orders.bin")
        val dos = new DataOutputStream(fos)

        val size = player.orders.size
        dos.writeInt(size);
        player.orders.foreach((order) => 
            SHelper.writeOrder(dos, order)
        )
        
        dos.close()
        fos.close()
    }
}