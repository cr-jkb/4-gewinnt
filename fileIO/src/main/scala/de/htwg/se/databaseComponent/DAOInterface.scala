package de.htwg.se.databaseComponent

trait DAOInterface {
  def create: Unit
  def read: Unit
  def update: Unit
  def delete: Unit
}
