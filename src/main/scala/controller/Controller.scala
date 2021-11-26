package controller

import model.Field
import model.Stone
import util.Observable

case class Controller(var field: Field) extends Observable:
  def this() = this(new Field())
  def put(x: Int, y: Int) =
    field = field.put(x, y)
    notifyObservers
  def setMode(str: String) = field.setMode(str)
  override def toString = field.toString