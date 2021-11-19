package controller

import model.Field
import model.Stone
import util.Observable

case class Controller(var field: Field) extends Observable:
  def this() = this(new Field())
  def put(stone: Stone, x: Int, y: Int) =
    field = field.put(stone, x, y)
    notifyObservers
  override def toString = field.toString
