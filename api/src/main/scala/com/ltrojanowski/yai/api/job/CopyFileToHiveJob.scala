package com.ltrojanowski.yai.api.job

import com.ltrojanowski.yai.api.utils.RefOrValue
import com.ltrojanowski.yai.api.file.CsvFile
import com.ltrojanowski.yai.api.io.{HiveTable, IngestDirectory}
import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto._

case class CopyFileToHiveJob(
    name: Option[String],
    input: RefOrValue[IngestDirectory],
    output: RefOrValue[HiveTable],
    file: RefOrValue[CsvFile]
)
