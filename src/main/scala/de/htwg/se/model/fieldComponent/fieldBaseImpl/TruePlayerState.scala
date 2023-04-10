// Implemented 1-Instance Class - Read FileName as PlayersTurn = true / false

package de.htwg.se.model.fieldComponent.fieldBaseImpl

import de.htwg.se.util.PlayerState

case class TruePlayerState() extends PlayerState { //Todo23 , evt reicht auch if else mit einfach unverändertem field. mit failure hätten wir field verloren.
  override def put(
      x: Int,
      y: Int,
      field: Field
  ): ErrorField /* (Field, Error) */ /* Try[Field] */ = { // to be called from field.player in Field.scala and ___ModeStrategy.scala{2}
    if (field.get(x, y) == Stone.Empty)
      field.player =
        FalsePlayerState() // hand ball over to the other player (to 'O')
      (
        new ErrorField(field.copy(field.matrix.replaceCell(x, y, Stone.X)), "")
        /* Error("") */
      ) // x is vertical
    else new ErrorField(field, "Field already taken")
  }
}
