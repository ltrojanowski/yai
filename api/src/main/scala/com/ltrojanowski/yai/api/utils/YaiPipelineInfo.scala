package com.ltrojanowski.yai.api.utils

case class TeamMember(name: String, mail: String)

case class YaiPipelineInfo(
    yaiVersion: String,
    domain: String,
    teamMail: String,
    members: List[TeamMember]
) extends YaiVersion
