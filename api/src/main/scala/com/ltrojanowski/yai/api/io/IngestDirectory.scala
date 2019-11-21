package com.ltrojanowski.yai.api.io

import com.ltrojanowski.yai.api.file.CsvFile
import com.ltrojanowski.yai.api.utils.RefOrValue

case class IngestDirectory(
    `type`: String,
    filePath: String,
    address: String,
    user: String,
    file: RefOrValue[CsvFile]
)
