package com.ltrojanowski.yai.sbt.plugin

import sbt.AutoPlugin
import sbt._

object YaiPlugin extends AutoPlugin {

  object autoImport extends YaiKeys
  import autoImport._

}
