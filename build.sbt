name := "scala-machine-learning-libraries"

version := "0.1"

scalaVersion := "2.13.5"

idePackagePrefix := Some("it.unibo")

lazy val smile = project
  .in(file("smile"))
  .settings(
    libraryDependencies ++= Seq(
      "com.github.haifengl" %% "smile-scala" % "2.6.0",
      //for low level api
      "org.bytedeco" % "javacpp" % "1.5.4" classifier "macosx-x86_64" classifier "windows-x86_64" classifier "linux-x86_64" classifier "linux-arm64" classifier "linux-ppc64le" classifier "android-arm64" classifier "ios-arm64",
      "org.bytedeco" % "openblas" % "0.3.10-1.5.4" classifier "macosx-x86_64" classifier "windows-x86_64" classifier "linux-x86_64" classifier "linux-arm64" classifier "linux-ppc64le" classifier "android-arm64" classifier "ios-arm64",
      "org.bytedeco" % "arpack-ng" % "3.7.0-1.5.4" classifier "macosx-x86_64" classifier "windows-x86_64" classifier "linux-x86_64" classifier "linux-arm64" classifier "linux-ppc64le"
    )
  )

lazy val doddle = project
  .in(file("doodle"))
  .settings(
    libraryDependencies ++= Seq(
      "io.github.picnicml" %% "doddle-model" % "0.0.1-beta5",
      // add optionally to utilize native libraries for a significant performance boost
      "org.scalanlp" %% "breeze-natives" % "1.0"
    )
  )
//from https://scalapy.dev/
import scala.sys.process._
lazy val pythonLdFlags = {
  val withoutEmbed = "python3-config --ldflags".!!
  if (withoutEmbed.contains("-lpython")) {
    withoutEmbed.split(' ').map(_.trim).filter(_.nonEmpty).toSeq
  } else {
    val withEmbed = "python3-config --ldflags --embed".!!
    withEmbed.split(' ').map(_.trim).filter(_.nonEmpty).toSeq
  }
}

lazy val pythonLibsDir = { //for python
  pythonLdFlags.find(_.startsWith("-L")).get.drop("-L".length)
}

lazy val scalapy = project
  .in(file("scalapy"))
  .settings(
    libraryDependencies += "me.shadaj" %% "scalapy-core" % "0.4.2",
    libraryDependencies += "me.shadaj" %% "scalapy-numpy" % "0.1.0",
    fork := true,
    javaOptions += s"-Djna.library.path=$pythonLibsDir"
  )

lazy val mllib = project
  .in(file("mllib"))
  .enablePlugins(SparkPlugin)
  .settings(sparkComponents ++= Seq("core", "sql", "mllib"))

lazy val scalanet = project
  .in(file("scalanet"))
  .settings(
    scalaVersion := "2.11.12",
    libraryDependencies ++= Seq(
      "org.deeplearning4j" %% "scalnet" % "1.0.0-beta",
      "org.nd4j" % "nd4j-native-platform" % "1.0.0-beta" //for native mathematical operation
    )
  )
lazy val root = project
  .in(file("."))
  .aggregate(smile, doddle, scalapy, mllib, scalanet)
