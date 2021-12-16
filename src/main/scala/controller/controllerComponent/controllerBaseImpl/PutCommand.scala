package controller.controllerComponent.controllerBaseImpl

import model.fieldComponent.FieldInterface
import util.Command
import util.UndoManager

class PutCommand(x: Int, y: Int, controller: Controller) extends Command[FieldInterface]:
  override def noStep(field: FieldInterface): FieldInterface = controller.field
  override def doStep(field: FieldInterface): FieldInterface = controller.field.put(x, y)
  override def undoStep(field: FieldInterface): FieldInterface = controller.field.undo(x, y)
  override def redoStep(field: FieldInterface): FieldInterface = controller.field.put(x, y)