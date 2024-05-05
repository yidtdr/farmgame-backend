file:///D:/bestgame/src/main/scala/gameClasses/Bakery.scala
### java.lang.AssertionError: assertion failed: found: Array.apply[gameClasses.Slot]: (implicit evidence$5: scala.reflect.ClassTag[gameClasses.Slot]):
  Array[gameClasses.Slot], expected: Any

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.1
Classpath:
<WORKSPACE>\.bloop\bestgame\bloop-bsp-clients-classes\classes-Metals-HZ0nA7hUSa-8gD7vgzObjA== [exists ], <HOME>\AppData\Local\bloop\cache\semanticdb\com.sourcegraph.semanticdb-javac.0.9.9\semanticdb-javac-0.9.9.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala3-library_3\3.3.1\scala3-library_3-3.3.1.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\zio_3\2.0.21\zio_3-2.0.21.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\zio-http_3\3.0.0-RC3\zio-http_3-3.0.0-RC3.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scalikejdbc\scalikejdbc_3\4.2.1\scalikejdbc_3-4.2.1.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\com\h2database\h2\1.4.200\h2-1.4.200.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\ch\qos\logback\logback-classic\1.2.12\logback-classic-1.2.12.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\postgresql\postgresql\42.3.1\postgresql-42.3.1.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\com\typesafe\config\1.4.1\config-1.4.1.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\zio-json_3\0.6.2\zio-json_3-0.6.2.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala-library\2.13.10\scala-library-2.13.10.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\zio-internal-macros_3\2.0.21\zio-internal-macros_3-2.0.21.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\zio-stacktracer_3\2.0.21\zio-stacktracer_3-2.0.21.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\izumi-reflect_3\2.3.8\izumi-reflect_3-2.3.8.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-codec-http\4.1.100.Final\netty-codec-http-4.1.100.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-handler-proxy\4.1.100.Final\netty-handler-proxy-4.1.100.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-transport-native-epoll\4.1.100.Final\netty-transport-native-epoll-4.1.100.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-transport-native-epoll\4.1.100.Final\netty-transport-native-epoll-4.1.100.Final-linux-x86_64.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-transport-native-epoll\4.1.100.Final\netty-transport-native-epoll-4.1.100.Final-linux-aarch_64.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-transport-native-kqueue\4.1.100.Final\netty-transport-native-kqueue-4.1.100.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-transport-native-kqueue\4.1.100.Final\netty-transport-native-kqueue-4.1.100.Final-osx-x86_64.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-transport-native-kqueue\4.1.100.Final\netty-transport-native-kqueue-4.1.100.Final-osx-aarch_64.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\zio-streams_3\2.0.18\zio-streams_3-2.0.18.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\zio-schema_3\0.4.14\zio-schema_3-0.4.14.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\zio-schema-json_3\0.4.14\zio-schema-json_3-0.4.14.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\zio-schema-protobuf_3\0.4.14\zio-schema-protobuf_3-0.4.14.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\incubator\netty-incubator-transport-native-io_uring\0.0.20.Final\netty-incubator-transport-native-io_uring-0.0.20.Final-linux-x86_64.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scalikejdbc\scalikejdbc-core_3\4.2.1\scalikejdbc-core_3-4.2.1.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scalikejdbc\scalikejdbc-interpolation_3\4.2.1\scalikejdbc-interpolation_3-4.2.1.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\ch\qos\logback\logback-core\1.2.12\logback-core-1.2.12.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\slf4j\slf4j-api\2.0.12\slf4j-api-2.0.12.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\checkerframework\checker-qual\3.5.0\checker-qual-3.5.0.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\modules\scala-collection-compat_3\2.11.0\scala-collection-compat_3-2.11.0.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\com\softwaremill\magnolia1_3\magnolia_3\1.3.0\magnolia_3-1.3.0.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\izumi-reflect-thirdparty-boopickle-shaded_3\2.3.8\izumi-reflect-thirdparty-boopickle-shaded_3-2.3.8.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-common\4.1.100.Final\netty-common-4.1.100.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-buffer\4.1.100.Final\netty-buffer-4.1.100.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-transport\4.1.100.Final\netty-transport-4.1.100.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-codec\4.1.100.Final\netty-codec-4.1.100.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-handler\4.1.100.Final\netty-handler-4.1.100.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-codec-socks\4.1.100.Final\netty-codec-socks-4.1.100.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-transport-native-unix-common\4.1.100.Final\netty-transport-native-unix-common-4.1.100.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-transport-classes-epoll\4.1.100.Final\netty-transport-classes-epoll-4.1.100.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-transport-classes-kqueue\4.1.100.Final\netty-transport-classes-kqueue-4.1.100.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\zio-schema-macros_3\0.4.14\zio-schema-macros_3-0.4.14.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\zio-prelude_3\1.0.0-RC18\zio-prelude_3-1.0.0-RC18.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\zio-constraintless_3\0.3.2\zio-constraintless_3-0.3.2.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\zio-schema-derivation_3\0.4.14\zio-schema-derivation_3-0.4.14.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\incubator\netty-incubator-transport-classes-io_uring\0.0.20.Final\netty-incubator-transport-classes-io_uring-0.0.20.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\apache\commons\commons-dbcp2\2.11.0\commons-dbcp2-2.11.0.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\modules\scala-parser-combinators_3\2.3.0\scala-parser-combinators_3-2.3.0.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scalikejdbc\scalikejdbc-interpolation-macro_3\4.2.1\scalikejdbc-interpolation-macro_3-4.2.1.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\io\netty\netty-resolver\4.1.100.Final\netty-resolver-4.1.100.Final.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\dev\zio\zio-prelude-macros_3\1.0.0-RC18\zio-prelude-macros_3-1.0.0-RC18.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\apache\commons\commons-pool2\2.12.0\commons-pool2-2.12.0.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\commons-logging\commons-logging\1.2\commons-logging-1.2.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\jakarta\transaction\jakarta.transaction-api\1.3.3\jakarta.transaction-api-1.3.3.jar [exists ]
Options:
-Xsemanticdb -sourceroot <WORKSPACE>


