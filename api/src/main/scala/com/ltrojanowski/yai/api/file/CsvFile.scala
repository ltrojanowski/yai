package com.ltrojanowski.yai.api.file

import com.ltrojanowski.yai.api.schema.CsvSchema
import com.ltrojanowski.yai.api.utils.RefOrValue

case class DataOwner(name: String, email: String)

case class CsvFile(
    `type`: String,
    filePrefix: String,
    description: String,
    estimatedArrivalTime: String,
    encoding: String,
    dataOwners: List[DataOwner],
    schema: CsvSchema
)
