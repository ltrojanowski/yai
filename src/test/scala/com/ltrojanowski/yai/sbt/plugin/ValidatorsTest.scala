package com.ltrojanowski.yai.sbt.plugin

import java.io.{FileInputStream, InputStreamReader}

import com.ltrojanowski.yai.api.utils.CirceSerializers._
import com.ltrojanowski.yai.api.utils.{TeamMember, YaiPipelineInfo}
import io.circe.yaml.parser
import org.scalatest.{FlatSpec, Matchers}

class ValidatorsTest extends FlatSpec with Matchers {

  it should "correctly validate if the version of the schema matches the plugin version" in {
    val csvFile           = getClass.getResource("/").getPath + "/utils/YaiPipelineInfo.yaml"
    val inputStreamReader = new InputStreamReader(new FileInputStream(csvFile))

    val json = parser.parse(inputStreamReader)

    val result = for {
      schema    <- json.flatMap(_.as[YaiPipelineInfo])
      validated <- Validators.validateApiVersion(schema)
    } yield validated
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
}
