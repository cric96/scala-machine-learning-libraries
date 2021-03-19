package it.unibo

import me.shadaj.scalapy.py.module
//it seems not to work...
object MatplotlibTest extends App {
  val plt = module("matplotlib.pyplot")
  val np = module("numpy")
  val fig = plt.figure()
  val ax = plt.axes()
  val x = np.linspace(0, 10, 1000)
  ax.plot(x, np.sin(x))
}
