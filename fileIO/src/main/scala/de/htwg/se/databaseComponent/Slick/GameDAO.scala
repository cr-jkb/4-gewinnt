package de.htwg.se.databaseComponent

import slick.jdbc.PostgresProfile.api.*
import slick.jdbc.JdbcProfile

class GameDAO extends DAOInterface {
  val ip = "localhost"
  val port = "5432"
  val name = "fileio"
  val db = Database.forURL(
    url = "jdbc:postgresql://" + ip + ":" + port + "/" + name,
    user = "username",
    password = "password",
    driver = "org.postgresql.Driver"
  )
  override def create: Unit = {}
  override def read: String = {}
  override def update: Unit = {}
  override def delete: Unit = {}

}
