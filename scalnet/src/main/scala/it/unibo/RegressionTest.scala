package it.unibo

import org.datavec.api.records.reader.impl.csv.CSVRecordReader
import org.datavec.api.split.FileSplit
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator
import org.deeplearning4j.datasets.iterator.impl.ListDataSetIterator
import org.deeplearning4j.optimize.listeners.ScoreIterationListener
import org.deeplearning4j.scalnet.layers.core.Dense
import org.deeplearning4j.scalnet.logging.Logging
import org.deeplearning4j.scalnet.models.NeuralNet
import org.nd4j.linalg.activations.Activation
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator
import org.nd4j.linalg.dataset.{DataSet, SplitTestAndTrain}
import org.nd4j.linalg.io.ClassPathResource
import org.nd4j.linalg.lossfunctions.LossFunctions.LossFunction

import java.util

object RegressionTest extends App with Logging {
  val numLinesToSkip = 1
  val delimiter = ','
  val labelIndex = 4
  val batchSize = 50
  val hiddenSize = 128
  val inputSize = 4
  val outputSize = 1
  val epochs = 1000
  val scoreFrequency = 5
  val seed = 42

  logger.info("Reading data set...")
  val recordReader = new CSVRecordReader(numLinesToSkip, delimiter)
  recordReader.initialize(
    new FileSplit(new ClassPathResource("visualizing_soil.csv").getFile)
  )
  val iterator: DataSetIterator = new RecordReaderDataSetIterator(
    recordReader,
    batchSize,
    labelIndex,
    outputSize
  )

  logger.info("Prepare data set for training...")
  val next: DataSet = iterator.next()
  next.shuffle()
  val testAndTrain: SplitTestAndTrain = next.splitTestAndTrain(0.75)
  val testData: DataSet = testAndTrain.getTest
  val trainingList: util.List[DataSet] = testAndTrain.getTrain.asList()
  val trainingData = new ListDataSetIterator(trainingList, trainingList.size)

  logger.info("Build model...")
  val model: NeuralNet = NeuralNet(rngSeed = seed)
  model.add(
    Dense(nIn = inputSize, nOut = hiddenSize, activation = Activation.RELU)
  )
  model.add(Dense(nOut = outputSize))
  model.compile(LossFunction.MSE)

  logger.info("Train model...")
  model.fit(
    trainingData,
    epochs,
    List(new ScoreIterationListener(scoreFrequency))
  )
  val prediction = model.predict(testData)
  val realData = testData.getLabels
  val data = realData.addi(prediction.muli(-1))
  val dataPow = data.muli(data)
  val mse = dataPow.sumNumber()
  logger.info(s"MSE = ${mse}")
}
