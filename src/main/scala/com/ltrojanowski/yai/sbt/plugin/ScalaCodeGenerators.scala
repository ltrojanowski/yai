package com.ltrojanowski.yai.sbt.plugin

import com.ltrojanowski.yai.api.schema.{BaseField, ColumnField}
import scala.meta.{Ctor, Defn, Mod, Name, Self, Template, Term, Type}

object ScalaCodeGenerators {

  def schemaToContract(fields: List[ColumnField]): Defn.Class = {
    val params: List[Term.Param] = fields.map(fieldToParameter)
    Defn.Class(
      List(Mod.Case()),
      Type.Name("Source"),
      Nil,
      Ctor.Primary(Nil, Name(""), List(params)),
      Template(Nil, Nil, Self(Name(""), None), Nil)
    )
  }

  def baseTypeToScalaType(`type`: String, nullable: Boolean, formatOpt: Option[String]) = {
    `type` match {
      case "number" => {
        val format = formatOpt.getOrElse("float")
        format match {
          case "float"  => Type.Name("Float")
          case "double" => Type.Name("Double")
        }
      }
      case "integer" => {
        val format = formatOpt.getOrElse("int32")
        format match {
          case "int8"  => Type.Name("Byte")
          case "int16" => Type.Name("Short")
          case "int32" => Type.Name("Int")
          case "int64" => Type.Name("Long")
        }
      }
      case "string"  => Type.Name("String")
      case "boolean" => Type.Name("Boolean")
    }
  }

  def fieldToParameter(field: ColumnField): Term.Param = {
    def scalaType(baseItem: BaseField, ignoreNullable: Boolean = false): Type = {
      if (baseItem.nullable && !ignoreNullable) {
        Type.Apply(Type.Name("Option"), List(scalaType(baseItem, ignoreNullable = true)))
      } else {
        baseItem.`type` match {
          case "number" => {
            val format = baseItem.format.getOrElse("float")
            format match {
              case "float"  => Type.Name("Float")
              case "double" => Type.Name("Double")
            }
          }
          case "integer" => {
            val format = baseItem.format.getOrElse("int32")
            format match {
              case "int8"  => Type.Name("Byte")
              case "int16" => Type.Name("Short")
              case "int32" => Type.Name("Int")
              case "int64" => Type.Name("Long")
            }
          }
          case "string"  => Type.Name("String")
          case "boolean" => Type.Name("Boolean")
          case "array"   => Type.Apply(Type.Name("Array"), baseItem.items.map(scalaType(_)).toList)
          case "object"  => ???
        }
      }
    }
    Term.Param(Nil, Term.Name(field.name), Some(scalaType(field)), None)
  }

}
