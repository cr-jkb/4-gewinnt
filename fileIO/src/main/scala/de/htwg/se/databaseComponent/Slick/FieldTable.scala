package de.htwg.se.databaseComponent

import slick.jdbc.PostgresProfile.api.*

class FieldTable(tag: Tag) extends Table[(String)](tag, "field") {

  def name = column[String]("name", O.PrimaryKey)

  def * = (name)

}
