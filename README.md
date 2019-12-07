# scala-ant
Ant support from scala, copied from original repository since it is abandoned in 2.13+

## Refs
+ [Scala plugin's scaladoc task broken with Scala 2.13.0](https://github.com/gradle/gradle/issues/9855)
+ [remove Ant support, remove Eclipse support](https://github.com/scala/scala/pull/6255)
+ [scala-2.13.0 & ant support](https://users.scala-lang.org/t/scala-2-13-0-ant-support/4694/11)

## gradle scaladoc fix
```groovy
configurations {
  scalaAnt
}
dependencies {
  scalaAnt 'com.sandinh:scala-ant_2.13:2.13.1'
}
scaladoc {
  scalaClasspath = scalaRuntime.inferScalaClasspath(configurations.compile) +
            layout.files(configurations.scalaAnt)
  // or simple: scalaClasspath = configurations.scalaAnt
}
```
