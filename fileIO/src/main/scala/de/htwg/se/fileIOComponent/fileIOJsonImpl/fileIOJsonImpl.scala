package de.htwg.se.fileIOComponent.fileIOJsonImpl

import java.io.PrintWriter
import scala.io.Source
import java.io.File

object fileIOJsonImpl {
  def save(json: String): Unit = {
    val pw = PrintWriter(File("game.json"))
    pw.write(json)
    pw.close

  }
  def load(): String = {

    val source: String = Source.fromFile("game.json").getLines.mkString
    source
  }
}
