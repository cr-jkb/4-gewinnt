package de.htwg.se.fileIOComponent.fileIOJsonImpl

import java.io.PrintWriter
import scala.io.Source
import java.io.File

/* import de.htwg.se.databaseComponent.slickFieldDAO */

object fileIOJsonImpl {
  var latestID: Int = -1;

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
