// Implemented 1-Instance Class - Read FileName as PlayersTurn = true / false

package de.htwg.se.model.fieldComponent.fieldBaseImpl

import de.htwg.se.util.PlayerState
import scala.util.control.Breaks._

object SpaceFound extends Exception

case class TruePlayerState() extends PlayerState {
  override def put(x: Int, y: Int, field: Field): Field = { // to be called from field.player in Field.scala and ___ModeStrategy.scala{2}
    field.player = FalsePlayerState() // hand ball over to the other player (to 'O')
    var low_x = 0
    breakable {
      for (low_x <- (field.matrix.size-1 until 0)) { //gehe von unten los
        if (field.matrix.cell(low_x, y).toString.equals(" ")) break;
      }
     }
    field.copy(field.matrix.replaceCell(low_x, y, Stone.X))
  }
}