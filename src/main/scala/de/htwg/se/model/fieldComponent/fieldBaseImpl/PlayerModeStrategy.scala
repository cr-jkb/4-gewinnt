package de.htwg.se.model.fieldComponent.fieldBaseImpl

import de.htwg.se.util.ModeStrategy
import de.htwg.se.model.fieldComponent.FieldInterface
import scala.compiletime.ops.boolean
import scala.collection.immutable.HashMap
import scala.collection.immutable.ListSet
import de.htwg.se.model.WinDetectorComponent.WinDetectorInterface
import de.htwg.se.model.WinDetectorComponent.WinAlert

case class PlayerModeStrategy() extends ModeStrategy {
  val winCheck : WinDetectorInterface = WinAlert()
  override def put(x: Int, y: Int, field: Field): ErrorField = {
    val mF = field.player.put(x, y, field) // hand parameters through to the next layer (which is player)
    val won = winCheck.checkWin(mF.returnField) //destroys field somehow (not handed through afterwards) probably because of the warning
    mF
  }

  override def setDifficulty(diff: Int) = { println("Sorry, you need to be in SinglePlayer Mode to set the difficulty. \n(Enter in TUI: singleplayer)") } //not needed
}