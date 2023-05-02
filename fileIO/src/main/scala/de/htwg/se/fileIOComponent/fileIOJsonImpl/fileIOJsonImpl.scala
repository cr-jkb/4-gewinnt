package de.htwg.se.fileIOComponent.fileIOJsonImpl

import java.io.PrintWriter
import scala.io.Source
import java.io.File

object fileIOJsonImpl {
  def save(json: String): Unit = {
    val pw = PrintWriter(File("res/game.json"))
    print("hallo")
    pw.write(json)
    pw.close
  }
  def load(): String = {
    val source: String = Source.fromFile("res/game.json").getLines.mkString
    source
  }
}
