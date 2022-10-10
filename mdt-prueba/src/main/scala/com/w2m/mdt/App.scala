package com.w2m.mdt

import com.w2m.mdt.reading.{JsonReader, Reader}
import com.w2m.mdt.schemas.DataInput
import com.w2m.mdt.transformation.LastDataInputTransformation
import com.w2m.mdt.writing.{TsvWriter, Writer}
import org.apache.spark.sql.functions.{col, max}
import org.apache.spark.sql.{DataFrame, SparkSession}

import java.io.File

/**
 * @author mduenast
 */
object App {

  def main(args: Array[String]): Unit = {
    implicit val sparkSession: SparkSession = SparkSession.builder()
      .master("local[*]")
      .appName("MDT prueba")
      .getOrCreate();

    try {
      //val cwd = System.getProperty("user.dir")

      if(args.length != 2 ) {
        throw new IllegalArgumentException("Two input arguments are required.\n<path of the input file> <path of the destination file>.")
      }

      val inputFile: String = args(0)
        //s"${cwd}${File.separator}data-input.json";
      //classOf[App].getClassLoader.getResource("data-input.json").getPath
      val outPathFile = args(1)
      //s"${cwd}${File.separator}data-output"
      //classOf[App].getClassLoader.getResource("") + "data-output"

      println(inputFile)

      val reader: Reader = JsonReader()

      // read data
      val inputDataDF: DataFrame = reader.read(inputFile, DataInput.dataInputSchema)

      // transform data
      val lastDataInputTransformation: LastDataInputTransformation = LastDataInputTransformation()

      val lastInputData: DataFrame =
        lastDataInputTransformation.getLastDataInputBy(inputDataDF,
          DataInput.DATE,
          Seq(col(DataInput.ID), col(DataInput.BRAND)))

      // write data
      val tsvWriter: Writer = TsvWriter()

      lastInputData.show(false)

      tsvWriter.writeToTsv(lastInputData, outPathFile)
    } catch {
      case e: Exception => {
        println(e.getMessage)
      }
    } finally {
      sparkSession.stop()
    }
  }

}
