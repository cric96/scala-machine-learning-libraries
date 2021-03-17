package it.unibo
import smile.data.formula._
import smile.read
import smile.regression._

import java.nio.file.Path

object RegressionTest extends App {
  val airplanes =
    read.arff(Path.of(getClass.getResource("/2dplanes.arff").toURI))
  val model = lm("y" ~, airplanes)
  println(model)
  println("###### Prediction: ########")
  println(model.predict(airplanes.get(0)))
  println("###### True value: ########")
  println(airplanes.getFloat(0, "y"))
}
