import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import java.util.Base64
import _root_.`<empty>`.ENVars

object WebAppSignatureValidator {
  
  val token = ENVars.botApiToken

  def checkWebAppSignature(initData: String): Option[String] = {
    def parseQueryString(query: String): Map[String, String] = {
      query.split("&").map { param =>
        val keyValue = param.split("=")
        URLDecoder.decode(keyValue(0), StandardCharsets.UTF_8.toString) -> 
        URLDecoder.decode(keyValue(1), StandardCharsets.UTF_8.toString)
      }.toMap
    }

    try {
      val parsedData = parseQueryString(initData)
      if (!parsedData.contains("hash")) {
        return None
      }

      val hash = parsedData("hash")
      val dataCheckString = parsedData
        .filterKeys(_ != "hash")
        .toSeq
        .sortBy(_._1)
        .map { case (k, v) => s"$k=$v" }
        .mkString("\n")

      val secretKeySpec = new SecretKeySpec("WebAppData".getBytes(StandardCharsets.UTF_8), "HmacSHA256")
      val mac = Mac.getInstance("HmacSHA256")
      mac.init(secretKeySpec)
      val secretKey = mac.doFinal(token.getBytes(StandardCharsets.UTF_8))

      val finalSecretKeySpec = new SecretKeySpec(secretKey, "HmacSHA256")
      mac.init(finalSecretKeySpec)
      val calculatedHash = mac.doFinal(dataCheckString.getBytes(StandardCharsets.UTF_8))

      val calculatedHashHex = calculatedHash.map("%02x".format(_)).mkString

      if (calculatedHashHex == hash) Some(parsedData("user").split(",")(0).split(":")(1)) else None
    } catch {
      case _: Exception => None
    }
  }
}
