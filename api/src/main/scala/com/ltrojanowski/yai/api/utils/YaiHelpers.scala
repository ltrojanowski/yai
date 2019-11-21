package com.ltrojanowski.yai.api.utils

import java.io.{FileInputStream, InputStreamReader}

import io.circe.Decoder
import io.circe.yaml.parser

trait YaiHelpers {

  def readFileAs[A: Decoder](path: String): Either[Exception, A] = {
    val inputStreamReader = new InputStreamReader(new FileInputStream(path))
    val json              = parser.parse(inputStreamReader)
    json.flatMap(_.as[A])
  }

}

object YaiHelpers extends YaiHelpers
