package com.w2m.mdt.reading

import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{DataFrame, SparkSession}

object JsonReader {
  def apply() = {
    new JsonReader
  }
}

class JsonReader extends Reader {
  override def read(from: String, schema: StructType)(implicit sparkSession: SparkSession): DataFrame = {
    sparkSession.read
      .schema(schema)
      .json(from)
  }
}
