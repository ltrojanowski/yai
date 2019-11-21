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
