package com.w2m.mdt.schemas

import org.apache.spark.sql.types.{DateType, StringType, StructField, StructType}

object DataInput{

  val ID = "id"
  val VALUE = "value"
  val BRAND = "brand"
  val DATE = "date"

  val dataInputSchema: StructType = StructType(
    Seq(
      StructField(ID, StringType, nullable = false),
      StructField(VALUE, StringType, nullable = true),
      StructField(BRAND, StringType, nullable = false),
      StructField(DATE, DateType, nullable = true)
    )
  )
}
