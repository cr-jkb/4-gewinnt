package model.fieldComponent

import model.fieldComponent.fieldBaseImpl.Stone
import model.fieldComponent.fieldBaseImpl.Field
import util.ModeStrategy
trait FieldInterface {
  def size: Int
  def size2: Int
  def put(x: Int, y: Int): Field
  def get(x: Int, y: Int): Stone
  def getPlayerState(): Boolean
  def setMode(str: String): ModeStrategy
  def undo(x: Int, y: Int): FieldInterface
}