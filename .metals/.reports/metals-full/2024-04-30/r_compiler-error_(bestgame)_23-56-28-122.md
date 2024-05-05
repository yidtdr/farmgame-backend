file:///D:/bestgame/src/main/scala/JSON.scala
### java.lang.IndexOutOfBoundsException: 0

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.1
Classpath:
<WORKSPACE>\.bloop\bestgame\bloop-bsp-clients-classes\classes-Metals-Vee9RbCfR-SccetgiTk7Tw== [missing ], <HOME>\AppData\Local\bloop\cache\semanticdb\com.sourcegraph.semanticdb-javac.0.9.9\semanticdb-javac-0.9.9.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala3-library_3\3.3.1\scala3-library_3-3.3.1.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\zio_3\2.0.21\zio_3-2.0.21.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\zio-http_3\3.0.0-RC3\zio-http_3-3.0.0-RC3.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scalikejdbc\scalikejdbc_3\4.2.1\scalikejdbc_3-4.2.1.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\com\h2database\h2\1.4.200\h2-1.4.200.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\ch\qos\logback\logback-classic\1.2.12\logback-classic-1.2.12.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\postgresql\postgresql\42.3.1\postgresql-42.3.1.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\com\typesafe\config\1.4.1\config-1.4.1.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\zio-json_3\0.6.2\zio-json_3-0.6.2.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala-library\2.13.10\scala-library-2.13.10.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\zio-internal-macros_3\2.0.21\zio-internal-macros_3-2.0.21.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\zio-stacktracer_3\2.0.21\zio-stacktracer_3-2.0.21.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\izumi-reflect_3\2.3.8\izumi-reflect_3-2.3.8.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-codec-http\4.1.100.Final\netty-codec-http-4.1.100.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-handler-proxy\4.1.100.Final\netty-handler-proxy-4.1.100.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-transport-native-epoll\4.1.100.Final\netty-transport-native-epoll-4.1.100.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-transport-native-epoll\4.1.100.Final\netty-transport-native-epoll-4.1.100.Final-linux-x86_64.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-transport-native-epoll\4.1.100.Final\netty-transport-native-epoll-4.1.100.Final-linux-aarch_64.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-transport-native-kqueue\4.1.100.Final\netty-transport-native-kqueue-4.1.100.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-transport-native-kqueue\4.1.100.Final\netty-transport-native-kqueue-4.1.100.Final-osx-x86_64.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-transport-native-kqueue\4.1.100.Final\netty-transport-native-kqueue-4.1.100.Final-osx-aarch_64.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\zio-streams_3\2.0.18\zio-streams_3-2.0.18.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\zio-schema_3\0.4.14\zio-schema_3-0.4.14.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\zio-schema-json_3\0.4.14\zio-schema-json_3-0.4.14.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\zio-schema-protobuf_3\0.4.14\zio-schema-protobuf_3-0.4.14.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\incubator\netty-incubator-transport-native-io_uring\0.0.20.Final\netty-incubator-transport-native-io_uring-0.0.20.Final-linux-x86_64.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scalikejdbc\scalikejdbc-core_3\4.2.1\scalikejdbc-core_3-4.2.1.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scalikejdbc\scalikejdbc-interpolation_3\4.2.1\scalikejdbc-interpolation_3-4.2.1.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\ch\qos\logback\logback-core\1.2.12\logback-core-1.2.12.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\slf4j\slf4j-api\2.0.12\slf4j-api-2.0.12.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\checkerframework\checker-qual\3.5.0\checker-qual-3.5.0.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\modules\scala-collection-compat_3\2.11.0\scala-collection-compat_3-2.11.0.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\com\softwaremill\magnolia1_3\magnolia_3\1.3.0\magnolia_3-1.3.0.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\izumi-reflect-thirdparty-boopickle-shaded_3\2.3.8\izumi-reflect-thirdparty-boopickle-shaded_3-2.3.8.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-common\4.1.100.Final\netty-common-4.1.100.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-buffer\4.1.100.Final\netty-buffer-4.1.100.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-transport\4.1.100.Final\netty-transport-4.1.100.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-codec\4.1.100.Final\netty-codec-4.1.100.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-handler\4.1.100.Final\netty-handler-4.1.100.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-codec-socks\4.1.100.Final\netty-codec-socks-4.1.100.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-transport-native-unix-common\4.1.100.Final\netty-transport-native-unix-common-4.1.100.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-transport-classes-epoll\4.1.100.Final\netty-transport-classes-epoll-4.1.100.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-transport-classes-kqueue\4.1.100.Final\netty-transport-classes-kqueue-4.1.100.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\zio-schema-macros_3\0.4.14\zio-schema-macros_3-0.4.14.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\zio-prelude_3\1.0.0-RC18\zio-prelude_3-1.0.0-RC18.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\zio-constraintless_3\0.3.2\zio-constraintless_3-0.3.2.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\zio-schema-derivation_3\0.4.14\zio-schema-derivation_3-0.4.14.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\incubator\netty-incubator-transport-classes-io_uring\0.0.20.Final\netty-incubator-transport-classes-io_uring-0.0.20.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\apache\commons\commons-dbcp2\2.11.0\commons-dbcp2-2.11.0.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\modules\scala-parser-combinators_3\2.3.0\scala-parser-combinators_3-2.3.0.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scalikejdbc\scalikejdbc-interpolation-macro_3\4.2.1\scalikejdbc-interpolation-macro_3-4.2.1.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-resolver\4.1.100.Final\netty-resolver-4.1.100.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\zio-prelude-macros_3\1.0.0-RC18\zio-prelude-macros_3-1.0.0-RC18.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\apache\commons\commons-pool2\2.12.0\commons-pool2-2.12.0.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\commons-logging\commons-logging\1.2\commons-logging-1.2.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\jakarta\transaction\jakarta.transaction-api\1.3.3\jakarta.transaction-api-1.3.3.jar [exists ]
Options:
-Xsemanticdb -sourceroot <WORKSPACE>


