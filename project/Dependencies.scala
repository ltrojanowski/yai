import sbt._

object Dependencies {

  val scalameta = "org.scalameta" %% "scalameta" % "4.2.3"

  val circeYaml          = "io.circe" %% "circe-yaml"           % "0.11.0-M1"
  val circeGeneric       = "io.circe" %% "circe-generic"        % "0.12.2"
  val circeGenericExtras = "io.circe" %% "circe-generic-extras" % "0.12.2"

  val scalactic = "org.scalactic" %% "scalactic" % "3.0.8"
  val scalatest = "org.scalatest" %% "scalatest" % "3.0.8" % "test"

  val commonDeps: Seq[ModuleID] = Seq(scalameta, circeYaml, circeGeneric, circeGenericExtras, scalactic, scalatest)

}
