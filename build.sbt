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
    week4,
    week5
  )

lazy val common = (project in file("common"))
  .settings(
    name := "common",
    commonSettings
  )

lazy val week1 = (project in file("week1"))
  .settings(
    name := "week1",
    commonSettings
  )
  .dependsOn(
    common % "test->test;compile->compile"
  )

lazy val week2 = (project in file("week2"))
  .settings(
    name := "week2",
    commonSettings
  )
  .dependsOn(
    common % "test->test;compile->compile"
  )  

lazy val week3 = (project in file("week3"))
  .settings(
    name := "week3",
    commonSettings
  )
  .dependsOn(
    common % "test->test;compile->compile"
  )  

lazy val week4 = (project in file("week4"))
  .settings(
    name := "week4",
    commonSettings
  )
  .dependsOn(
    common % "test->test;compile->compile"
  )    

lazy val week5 = (project in file("week5"))
  .settings(
    name := "week5",
    commonSettings
  )
  .dependsOn(
    common % "test->test;compile->compile"
  )    

  lazy val week6 = (project in file("week6"))
  .settings(
    name := "week6",
    commonSettings
  )
  .dependsOn(
    common % "test->test;compile->compile"
  ) 
   
  lazy val grind2 = (project in file("grind2"))
  .settings(
    name := "grind2",
    commonSettings
  )
  .dependsOn(
    common % "test->test;compile->compile"
  )    