action parameters:
offset: 832
uri: file:///D:/bestgame/src/main/scala/gameClasses/Bakery.scala
text:
```scala
package gameClasses

import zio._
import zio.json._
import java.nio.file.{Files, Path}
import java.time.{Instant, Duration}
import _root_.`<empty>`.ENVars


case class WorkTypeINFO(timeToFinish: Int, items: Map[String, Int], products: Map[String, Int])
case class BakeryINFO(name: String, price: Int, maxSlots: Int, workTypes: Map[String, WorkTypeINFO])

object WorkTypeINFO
{
  implicit val decoder: JsonDecoder[WorkTypeINFO] = DeriveJsonDecoder.gen[WorkTypeINFO]
}

object BakeryINFO
{
  implicit val decoder: JsonDecoder[BakeryINFO] = DeriveJsonDecoder.gen[BakeryINFO]
}

class Slot(workTypeName: String)
{
	val workType = workTypeName;
	var workStartTimeStamp = Instant.now();
	var workEndTimeStamp = Instant.now();
}

class Bakery(name: String) extends Buildable
{
	val bakeryType = name;
	var slots: Array[Slot] = Array[Slot].@@
	def collect(): Int = 
	{0}
	def use(data: String): Int =
	{0}
	def getName(): String =
	{
		bakeryType
	}
}

object BakeryManager
{
	var bakeriesINFO: Map[String, BakeryINFO] = Map.empty

	for (name <- ENVars.ASSETS.BAKERIES.keySet)
	{
    	bakeriesINFO = bakeriesINFO.updated(name, nameToLoadBakery(name))
    }
	
	def nameToLoadBakery(name: String): BakeryINFO =
		loadBakery(ENVars.ASSETS.SEEDS(name))

	def loadBakery(path: Path): BakeryINFO =
	{
		val bytes = Files.readAllBytes(path)
		val bakery = (new String(bytes, "UTF-8")).fromJson[BakeryINFO].left.map(new Exception(_))
		bakery.getOrElse(null)
	}

	def getPrice(building: String): Int =
	{
		bakeriesINFO(building).price
	}

	def getMaxSlots(building: String): Int =
	{
		bakeriesINFO(building).maxSlots
	}

	def use(bakery: Bakery, data: String, player: Player): Int =
	{
		0
	}

	def collect(bakery: Bakery, player: Player): Int =
	{
		0
	}
}
```



#### Error stacktrace:

```
scala.runtime.Scala3RunTime$.assertFailed(Scala3RunTime.scala:8)
	dotty.tools.dotc.typer.Implicits$ImplicitSearch.<init>(Implicits.scala:1195)
	dotty.tools.dotc.interactive.Completion$Completer.implicitConversionTargets(Completion.scala:515)
	dotty.tools.dotc.interactive.Completion$Completer.implicitConversionMemberCompletions(Completion.scala:380)
	dotty.tools.dotc.interactive.Completion$Completer.selectionCompletions(Completion.scala:322)
	dotty.tools.dotc.interactive.Completion$.computeCompletions(Completion.scala:145)
	dotty.tools.dotc.interactive.Completion$.completions(Completion.scala:50)
	scala.meta.internal.pc.completions.Completions.completions(Completions.scala:196)
	scala.meta.internal.pc.completions.CompletionProvider.completions(CompletionProvider.scala:86)
	scala.meta.internal.pc.ScalaPresentationCompiler.complete$$anonfun$1(ScalaPresentationCompiler.scala:147)
```
#### Short summary: 

java.lang.AssertionError: assertion failed: found: Array.apply[gameClasses.Slot]: (implicit evidence$5: scala.reflect.ClassTag[gameClasses.Slot]):
  Array[gameClasses.Slot], expected: Any