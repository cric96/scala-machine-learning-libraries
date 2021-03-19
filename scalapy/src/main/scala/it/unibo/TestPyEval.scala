package it.unibo
import me.shadaj.scalapy.py
import me.shadaj.scalapy.py.{PyQuote, SeqConverters}
object TestPyEval extends App {
  val mappedList = py.Dynamic.global
    .list(py"map(lambda elem: elem + 1, ${Seq(1, 2, 3).toPythonProxy})")
  println(mappedList)
  println(py.eval("3 * 10"))
}
