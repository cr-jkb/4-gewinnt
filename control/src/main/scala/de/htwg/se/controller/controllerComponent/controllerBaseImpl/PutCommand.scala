// Implements Command.scala Interface and uses UndoManager
// PutCommand -> Controller -> Command
package de.htwg.se
package controller.controllerComponent.controllerBaseImpl

import de.htwg.se.controller.controllerComponent.Command
import de.htwg.se.fieldComponent.FieldInterface

class PutCommand(x: Int, y: Int, controller: Controller)
    extends Command[FieldInterface]:
  override def noStep(field: FieldInterface): FieldInterface = controller.field
  override def doStep(field: FieldInterface): FieldInterface =
    controller.field.put(x, y)
  override def undoStep(field: FieldInterface): FieldInterface =
    controller.field.undo(x, y)
  override def redoStep(field: FieldInterface): FieldInterface =
    controller.field.put(x, y)
