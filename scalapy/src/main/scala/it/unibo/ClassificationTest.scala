package it.unibo

import me.shadaj.scalapy
import me.shadaj.scalapy.py.PyQuote
object ClassificationTest extends App {

  val sklearn = scalapy.py.module("sklearn.svm")
  val np = scalapy.py.module("numpy")
  val dataset = scalapy.py.module("sklearn.datasets")
  val boston = dataset.load_iris()
  val X = boston.data
  val y = boston.target
  val regression = sklearn.SVC()
  regression.fit(X, y)
  val predict = regression.predict(X)
  val errors = py"$predict == $y"
  println("errors = " + py"${errors}.shape[0] - ${np.count_nonzero(errors)}")
}
