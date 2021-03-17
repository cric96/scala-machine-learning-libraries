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