package com.ltrojanowski.yai.api.utils

case class YaiType(`type`: String)

trait YaiVersion {
  val yaiVersion: String
}

case class YaiVersionContext(yaiVersion: String) extends YaiVersion

sealed trait RefOrValue[+T]
case class Ref[+T](ref: String) extends RefOrValue[T]
case class Value[+T](value: T) extends RefOrValue[T]
