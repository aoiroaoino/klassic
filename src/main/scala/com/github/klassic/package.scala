package com.github

import java.io.{BufferedReader, FileInputStream, InputStreamReader}

import com.github.klassic.TypeDescription.{FunctionType, TypeVariable}

import scala.language.reflectiveCalls

/**
 * @author Kota Mizushima
 */
package object klassic {
  def openReader[A](fileName: String)(f: BufferedReader => A): A = {
    val reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"))
    using(reader)(f)
  }
  def using[A <: { def close(): Unit}, B](resource: A)(f: A => B): B = try {
    f(resource)
  } finally {
    scala.util.control.Exception.allCatch(resource.close())
  }

  val p = new Parser
  val t = new Typer
  val i = new Interpreter

  def typeOf(input: String): TypeDescription = {
    t.typeOf(p.parse(input).get)
  }

  def evaluate(input: String): Value = {
    i.evaluateString(input)
  }

  def tv(name: String): TypeVariable = TypeVariable(name)
}
