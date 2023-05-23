package de.htwg.se.databaseComponent

object MongodbImpl {
  def load(): String = {
    val id = 1;
    mongoFieldDAO.read(id)
  }
  def save(jsonField: String) = {
    mongoFieldDAO.create(jsonField)
  }
}
