organization := "com.sandinh"

name := "scala-ant"

version := "2.13.1-SNAPSHOT"

scalaVersion := "2.13.1"

scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-feature")

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-compiler" % scalaVersion.value,
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "org.apache.ant" % "ant" % "1.9.14",
)
