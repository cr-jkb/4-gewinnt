package de.htwg.se
package fieldComponent.fieldBaseImpl

import de.htwg.se.tools.GameMode
import de.htwg.se.fieldComponent.FieldInterface
import scala.compiletime.ops.boolean
import scala.collection.immutable.HashMap
import scala.collection.immutable.ListSet
import de.htwg.se.WinDetectorComponent.WinDetectorInterface
import de.htwg.se.WinDetectorComponent.WinDetectorComponent_Impl.WinAlert

case class PlayerModeStrategy() extends GameMode {
  val winCheck: WinDetectorInterface = WinAlert()
  override def put(x: Int, y: Int, field: Field): ErrorField = {
    val mF = field.player.put(
      x,
      y,
      field
    ) // hand parameters through to the next layer (which is player)
    val won = winCheck.checkWin(
      mF.field
    ) // destroys field somehow (not handed through afterwards) probably because of the warning
    mF
  }

  override def setDifficulty(diff: Int) = {
    println(
      "Sorry, you need to be in SinglePlayer Mode to set the difficulty. \n(Enter in TUI: singleplayer)"
    )
  } // not needed
}
