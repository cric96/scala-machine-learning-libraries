package it.unibo
import me.shadaj.scalapy

object RegressionTest extends App {
  val sklearn = scalapy.py.module("sklearn.linear_model")
  val np = scalapy.py.module("numpy")
  val dataset = scalapy.py.module("sklearn.datasets")
  val boston = dataset.load_boston()
  val X = boston.data
  val y = boston.target
  val regression = sklearn.LinearRegression()
  regression.fit(X, y)
  val predict = regression.predict(X)
  println(predict - y) //error
}
