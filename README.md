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
- hasn't a big activity on GitHub;
- doesn't support a lot of algorithm type;
- hasn't a plot support;
- doesn't support deep learning.

## [BIDData](https://github.com/BIDData/BIDMach) 
:no_entry_sign: In this repository there aren't examples about this framework.

## [sklearn](https://scikit-learn.org/stable/index.html) and [tensorflow](https://www.tensorflow.org/) leveraging [scalapy](https://scalapy.dev/)
This is a very interesting library by a performance point of view. Despite this,
it is hosted in a bintray repository, that will be dropped soon..