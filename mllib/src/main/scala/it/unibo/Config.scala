package it.unibo

import org.apache.spark.sql.SparkSession

object Config {
  def createSession(name: String, logLevel: String = "ERROR"): SparkSession = {
    val session = SparkSession.builder
      .appName(name)
      .master("local")
      .getOrCreate()
    session.sparkContext.setLogLevel("ERROR")
    session
  }
}
