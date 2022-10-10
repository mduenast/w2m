package com.w2m.mdt.writing
import org.apache.spark.sql.DataFrame

object TsvWriter{
  def apply() = {
    new TsvWriter
  }
}
class TsvWriter extends Writer {
  override def writeToTsv(outputDF: DataFrame, to: String): Unit = {
      outputDF.write
      .option("header", "true").
      option("delimiter", "\t").
      csv(to)
  }
}
