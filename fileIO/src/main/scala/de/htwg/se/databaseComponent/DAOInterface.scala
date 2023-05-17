package de.htwg.se.databaseComponent

trait DAOInterface {
  def create: Int
  def read: String
  def update(jsField:String): Unit
  def delete: Unit
}
