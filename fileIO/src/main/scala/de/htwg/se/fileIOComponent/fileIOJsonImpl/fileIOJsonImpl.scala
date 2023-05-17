package de.htwg.se.fileIOComponent.fileIOJsonImpl

import java.io.PrintWriter
import scala.io.Source
import java.io.File

import de.htwg.se.databaseComponent.fieldDAO

object fileIOJsonImpl {
  var latestID : Int = -1;

  def save(json: String): Unit = {
    val pw = PrintWriter(File("game.json"))
    pw.write(json)
    latestID = fieldDAO.create(json);
    pw.close

  }
  def load(): String = {
    fieldDAO.read(latestID)
    val source: String = Source.fromFile("game.json").getLines.mkString
    source
  }
}
