package com.ltrojanowski.yai.sbt.plugin

import com.ltrojanowski.yai.api.utils.YaiVersion

object Validators {

  def validateApiVersion[T <: YaiVersion](
      versionedSchema: T
  ): Either[Exception, T] = {
    if (versionedSchema.yaiVersion == PluginInfo.API_VERSION) {
      Right(versionedSchema)
    } else {
      Left(new Exception("Plugin version doesn't match yai version"))
    }
  }

}
