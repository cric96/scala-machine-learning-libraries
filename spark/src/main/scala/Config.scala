import org.apache.spark.sql.SparkSession

object Config {
  def createSession(name: String): SparkSession =
    SparkSession.builder
      .appName(name)
      .master("local")
      .getOrCreate()

}
