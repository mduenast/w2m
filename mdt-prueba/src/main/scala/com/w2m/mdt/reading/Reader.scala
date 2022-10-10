package com.w2m.mdt.reading

import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{DataFrame, SparkSession}

trait Reader {

  def read(from: String, schema: StructType)(implicit sparkSession: SparkSession): DataFrame

}
