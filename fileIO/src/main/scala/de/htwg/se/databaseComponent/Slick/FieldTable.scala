package de.htwg.se.databaseComponent

import slick.jdbc.PostgresProfile.api.*
import slick.lifted.Tag

class FieldTable(tag: Tag) extends Table[String](tag, "field") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def jsonField = column[String]("jsonField")

  // default projection
  override def * = (id, jsonField)

}
