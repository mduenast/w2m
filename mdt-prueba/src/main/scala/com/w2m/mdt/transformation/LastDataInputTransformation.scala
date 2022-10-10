package com.w2m.mdt.transformation

import org.apache.spark.sql.{Column, DataFrame, SparkSession, functions}

object LastDataInputTransformation {
  def apply()= {
    new LastDataInputTransformation
  }
}
class LastDataInputTransformation {

  def getLastDataInputBy(inputDF: DataFrame,maxCriteriaColumn: String, groupColumns: Seq[Column])(implicit sparkSession: SparkSession): DataFrame = {
    inputDF.groupBy(groupColumns: _*)
        .agg(functions.max(maxCriteriaColumn).as(maxCriteriaColumn))
  }

}
