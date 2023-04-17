package util

import de.htwg.se.util.Command

trait Command[T]: // Interface to be implemented in /controller/PutCommand.scala
  def noStep(t: T): T
  def doStep(t: T): T
  def undoStep(t: T): T
  def redoStep(t: T): T

class UndoManager[T]: // Implemented Class as Object
  private var undoStack: List[Command[T]] = Nil
  private var redoStack: List[Command[T]] = Nil
  def doStep(t: T, command: Command[T]) =
    undoStack = command :: undoStack // '::' nimmt den command vom UndoStack
    command.doStep(t)
  def undoStep(t: T): T =
    undoStack match {
      case Nil => t
      case head :: stack => {
        val result = head.undoStep(t)
        undoStack = stack
        redoStack = head :: redoStack
        result
      }
    }
  def redoStep(t: T): T = //same as above but on the redoStack
    redoStack match {
      case Nil => t
      case head :: stack => {
        val result = head.redoStep(t)
        redoStack = stack
        undoStack = head :: undoStack
        result
      }
    }
  def clearRedo() = 
    redoStack = Nil