//val ZIOVersion = "1.0.13"
val ZIOVersion = "1.0.13"
//val ZIOVersion = "2.0.0-RC2"
//val zhttpVersion     = "1.0.0.0-RC19"
val zhttpVersion     = "1.0.0.0-RC25"
//val zhttpVersion = "2.0.0-RC4"

lazy val root = project
  .in(file("."))
  .settings(
    name := "client-benchmark",
    organization := "bench",
    scalaVersion := "2.12.11",
//    scalaVersion := "2.13.0",
  )

addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt")
addCommandAlias(
  "check",
  "all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck"
)

libraryDependencies ++= Seq(
  // ZIO
  "dev.zio" %% "zio"          % ZIOVersion,
  "dev.zio" %% "zio-streams"  % ZIOVersion,
//  "dev.zio" %% "zio-test"     % ZIOVersion % "test",
//  "dev.zio" %% "zio-test-sbt" % ZIOVersion % "test",

  // zio-http
  "io.d11"                %% "zhttp"                          % zhttpVersion,

  // sttp
  "com.softwaremill.sttp.tapir" %% "tapir-zio-http-server" % "1.0.0-M1",
  //"com.softwaremill.sttp.tapir" %% "tapir-zio-http" % "1.0.0-M1",
  "com.softwaremill.sttp.client3" %% "core" % "3.5.1",
  "com.softwaremill.sttp.client3" %% "async-http-client-backend-zio1" % "3.5.1" // for ZIO 1.x,
)

testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework"))

scalacOptions in Compile in console := Seq(
  "-Ypartial-unification",
  "-language:higherKinds",
  "-language:existentials",
  "-Yno-adapted-args",
  "-Xsource:2.13",
  "-Yrepl-class-based",
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-explaintypes",
  "-Yrangepos",
  "-feature",
  "-Xfuture",
  "-unchecked",
  "-Xlint:_,-type-parameter-shadow",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-opt-warnings",
  "-Ywarn-extra-implicit",
  "-Ywarn-unused:_,imports",
  "-Ywarn-unused:imports",
  "-opt:l:inline",
  "-opt-inline-from:<source>",
  "-Ypartial-unification",
  "-Yno-adapted-args",
  "-Ywarn-inaccessible",
  "-Ywarn-infer-any",
  "-Ywarn-nullary-override",
  "-Ywarn-nullary-unit"
)