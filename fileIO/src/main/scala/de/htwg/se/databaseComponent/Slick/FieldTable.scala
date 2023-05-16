package de.htwg.se.databaseComponent

import slick.jdbc.PostgresProfile.api.*

class FieldTable(tag: Tag) extends Table[String](tag, "field") {

  def id = column[Int]("id", O.PrimaryKey)
  def jsonField = column[String]("jsonField")

  // default projection
  override def * = (id, jsonField)

}
