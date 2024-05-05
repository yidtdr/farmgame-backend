error id: QmmdDQYXCQ69oAA9jaTQyg==
### Bloop error:

Unexpected error when compiling bestgame: java.nio.file.NoSuchFileException: <WORKSPACE>\.bloop\bestgame\bloop-bsp-clients-classes\classes-Metals-gLEgjcTAT8qBNgdsdWiaBw==\DataService$$anon$7.class
	at java.base/sun.nio.fs.WindowsException.translateToIOException(WindowsException.java:85)
	at java.base/sun.nio.fs.WindowsException.rethrowAsIOException(WindowsException.java:103)
	at java.base/sun.nio.fs.WindowsException.rethrowAsIOException(WindowsException.java:108)
	at java.base/sun.nio.fs.WindowsFileSystemProvider.implDelete(WindowsFileSystemProvider.java:275)
	at java.base/sun.nio.fs.AbstractFileSystemProvider.delete(AbstractFileSystemProvider.java:105)
	at java.base/java.nio.file.Files.delete(Files.java:1152)
	at bloop.Compiler$$anon$2.$anonfun$trigger$10(Compiler.scala:548)
	at bloop.Compiler$$anon$2.$anonfun$trigger$10$adapted(Compiler.scala:540)
	at scala.collection.mutable.HashSet.foreach(HashSet.scala:79)
	at bloop.Compiler$$anon$2.$anonfun$trigger$9(Compiler.scala:540)
	at scala.runtime.java8.JFunction0$mcV$sp.apply(JFunction0$mcV$sp.java:23)
	at monix.eval.internal.TaskRunLoop$.startFull(TaskRunLoop.scala:81)
	at monix.eval.internal.TaskRestartCallback.syncOnSuccess(TaskRestartCallback.scala:101)
	at monix.eval.internal.TaskRestartCallback.onSuccess(TaskRestartCallback.scala:74)
	at monix.eval.internal.TaskShift$Register$$anon$1.run(TaskShift.scala:65)
	at monix.execution.internal.InterceptRunnable.run(InterceptRunnable.scala:27)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
	at java.base/java.lang.Thread.run(Thread.java:833)
#### Short summary: 

Unexpected error when compiling bestgame: java.nio.file.NoSuchFileException: <WORKSPACE>\.bloop\best...