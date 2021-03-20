# Scala ML libraries
In this repository, there are some minimal examples of ML scala libraries.
Each library is contained in an sbt subproject having the same name as the library.
## [smile](https://github.com/haifengl/smile)
A good alternative to sklearn. It is written in Java but has a scala DSL facade that makes the development easier.

**pro**:
- a good API;
- a vast number of algorithm about classification, regression, feature extraction and so on;
- a good performance;
- a clean design;
- modest plot support (also with vega);

**cons**:
- currently don't have a deep learning support;

## [doddle-model](https://github.com/picnicml/doddle-model)
A functional library that leverages type classes to implement some relevant ml algorithm (e.g. classification, regression, clustering).

**pro**
- a functional design;
- decent performance (comparable with sklearn);
- a good api;

**cons** 
- it hasn't a big activity on GitHub;
- it doesn't support a lot of algorithms;
- it hasn't a plot support;
- it doesn't support deep learning.

## [BIDData](https://github.com/BIDData/BIDMach) 
:no_entry_sign: In this repository there aren't examples about this framework.

This is a very interesting library from a performance point of view. Despite this,
it is hosted in a bintray repository, that will be dropped soon..

## [scikit-learn](https://scikit-learn.org/stable/index.html), [TensorFlow](https://www.tensorflow.org/), and [Keras](https://keras.io/) leveraging [ScalaPy](https://scalapy.dev/)

ScalaPy is an interesting project.
It allows the usage of Python libraries via Scala with an ad-hoc syntax. 
I have integrated Scala code with Keras and Sklearn almost without problems.
There are some facades (like NumPy and TensorFlow), 
but they seem to be deprecated or not updated.

**pro**
- you can access the python ecosystem quite agile;
- you can leverage scala.native to improve the performance;
- it is possible to use python libraries with scala typing support.

**cons**
- it is still quite immature;
- there aren't a good support from the community (few and poor typing implemented);
- the documentation is quite limited;
- you need to implement you own typing if there aren't implement;
- matplotlib seems not to work.

## [MLlib](https://spark.apache.org/docs/latest/ml-guide.html) (Spark based)

MLlib is Spark’s machine learning (ML) library. Its goal is to make practical machine learning scalable and easy. At a high level, it provides tools such as:

- ML Algorithms: common learning algorithms such as classification, regression, clustering, and collaborative filtering
- Featurization: feature extraction, transformation, dimensionality reduction, and selection
- Pipelines: tools for constructing, evaluating, and tuning ML Pipelines
- Persistence: saving and load algorithms, models, and Pipelines
- Utilities: linear algebra, statistics, data handling, etc.

from https://spark.apache.org/docs/latest/ml-guide.html

The spark documentation is very detailed, please refer to that. 

**pro**
- it has a lot of ml algorithms implemented;
- it has a beautiful documentation;
- it supports cluster computation;
- the API is quite simple.
- it is a well-supported framework; 

**cons**
- it could be an overkill (because you don't interested in scale up);
- the performance is not good as other alternatives;
- it requires more code than other solution;
- it hasn't DL support (but DL-Lib seems a solution).

## [BigDL](https://github.com/intel-analytics/BigDL) (Spark based)
BigDL is a distributed deep learning library for Apache Spark; 
with BigDL, users can write their deep learning applications as 
standard Spark programs, which can directly run on top of 
existing Spark or Hadoop clusters.

from https://bigdl-project.github.io/master/