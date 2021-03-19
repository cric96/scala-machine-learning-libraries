import Config._
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{StringIndexer, VectorAssembler}
import org.apache.spark.sql.types.{
  DoubleType,
  StringType,
  StructField,
  StructType
}

import java.nio.file.Path
object ClassificationTest extends App {
  val path =
    Path.of(getClass.getResource("/iris.csv").toURI).toAbsolutePath.toString
  val (sepalLength, sepalWidth, petalLength, petalWidth) =
    ("sepal_length", "sepal_width", "petal_length", "petal_width")

  val label = "species"
  val target = "label"
  val features = "features"

  val schemaStruct = StructType(
    StructField("sepal_length", DoubleType) ::
      StructField("sepal_width", DoubleType) ::
      StructField("petal_length", DoubleType) ::
      StructField("petal_width", DoubleType) ::
      StructField("species", StringType) :: Nil
  )
  val spark = createSession("Classification")

  val assembler = new VectorAssembler()
    .setInputCols(Array(sepalLength, sepalWidth, petalLength, petalWidth))
    .setOutputCol(features)

  val df = assembler.transform(
    spark.read
      .option("header", value = true)
      .schema(schemaStruct)
      .csv(path)
  )

  val indexer = new StringIndexer()
    .setInputCol(label)
    .setOutputCol(target)

  val indexed = indexer.fit(df).transform(df)

  indexed.show()

  val Array(trainingData, testData) = indexed.randomSplit(Array(0.8, 0.2))

  val lr = new LogisticRegression()
    .setMaxIter(10)
    .setRegParam(0.3)
    .setElasticNetParam(0.8)
    .setLabelCol(target)
    .setFeaturesCol(features)

  val model = lr.fit(trainingData)

  val summary = model.summary
  println(summary.objectiveHistory.toList)
  println("accuracy = " + summary.accuracy)

  val predictions = model.transform(testData)
  predictions.show()

  // Select (prediction, true label) and compute test error.
  val evaluator = new MulticlassClassificationEvaluator()
    .setLabelCol(target)
    .setPredictionCol("prediction")
    .setMetricName("accuracy")

  val accuracy = evaluator.evaluate(predictions)
  println(s"Test Error = ${(1.0 - accuracy)}")
}
