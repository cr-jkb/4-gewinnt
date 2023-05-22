package de.htwg.se.databaseComponent

trait DAOInterface {
  def create(jsonField: String): Int
  def read(id: Int): String
  def update(id: Int, jsonField: String): Unit
  def delete(id: Int): Unit
}
