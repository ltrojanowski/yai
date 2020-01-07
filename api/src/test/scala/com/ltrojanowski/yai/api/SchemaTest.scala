package com.ltrojanowski.yai.api

import java.io.{FileInputStream, InputStreamReader}

import com.ltrojanowski.yai.api.utils.YaiHelpers
//import com.ltrojanowski.yai.sbt.plugin.api.file.CsvFile
//import com.ltrojanowski.yai.sbt.plugin.api.utils.YaiHelpers
//import com.ltrojanowski.yai.sbt.plugin.api.utils.CirceSerializers._

import scala.util.Right
import org.scalatest._

import scala.meta._

class SchemaTest extends FlatSpec with Matchers with YaiHelpers {

  /*val yamlFile = getClass.getResource("/").getPath + "/schema.yaml"

  it should "parse yaml file" in {

    val inputStreamReader = new InputStreamReader(new FileInputStream(yamlFile))

    val json   = parser.parse(inputStreamReader)
    val schema = json.map(_.as[SchemaYamlFile])

    println(schema)

  }*/

  /*
  it should "foo bar" in {
    val sampleTree: Source = """case class Source(
      |  a: Int,
      |  b: String,
      |  c: Double,
      |  d: Option[Long],
      |  e: Float,
      |  f: Option[Short],
      |  g: BigDecimal,
      |  h: BigInt
      |)
      |""".stripMargin.parse[Source].get

    println(sampleTree.structure)
  } */

  /* it should "get types" in {
    val csvFile           = getClass.getResource("/").getPath + "/utils/CsvFile.yaml"
    val inputStreamReader = new InputStreamReader(new FileInputStream(csvFile))

    val json   = parser.parse(inputStreamReader)
    val schema = json.map(_.as[YaiType])

    println(schema)
  }*/
}
