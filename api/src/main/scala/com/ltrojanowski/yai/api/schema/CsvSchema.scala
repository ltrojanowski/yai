package com.ltrojanowski.yai.api.schema

trait BaseField {
  val `type`: String
  val nullable: Boolean
  val format: Option[String]
  val items: Option[BaseField]
}

case class NestedItem(
    `type`: String,
    nullable: Boolean         = false,
    format: Option[String]    = None,
    items: Option[NestedItem] = None
) extends BaseField

case class ColumnField(
    name: String,
    `type`: String,
    nullable: Boolean           = false,
    format: Option[String]      = None,
    description: Option[String] = None,
    items: Option[NestedItem]   = None
) extends BaseField

case class CsvSchema(columns: List[ColumnField])
