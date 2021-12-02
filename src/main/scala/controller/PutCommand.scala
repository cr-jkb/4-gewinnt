package controller

import model.Field
import util.Command
import util.UndoManager

class PutCommand(x: Int, y: Int) extends Command[Field]:
  override def noStep(field: Field): Field = field
  override def doStep(field: Field): Field = field.put(x, y)
  override def undoStep(field: Field): Field = field.undo(x, y)
  override def redoStep(field: Field): Field = field.put(x, y)