name := "common"
organization := "com.karthik.java"
version := "1.0"

libraryDependencies ++= Seq(
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.scalatest" %% "scalatest" % "3.2.2" % Test,
  "junit" % "junit" % "4.13.1" % Test
)