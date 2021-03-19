package it.unibo
import me.shadaj.scalapy.py
import me.shadaj.scalapy.py.SeqConverters
object TestPy extends App {
  val listLengthPython = py.Dynamic.global.len(List(1, 2, 3).toPythonProxy)
  println(listLengthPython)
}
