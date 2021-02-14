scalaVersion := "2.13.3"

name := "algorithmsp1"
organization := "com.karthik.java"
version := "1.0"

lazy val compilerOptions = Seq(
  "-unchecked",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-deprecation",
  "-encoding",
  "utf8"
)

lazy val commonSettings = Seq(
  scalacOptions ++= compilerOptions,
  EclipseKeys.withSource := true,
  resolvers ++= Seq(
    "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository",
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots")
  )
)

// PROJECTS

lazy val root = project
  .in(file("."))
  .settings(commonSettings)
  .aggregate(
    common,
    week1,
    week2,
    week3,
    week4
  )

lazy val common = project
  .settings(
    name := "common",
    commonSettings
  )

lazy val week1 = project
  .settings(
    name := "week1",
    commonSettings
  )
  .dependsOn(
    common % "test->test;compile->compile"
  )

lazy val week2 = project
  .settings(
    name := "week2",
    commonSettings
  )
  .dependsOn(
    common % "test->test;compile->compile"
  )  

lazy val week3 = project
  .settings(
    name := "week3",
    commonSettings
  )
  .dependsOn(
    common % "test->test;compile->compile"
  )  

lazy val week4 = project
  .settings(
    name := "week4",
    commonSettings
  )
  .dependsOn(
    common % "test->test;compile->compile"
  )    

