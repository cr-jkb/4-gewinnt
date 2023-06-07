package de.htwg.se.databaseComponent

import slick.jdbc.PostgresProfile.api.*
import slick.lifted.Tag

class FieldTable(tag: Tag) extends Table[(Int, String)](tag, "field") {

  def id = column[Int]("id", O.PrimaryKey)
  def jsonField = column[String]("jsonField")

  // default projection
  override def * = (id, jsonField)

}
