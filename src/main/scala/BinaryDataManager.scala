import java.io._
import _root_.serialization._

object BinaryDataManager {
  def writeGame(game: Game, username: String) =
    {
        try {
            val writeGame = convertGame(game);
            val file = new File(s"sessions/$username")
            file.mkdirs()
            val fileOutputStream = new FileOutputStream(file.getPath + "/game.bin")
            val objectOutputStream = new ObjectOutputStream(fileOutputStream)
            objectOutputStream.writeObject(writeGame)
            objectOutputStream.close()
            BSerializer.writeGame(writeGame);
        } catch {
            case e: Exception => e.printStackTrace()
        }
    }
  def readGame(username: String) : Game =
    {
        try {
            val fileInputStream = new FileInputStream(s"sessions/$username/game.bin")
            val objectInputStream = new ObjectInputStream(fileInputStream)
            val readGame = objectInputStream.readObject().asInstanceOf[sGame]
            BSerializer.readGame(username)
            objectInputStream.close()
            convertToGame(readGame)
        } catch {
            case e: Exception => 
                println(e.getMessage());
                e.printStackTrace()
                null
        }
    }
}
