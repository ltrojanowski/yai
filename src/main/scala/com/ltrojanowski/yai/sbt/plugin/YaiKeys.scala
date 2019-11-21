package com.ltrojanowski.yai.sbt.plugin

import sbt.taskKey

trait YaiKeys {

  lazy val generateSchema =
    taskKey[Unit]("generate case classes from yaml definition")

}
