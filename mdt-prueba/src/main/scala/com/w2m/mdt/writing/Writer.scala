package com.w2m.mdt.writing

import org.apache.spark.sql.DataFrame

trait Writer {
  def writeToTsv(outputDF: DataFrame, to: String): Unit

}
