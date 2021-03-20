import ClassificationTest.{model, testData}
import Config.createSession
import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.sql.types.{
  DoubleType,
  IntegerType,
  StructField,
  StructType
}

import java.nio.file.Path

object RegressionTest extends App {
  val path =
    Path.of(getClass.getResource("/2dplanes.csv").toURI).toAbsolutePath.toString

  val features = 1 to 10 map { i =>
    s"x${i}"
  }

  val featuresLabel = "features"
  val label = "y"

  val fields = StructType(
    features.map(name => StructField(name, IntegerType)) ++ Seq(
      StructField(label, DoubleType)
    )
  )

  val spark = createSession("Regression")

  val assembler = new VectorAssembler()
    .setInputCols(features.toArray)
    .setOutputCol(featuresLabel)

  val df = assembler.transform(
    spark.read
      .option("header", value = true)
      .schema(fields)
      .csv(path)
  )

  val Array(trainingData, testData) = df.randomSplit(Array(0.8, 0.2))

  val lr = new LinearRegression()
    .setMaxIter(10)
    .setRegParam(0.3)
    .setElasticNetParam(0.8)
    .setFeaturesCol(featuresLabel)
    .setLabelCol(label)

  val model = lr.fit(trainingData)

  val predictions = model.transform(testData)
  predictions.show()

  // Select (prediction, true label) and compute test error.
  val evaluator = new RegressionEvaluator()
    .setLabelCol(label)
    .setPredictionCol("prediction")
    .setMetricName("rmse")

  val rmse = evaluator.evaluate(predictions)

  println(s"Root Mean Squared Error (RMSE) on test data = $rmse")
}
