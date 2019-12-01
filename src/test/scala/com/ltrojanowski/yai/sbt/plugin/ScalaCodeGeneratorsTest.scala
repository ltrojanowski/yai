package com.ltrojanowski.yai.sbt.plugin

import com.ltrojanowski.yai.api.file.CsvFile
import com.ltrojanowski.yai.api.utils.{Ref, Value, YaiHelpers}
import org.scalatest.{FlatSpec, Matchers}
import com.ltrojanowski.yai.api.utils.CirceSerializers._

import scala.util.Right

class ScalaCodeGeneratorsTest extends FlatSpec with Matchers with YaiHelpers {

  behavior of "scala contract code generators"

  it should "convert file to scala case class" in {
    val path = getClass.getResource("/file").getPath + "/CsvFile.yaml"
    val result = for {
      csvFile <- readFileAs[CsvFile](path)
      columns = csvFile.schema match {
        case Ref(columns)     => ???
        case Value(csvSchema) => csvSchema.columns
      }
      code = ScalaCodeGenerators.schemaToContract(columns).syntax
    } yield code

    result shouldEqual Right(
      """case class Source(col1: String, col2: Int, col3: Option[Float], col4: Double, col5: Int, col6: Long, col7: Boolean, col8: Array[Option[Int]])"""
    )
  }

}
