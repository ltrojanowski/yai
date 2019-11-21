package com.ltrojanowski.yai.sbt.plugin

import java.io.{FileInputStream, InputStreamReader}

//import com.ltrojanowski.yai.sbt.plugin.api.{Field, Schema, SchemaYamlFile}
import io.circe.generic.auto._
import io.circe.yaml.parser
import io.circe._
import Validators._
import scala.meta._
import _root_.io.circe

object YaiFilesReader {

  /*def fetchSchema(path: String): Either[Exception, Schema] = {
    val yamlFilePath = path

    val inputStreamReader = new InputStreamReader(
      new FileInputStream(yamlFilePath)
    )

    for {
      json       <- parser.parse(inputStreamReader)
      schemaFile <- json.as[SchemaYamlFile]
      schema     <- validateApiVersion(schemaFile)
    } yield schema
  }*/

}
