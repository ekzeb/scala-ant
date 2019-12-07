//https://github.com/xerial/sbt-sonatype#project-rootsonatypesbt

publishMavenStyle := true

publishTo in Global := sonatypePublishToBundle.value

licenses := Seq("Apache 2" -> url("http://www.apache.org/licenses/LICENSE-2.0"))

import xerial.sbt.Sonatype._
sonatypeProjectHosting := Some(GitHubHosting("sandinh", "scala-ant", "thanhbv@sandinh.net"))

developers := List(
  Developer("cigaly", "Gabriel Birke", "gb@birke-software.de", url("http://birke-software.de")),
  Developer("giabao", "Bùi Việt Thành", "thanhbv@sandinh.net", url("https://sandinh.com")),
)
