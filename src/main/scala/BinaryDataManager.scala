import java.io._

object BinaryDataManager {
  def writeGame(game: Game, username: String) =
    {
        try {
            val file = new File(s"sessions/$username")
            file.mkdirs()
            val fileOutputStream = new FileOutputStream(file.getPath + "/game.bin")
            val objectOutputStream = new ObjectOutputStream(fileOutputStream)
            objectOutputStream.writeObject(game)
            objectOutputStream.close()
        } catch {
            case e: Exception => e.printStackTrace()
        }
    }
  def readGame(username: String) : Game =
    {
        try {
            val fileInputStream = new FileInputStream(s"sessions/$username/game.bin")
            val objectInputStream = new ObjectInputStream(fileInputStream)
            val readGame = objectInputStream.readObject().asInstanceOf[Game]
            objectInputStream.close()
            readGame
        } catch {
            case e: Exception => 
                e.printStackTrace()
                null
        }
    }
}
