package it.unibo

import smile.classification._
import smile.data.formula._
import smile.plot._
import smile.plot.swing._
import smile.plot.Render.renderCanvas
import smile.read

import java.nio.file.Path
import scala.language.postfixOps

object ClassificationTest extends App {
  val formula: Formula = "class" ~
  val iris = read.arff(Path.of(getClass.getResource("/iris.arff").toURI))
  val labels = formula.y(iris).toStringArray
  val model = randomForest(formula, iris)
  println(model.metrics)
  println("####### Prediction #####")
  println(labels(model.predict(iris.get(0))))
  println("####### True value #####")
  println(iris.getString(0, "class"))
  val x = iris.select(0, 1).toArray
  val y: Array[Int] = iris.column("class").toStringArray.map(labels.indexOf)
  val result = plot(x, y, 'x')
  show(result)
}
