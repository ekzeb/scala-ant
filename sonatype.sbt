publishMavenStyle := true

publishTo in Global := Some(
  if (isSnapshot.value)
    Opts.resolver.sonatypeSnapshots
  else
    Opts.resolver.sonatypeStaging
)

pomExtra in Global := <url>https://github.com/sandinh/scala-ant</url>
  <licenses>
    <license>
      <name>Apache 2</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>https://github.com/sandinh/scala-ant</url>
    <connection>scm:git:git@github.com:sandinh/scala-ant.git</connection>
  </scm>
  <developers>
    <developer>
      <id>cigaly</id>
      <organizationUrl>https://github.com/cigaly</organizationUrl>
    </developer>
    <developer>
      <id>giabao</id>
      <name>Bùi Việt Thành</name>
      <email>thanhbv@sandinh.net</email>
      <organization>Sân Đình</organization>
      <organizationUrl>https://sandinh.com</organizationUrl>
    </developer>
  </developers>
