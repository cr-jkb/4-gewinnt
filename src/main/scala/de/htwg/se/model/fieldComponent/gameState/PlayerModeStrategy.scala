package de.htwg.se.model.fieldComponent.gameState

import de.htwg.se.model.fieldComponent.GameModeInterface
import de.htwg.se.model.fieldComponent.fieldElements.Game
/*import de.htwg.se.model.fieldComponent.FieldInterface*/
import scala.compiletime.ops.boolean
import scala.collection.immutable.HashMap
import scala.collection.immutable.ListSet
import de.htwg.se.model.WinDetectorComponent.WinDetectorInterface
import de.htwg.se.model.WinDetectorComponent.WinAlert

case class PlayerModeStrategy() extends GameModeInterface {
  val winCheck: WinDetectorInterface = WinAlert()
  override def put(x: Int, y: Int, field: Game): (Game, Error) = {
    val mF = field.player.put(x,y,field)
    //error handling? TODO23
    // hand parameters through to the next layer (which is player)
    val won = winCheck.checkWin(mF._1)
    //destroys field somehow (not handed through afterwards) probably because of the warning
    (mF._1, "")
  }

  override def setDifficulty(diff: Int) = {
    println(
      "Sorry, you need to be in SinglePlayer Mode to set the difficulty. \n(Enter in TUI: singleplayer)"
    )
  } //not needed
}
