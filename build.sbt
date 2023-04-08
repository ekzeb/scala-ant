organization := "com.sandinh"
//organization := "org.scala-lang.modules"

name := "scala-ant"

version := "2.13.10"

scalaVersion := "2.13.10"

scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-feature")

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-compiler" % scalaVersion.value,
  "org.apache.commons" % "commons-compress" % "1.22",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "org.apache.ant" % "ant" % "1.10.13",
)
