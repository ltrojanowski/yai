package com.ltrojanowski.yai.api.utils

import com.ltrojanowski.yai.api.file._
import com.ltrojanowski.yai.api.io.{HiveTable, IngestDirectory}
import com.ltrojanowski.yai.api.job.CopyFileToHiveJob
import com.ltrojanowski.yai.api.schema.{ColumnField, CsvSchema, NestedItem}
import io.circe.Decoder.Result
import io.circe.generic.extras.Configuration
import io.circe.{Decoder, Encoder, HCursor}
import io.circe.generic.extras.semiauto._

/*
  implicit def encodeEither[A, B](implicit
    encoderA: Encoder[A],
    encoderB: Encoder[B]
  ): Encoder[Either[A, B]] = {
    o: Either[A, B] =>
      o.fold(_.asJson, _.asJson)
  }

  implicit def decodeEither[A, B](implicit
    decoderA: Decoder[A],
    decoderB: Decoder[B]
  ): Decoder[Either[A, B]] = {
    c: HCursor =>
      c.as[A] match {
        case Right(a) => Right(Left(a))
        case _ => c.as[B].map(Right(_))
      }
  }
 */

object CirceSerializers {

  implicit val customConfig: Configuration                 = Configuration.default.withDefaults
  lazy implicit val nestedItemEncoder: Encoder[NestedItem] = deriveEncoder[NestedItem]
  lazy implicit val fieldEncoder: Encoder[ColumnField]     = deriveEncoder[ColumnField]
  lazy implicit val dataOwnerEncoder: Encoder[DataOwner]   = deriveEncoder[DataOwner]
  lazy implicit val csvSchemaEncoder: Encoder[CsvSchema]   = deriveEncoder[CsvSchema]
//  lazy implicit val csvFileEncoder: Encoder[CsvFile]       = deriveEncoder[CsvFile]
//  lazy implicit val hiveTableEncoder: Encoder[HiveTable]   = deriveEncoder[HiveTable]
//  lazy implicit val ingestNodeEncoder: Encoder[IngestNode] = deriveEncoder[IngestNode]
//  lazy implicit val copyFileToHiveJobEncoder: Encoder[CopyFileToHiveJob] = deriveEncoder[CopyFileToHiveJob]

  lazy implicit val nestedItemDecoder: Decoder[NestedItem]               = deriveDecoder[NestedItem]
  lazy implicit val fieldDecoder: Decoder[ColumnField]                   = deriveDecoder[ColumnField]
  lazy implicit val dataOwnerDecoder: Decoder[DataOwner]                 = deriveDecoder[DataOwner]
  lazy implicit val schemaDecoder: Decoder[CsvSchema]                    = deriveDecoder[CsvSchema]
  lazy implicit val csvFileDecoder: Decoder[CsvFile]                     = deriveDecoder[CsvFile]
  lazy implicit val hiveTableDecoder: Decoder[HiveTable]                 = deriveDecoder[HiveTable]
  lazy implicit val ingestNodeDecoder: Decoder[IngestDirectory]          = deriveDecoder[IngestDirectory]
  lazy implicit val copyFileToHiveJobDecoder: Decoder[CopyFileToHiveJob] = deriveDecoder[CopyFileToHiveJob]
  implicit def refDecoder[T]: Decoder[Ref[T]]                            = deriveDecoder[Ref[T]]
  implicit def valueDecoder[T: Decoder]: Decoder[Value[T]]               = deriveDecoder[Value[T]]
  implicit def refOrValueDecoder[T: Decoder]: Decoder[RefOrValue[T]] =
    refDecoder[T].map[RefOrValue[T]](identity).or(valueDecoder[T].map[RefOrValue[T]](identity))
  lazy implicit val teamMemberDecoder: Decoder[TeamMember]           = deriveDecoder[TeamMember]
  lazy implicit val yaiPipelineInfoDecoder: Decoder[YaiPipelineInfo] = deriveDecoder[YaiPipelineInfo]

//  implicit def withYaiVersionDecoder[T](implicit decoderT: Decoder[T]): Decoder[T with YaiVersion] =
//    deriveDecoder[T with YaiVersion]

}
