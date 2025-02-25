/*
 * Scala (https://www.scala-lang.org)
 *
 * Copyright EPFL and Lightbend, Inc.
 *
 * Licensed under Apache License 2.0
 * (http://www.apache.org/licenses/LICENSE-2.0).
 *
 * See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership.
 */

package scala
package tools.ant.sabbus

import java.io.File
import org.apache.tools.ant.Task

class Make extends Task with TaskArgs {
  override def execute(): Unit = {
    if (id.isEmpty) throw new IllegalStateException("Mandatory attribute 'id' is not set.")
    if (compilerPath.isEmpty) throw new IllegalStateException("Mandatory attribute 'compilerpath' is not set.")
    val settings = new Settings
    if (destinationDir.isDefined) settings.d = destinationDir.get
    if (compTarget.isDefined) settings.target = compTarget.get
    if (compilationPath.isDefined) settings.classpath = compilationPath.get
    if (sourcePath.isDefined) settings.sourcepath = sourcePath.get
    settings.extraParams = extraArgsFlat
    Compilers.make(id.get, compilerPath.get.list.map{ path => new File(path).toURI.toURL }, settings)
  }
}
