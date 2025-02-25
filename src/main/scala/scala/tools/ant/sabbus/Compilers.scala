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

package scala.tools.ant.sabbus

import java.net.URL

object Compilers extends scala.collection.Map[String, Compiler] {

  val debug = false

  private val container = new scala.collection.mutable.HashMap[String, Compiler]

  def iterator: Iterator[(String, Compiler)] = container.iterator

  def get(id: String): Option[Compiler] = container.get(id)

  override def size: Int = container.size

  def make(id: String, classpath: Array[URL], settings: Settings): Compiler = {
    if (debug) println("Making compiler " + id)
    if (debug) println("  memory before: " + freeMemoryString)
    val comp = new Compiler(classpath, settings)
    container(id) = comp
    if (debug) println("  memory after: " + freeMemoryString)
    comp
  }

  def break(id: String): Null = {
    if (debug) println("Breaking compiler " + id)
    if (debug) println("  memory before: " + freeMemoryString)
    container -= id
    System.gc()
    if (debug) println("  memory after: " + freeMemoryString)
    null
  }

  private def freeMemoryString: String =
    (Runtime.getRuntime.freeMemory/1048576.0).formatted("%10.2f") + " MB"

  // These two methods are not in MapOps so that MapView is not forced to implement them
  @deprecated("Use -- or removedAll on an immutable Map", "2.13.0")
  def -(key1: String, key2: String, keys: String*): scala.collection.Map[String,scala.tools.ant.sabbus.Compiler] = ???
  @deprecated("Use - or removed on an immutable Map", "2.13.0")
  def -(key: String): scala.collection.Map[String,scala.tools.ant.sabbus.Compiler] = ???
}
