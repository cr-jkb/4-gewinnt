package de.htwg.se.databaseComponent

trait DAOInterface {
  def create: Int
  def read: Unit
  def update: Unit
  def delete: Unit
}
