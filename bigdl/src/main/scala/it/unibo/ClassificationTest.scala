package it.unibo

import com.intel.analytics.bigdl.nn._
import com.intel.analytics.bigdl.numeric.NumericFloat
import com.intel.analytics.bigdl.utils._
import org.apache.spark.SparkContext
object ClassificationTest extends App {
  System.setProperty("bigdl.localMode", "true")
  System.setProperty("bigdl.coreNumber", "1")
  val conf = Engine
    .createSparkConf()
    .setMaster("local")
    .setAppName("Classification")

  val sc = new SparkContext(conf)
  Engine.init

  val state = T("learningRate" -> 0.05)
  val middleLevel = 20
  val maxEpoch = 15
  val input = 4
  val classNum = 3
  val model = Sequential()
    .add(Reshape(Array(input)))
    .add(Linear(input, middleLevel))
    .add(ReLU())
    .add(Linear(middleLevel, classNum).setName("fc2"))
    .add(LogSoftMax())

  model.evaluate()

  sc.cancelAllJobs()
}
