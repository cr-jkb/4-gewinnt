//Interface for Game Mode Setting - will be used in Swap-In Classes @ /model/fieldComponent/______ModeStrategy.scala {2}
package de.htwg.se.model.fieldComponent

import de.htwg.se.model.fieldComponent.fieldElements.Game

trait GameModeInterface {

  def put(x: Int, y: Int, field: Game): (Game, Error)

  def setDifficulty(diff: Int): Unit

}