action parameters:
offset: 1263
uri: file:///D:/bestgame/src/main/scala/JSON.scala
text:
```scala
import gameClasses._
import zio.json.JsonEncoder
import zio.json.DeriveJsonEncoder
import zio.json.ast.Json

case class ItemJSON(name: String)
object ItemJSON {
  implicit val encoder: JsonEncoder[ItemJSON] = DeriveJsonEncoder.gen[ItemJSON]
}

case class ItemsContainerJSON(map: Map[String, Int])
object ItemsContainerJSON {
  implicit val encoder: JsonEncoder[ItemsContainerJSON] = DeriveJsonEncoder.gen[ItemsContainerJSON]
}

case class PlayerJSON(money: Int, SeedsInventory: ItemsContainerJSON, ItemInventory: ItemsContainerJSON)
object PlayerJSON {
  implicit val encoder: JsonEncoder[PlayerJSON] = DeriveJsonEncoder.gen[PlayerJSON]
}

case class TileJSON(occupied: Boolean, place: String)
object TileJSON {
  implicit val encoder: JsonEncoder[TileJSON] = DeriveJsonEncoder.gen[TileJSON]
}

case class WorldJSON(tileArray: Array[Array[TileJSON]])
object WorldJSON {
  implicit val encoder: JsonEncoder[WorldJSON] = DeriveJsonEncoder.gen[WorldJSON]
}

case class GameJSON(dataType: String, player: PlayerJSON, world: WorldJSON)
object GameJSON {
  def fromGame(game: Game): GameJSON = GameJSON(
    "game-session",
    PlayerJSON(
      game.player.money, 
      ItemsContainerJSON(game.player.SeedsInventory.items.map { case (item, count) => item -> count }),@@

    ),
    WorldJSON(game.world.tileArray.map(_.map(tile => TileJSON(tile.occupied, if (tile.place != null) "garden" else "none"))))
  )

  implicit val gameDataEncoder: JsonEncoder[GameJSON] = DeriveJsonEncoder.gen[GameJSON]
}

case class OperationJSON(dataType: String, code: Int)
object OperationJSON
{
  def fromCode(code: Int): OperationJSON = OperationJSON("result-code", code)
  implicit val gameDataEncoder: JsonEncoder[OperationJSON] = DeriveJsonEncoder.gen[OperationJSON]
}

```



#### Error stacktrace:

```
scala.collection.LinearSeqOps.apply(LinearSeq.scala:131)
	scala.collection.LinearSeqOps.apply$(LinearSeq.scala:128)
	scala.collection.immutable.List.apply(List.scala:79)
	dotty.tools.dotc.util.Signatures$.countParams(Signatures.scala:501)
	dotty.tools.dotc.util.Signatures$.applyCallInfo(Signatures.scala:186)
	dotty.tools.dotc.util.Signatures$.computeSignatureHelp(Signatures.scala:94)
	dotty.tools.dotc.util.Signatures$.signatureHelp(Signatures.scala:63)
	scala.meta.internal.pc.MetalsSignatures$.signatures(MetalsSignatures.scala:17)
	scala.meta.internal.pc.SignatureHelpProvider$.signatureHelp(SignatureHelpProvider.scala:51)
	scala.meta.internal.pc.ScalaPresentationCompiler.signatureHelp$$anonfun$1(ScalaPresentationCompiler.scala:414)
```
#### Short summary: 

java.lang.IndexOutOfBoundsException: 0