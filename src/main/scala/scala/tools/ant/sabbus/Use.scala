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
package tools.ant
package sabbus

import java.io.File

import org.apache.tools.ant.util.{GlobPatternMapper, SourceFileScanner}

class Use extends ScalaMatchingTask {

  def setId(input: String): Unit = {
    id = Some(input)
  }

  def setSrcdir(input: File): Unit = {
    sourceDir = Some(input)
  }

  def setDestdir(input: File): Unit = {
    destinationDir = Some(input)
  }

  def setFailOnError(input: Boolean): Unit = {
    failOnError = input
  }

  private var id: Option[String] = None
  private var sourceDir: Option[File] = None
  private var destinationDir: Option[File] = None
  private var failOnError: Boolean = true

  override def execute(): Unit = {
    if (id.isEmpty) throw new IllegalStateException("Mandatory attribute 'id' is not set.")
    if (sourceDir.isEmpty) throw new IllegalStateException("Mandatory attribute 'srcdir' is not set.")
    val compiler = Compilers(id.get)
    if (!destinationDir.isEmpty) compiler.settings.d = destinationDir.get
    val mapper = new GlobPatternMapper()
    mapper.setTo("*.class")
    mapper.setFrom("*.scala")
    val includedFiles: Array[File] =
      new SourceFileScanner(this).restrict(
        getDirectoryScanner(sourceDir.get).getIncludedFiles,
        sourceDir.get,
        compiler.settings.d,
        mapper
      ) map (new File(sourceDir.get, _))
    if (includedFiles.length > 0)
      try {
        log("Compiling " + includedFiles.length + " file" + (if (includedFiles.length > 1) "s" else "") + " to " + compiler.settings.d.getAbsolutePath)
        val (errors, warnings) = compiler.compile(includedFiles)
        if (errors > 0)
          throw new RuntimeException(s"Compilation failed with $errors error${(if (errors > 1) "s" else "")}.")
        else if (warnings > 0)
          log("Compilation succeeded with " + warnings + " warning" + (if (warnings > 1) "s" else "") + ".")
      }
      catch {
        case CompilationFailure(msg, ex) =>
          ex.printStackTrace()
          val errorMsg =
            "Compilation failed because of an internal compiler error (" + msg + "); see the error output for details."
          if (failOnError) throw new RuntimeException(errorMsg) else log(errorMsg)
      }
  }

}
