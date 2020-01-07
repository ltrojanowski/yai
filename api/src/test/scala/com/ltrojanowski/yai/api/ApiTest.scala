package com.ltrojanowski.yai.api

import java.io.{FileInputStream, InputStreamReader}

import com.ltrojanowski.yai.api.file._
import com.ltrojanowski.yai.api.io.{HiveTable, IngestDirectory}
import com.ltrojanowski.yai.api.job.CopyFileToHiveJob
import com.ltrojanowski.yai.api.utils.CirceSerializers._
import com.ltrojanowski.yai.api.utils.{
  Ref,
  RefOrValue,
  TeamMember,
  Value,
  YaiPipelineInfo,
  YaiVersion,
  YaiVersionContext
}
import _root_.io.circe.Decoder
import _root_.io.circe.generic.extras.Configuration
import _root_.io.circe.yaml.parser
import com.ltrojanowski.yai.api.schema.{ColumnField, CsvSchema, NestedItem}
import org.scalatest._

import scala.util.Right

class ApiTest extends FlatSpec with Matchers {

  behavior of "correspondence of scala case class representation of api to yaml definitions"

  val YAI_VERSION: String = "0.1.0"

  def readFileAs[A: Decoder](path: String): Either[Exception, A] = {
    val inputStreamReader = new InputStreamReader(new FileInputStream(path))
    val json              = parser.parse(inputStreamReader)
    json.flatMap(_.as[A])
  }

  it should "correctly read a csv file schema" in {
    val path   = getClass.getResource("/file").getPath + "/CsvFile.yaml"
    val result = readFileAs[CsvFile](path)
    result shouldEqual Right(
      CsvFile(
        `type`               = "CsvFile",
        filePrefix           = "ASP_SAMPLE_FILE_{timestamp}",
        description          = "this is a sample csv file description",
        estimatedArrivalTime = "5 5 * * *",
        encoding             = "utf-8",
        dataOwners = List(
          DataOwner(name = "Jack Johnson", email     = "jack@example.com"),
          DataOwner(name = "Sample data team", email = "team@example.com")
        ),
        schema = Value(
          CsvSchema(
            columns = List(
              ColumnField(
                name        = "col1",
                `type`      = "string",
                description = Some("this is the first column. It is awesome")
              ),
              ColumnField(name = "col2", `type` = "integer", description = Some("some other description")),
              ColumnField(name = "col3", `type` = "number", nullable = true, format = Some("float")),
              ColumnField(name = "col4", `type` = "number", format = Some("double")),
              ColumnField(name = "col5", `type` = "integer", format = Some("int32")),
              ColumnField(name = "col6", `type` = "integer", format = Some("int64")),
              ColumnField(name = "col7", `type` = "boolean"),
              ColumnField(
                name   = "col8",
                `type` = "array",
                items  = Some(NestedItem(`type` = "integer", nullable = true, format = Some("int32")))
              )
            )
          )
        )
      )
    )
  }

  it should "correctly read a hive table definition" in {
    val path   = getClass.getResource("/io").getPath + "/HiveTable.yaml"
    val result = readFileAs[HiveTable](path)
    result shouldEqual Right(
      HiveTable(
        schema = "sample_schema",
        table  = "sample_table_name"
      )
    )
  }

  it should "correctly decode RefOrValue ref" in {
    val path   = getClass.getResource("/utils").getPath + "/YaiRef1.yaml"
    val result = readFileAs[RefOrValue[HiveTable]](path)
    result shouldEqual Right(
      Ref("asdf/asdf")
    )
  }

  it should "correctly decode RefOrValue value" in {
    val path   = getClass.getResource("/utils").getPath + "/YaiRef2.yaml"
    val result = readFileAs[RefOrValue[HiveTable]](path)
    result shouldEqual Right(
      Value(
        HiveTable(
          schema = "sample_schema",
          table  = "sample_table_name"
        )
      )
    )
  }

  it should "correctly decode YaiPipelineInfo" in {
    val path   = getClass.getResource("/utils").getPath + "/YaiPipelineInfo.yaml"
    val result = readFileAs[YaiPipelineInfo](path)
    result shouldEqual Right(
      YaiPipelineInfo(
        yaiVersion = "0.1.0",
        domain     = "stores",
        teamMail   = "asp@example.com",
        members = List(
          TeamMember(name = "Jan", mail  = "jan@example.com"),
          TeamMember(name = "Igor", mail = "igor@example.com")
        )
      )
    )
  }

  it should "correctly read a CopyFileToHiveJob using ref" in {
    val path   = getClass.getResource("/job").getPath + "/CopyFileToHiveJob.yaml"
    val result = readFileAs[CopyFileToHiveJob](path)
    result shouldEqual Right(
      CopyFileToHiveJob(
        name   = Some("asp-sample-file-copy-job"),
        input  = Ref("../io/IngestNode.yaml"),
        output = Ref("../io/HiveTable.yaml"),
        file   = Ref("../files/CsvFile.yaml")
      )
    )
  }

  it should "correctly read a CopyFileToHiveJob with embedded value" in {
    val path         = getClass.getResource("/job").getPath + "/CopyFileToHiveJobWithEmbeddedInput.yaml"
    val result       = readFileAs[CopyFileToHiveJob](path)
    implicit val foo = YaiVersionContext("0.1.0")
    result shouldEqual Right(
      CopyFileToHiveJob(
        name = Some("asp-sample-file-copy-job"),
        input = Value(
          IngestDirectory(
            `type`   = "IngestNode",
            filePath = "/home/svc-asp-dev/sample/",
            address  = "pphdpxx002.example.org",
            user     = "svc-asp-dev",
            file     = Ref("../file/CsvFile.yaml")
          )
        ),
        output = Ref("../io/HiveTable.yaml"),
        file   = Ref("../files/CsvFile.yaml")
      )
    )
  }

}
