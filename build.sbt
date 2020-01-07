import sbt.{CrossVersion, Resolver}
import Dependencies._

lazy val commonSettings =
  Seq(
    organization := "com.ltrojanowski",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := "2.12.8"
  )

lazy val api = (project in file("api"))
  .settings(commonSettings, libraryDependencies ++= commonDeps)

lazy val yai = (project in file("."))
  .settings(
    name := "yai",
    sbtPlugin := true,
    commonSettings,
    libraryDependencies ++= commonDeps
  )
  .dependsOn(api)
  .aggregate(api)

lazy val docs = (project in file("docs"))
  .enablePlugins(MicrositesPlugin)
  .settings(
    micrositeName := "yai-toolkit",
    micrositeDescription := "YAI - yet another ingest",
    micrositeTwitterCreator := "@ltrojanowski",
    micrositeGithubOwner := "ltrojanowski",
    micrositeGithubRepo := "yai-docs",
    micrositeGitterChannel := false,
    micrositeHighlightLanguages ++= Seq("yaml")
  )
  .settings(name := "yai-docs", version := "0.1", scalaVersion := "2.12.8")
