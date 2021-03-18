package it.unibo

import io.picnicml.doddlemodel.data.DatasetUtils.splitDataset
import io.picnicml.doddlemodel.data.loadBostonDataset
import io.picnicml.doddlemodel.linear.LinearRegression
import io.picnicml.doddlemodel.metrics.rmse
import io.picnicml.doddlemodel.syntax.RegressorSyntax._
/* taken from https://github.com/picnicml/doddle-model-examples/blob/master/src/main/scala/io/picnicml/doddlemodel/examples/linear/LinearRegressionExample.scala*/
object RegressionTest extends App {
  val (x, y, featureIndex) = loadBostonDataset
  println(s"features: $featureIndex")
  println(s"number of examples: ${x.rows}, number of features: ${x.cols}")

  val split = splitDataset(x, y)
  println(
    s"training set size: ${split.xTr.rows}, test set size: ${split.xTe.rows}"
  )

  // lambda is L2 regularization strength
  val model = LinearRegression(lambda = 1.5)
  val trainedModel = model.fit(split.xTr, split.yTr)

  val score = rmse(split.yTe, trainedModel.predict(split.xTe))
  println(f"test root mean squared error: $score%1.4f")
}
