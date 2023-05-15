package de.htwg.se.databaseComponent

import slick.jdbc.PostgresProfile.api.*
import slick.jdbc.JdbcProfile.*

class FieldTable(tag: Tag) extends Table[String](tag, "field") {

  def id = column[Int]("id", O.PrimaryKey)
  def jsonField = column[String]("jsonField")

  // default projection
  def * = (id, jsonField)

}
