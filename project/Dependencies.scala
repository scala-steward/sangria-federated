import sbt._

object Dependencies {
  object V {
    val circe = "0.15.0-M1"
    val circeOptics = "0.14.1"
    val http4s = "1.0.0-M30"
  }

  val sangria = "org.sangria-graphql" %% "sangria" % "4.2.10"
  val sangriaCirce = "org.sangria-graphql" %% "sangria-circe" % "1.3.2"
  val logbackClassic = "ch.qos.logback" % "logback-classic" % "1.5.18"

  val catsEffect = "org.typelevel" %% "cats-effect" % "3.6.1"
  val http4sEmberServer = "org.http4s" %% "http4s-ember-server" % V.http4s
  val http4sCirce = "org.http4s" %% "http4s-circe" % V.http4s
  val http4sDsl = "org.http4s" %% "http4s-dsl" % V.http4s

  val circeCore = "io.circe" %% "circe-core" % V.circe
  val circeGeneric = "io.circe" %% "circe-generic" % V.circe
  val circeParser = "io.circe" %% "circe-parser" % V.circe

  val scalaTest = "org.scalatest" %% "scalatest" % "3.2.19"
  val scalapbRuntime =
    "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf"
  val weaver = "org.typelevel" %% "weaver-cats" % "0.9.1"
}
