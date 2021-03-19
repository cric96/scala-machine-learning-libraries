package it.unibo
import me.shadaj.scalapy._
import me.shadaj.scalapy.py.SeqConverters
object TestNumpy extends App {
  val np = py.module("numpy")
  val a = np.array(
    Seq(Seq(1, 0).toPythonProxy, Seq(0, 12).toPythonProxy).toPythonProxy
  )
  // a: py.Dynamic = [[ 1  0]
  //  [ 0 12]]
  val aSquared = np.matmul(a, a)
  println(aSquared)
}
