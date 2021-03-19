package it.unibo

import me.shadaj.scalapy
import me.shadaj.scalapy.py.{PyQuote, `with`}
object DeepLearningTest extends App {
  val sklearn = scalapy.py.module("sklearn.model_selection")
  val np = scalapy.py.module("numpy")
  val tf = scalapy.py.module("tensorflow")
  val preprocessing = scalapy.py.module("sklearn.preprocessing")
  val dataset = scalapy.py.module("sklearn.datasets")
  val kerasUtils = scalapy.py.module("keras.utils")
  val iris = dataset.load_iris()
  val X = iris.data
  val y_numerical = iris.target
  val y = kerasUtils.to_categorical(y_numerical)
  val batch_size = 65

  `with`(tf.device("cpu:0")) { tf =>
    {
      val keras = scalapy.py.module("tensorflow.keras")
      val layers = scalapy.py.module("tensorflow.keras.layers")
      val input = py"${layers}.Input(shape = ($X.shape[1]))"
      val dense = py"${layers}.Dense(10, activation='relu')"
      val output = layers.Dense(3, activation = "softmax")

      val model = py"$keras.Sequential([$input, $dense, $output])"
      model.summary() //seems not to works
      py"${model}.compile(loss = 'categorical_crossentropy', optimizer = 'adam', metrics = 'accuracy')"
      val splits = sklearn.train_test_split(
        X,
        y,
        test_size = 0.25,
        random_state = 42,
        shuffle = true
      )
      val X_train = py"$splits[0]"
      val X_val = py"$splits[1]"
      val y_train = py"$splits[2]"
      val y_val = py"$splits[3]"

      val epoch_count = 200
      val batch_size = 2
      py"$model.fit($X_train, $y_train, validation_data = ($X_val, $y_val), epochs = ${epoch_count}, batch_size = ${batch_size})"
      println(model.predict(X_val))
    }
  }

}
