package it.unibo

import io.picnicml.doddlemodel.data.loadIrisDataset
import io.picnicml.doddlemodel.data.DatasetUtils.{shuffleDataset, splitDataset}
import io.picnicml.doddlemodel.dummy.classification.UniformClassifier
import io.picnicml.doddlemodel.metrics.accuracy
import io.picnicml.doddlemodel.syntax.ClassifierSyntax._

import scala.util.Random
/* taken https://github.com/picnicml/doddle-model-examples/blob/master/src/main/scala/io/picnicml/doddlemodel/examples/dummy/UniformClassifierExample.scala */
object ClassificationTest extends App {
  implicit val rand: Random = new Random(42)
  val (features, target, featureIndex) = loadIrisDataset
  println(s"features: $featureIndex")

  val (x, y) = shuffleDataset(features, target)
  println(s"number of examples: ${x.rows}, number of features: ${x.cols}")

  val split = splitDataset(x, y)
  println(
    s"training set size: ${split.xTr.rows}, test set size: ${split.xTe.rows}"
  )

  val model = UniformClassifier()
  val trainedModel = model.fit(split.xTr, split.yTr)

  val score = accuracy(split.yTe, trainedModel.predict(split.xTe))
  println(f"test accuracy: $score%1.4f")
}
