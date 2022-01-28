package de.htwg.se.model.fieldComponent.fieldBaseImpl

import de.htwg.se.util.ModeStrategy
import de.htwg.se.model.fieldComponent.FieldInterface
import de.htwg.se.model.fieldComponent.fieldBaseImpl.difficulties.Strategy
import de.htwg.se.model.fieldComponent.fieldBaseImpl.difficulties.easyStrategy
import de.htwg.se.model.fieldComponent.fieldBaseImpl.difficulties.mediumStrategy
import de.htwg.se.model.fieldComponent.fieldBaseImpl.difficulties.difficultStrategy

case class ComputerModeStrategy() extends ModeStrategy {
  var myDifficulty: Strategy = easyStrategy()

  override def put(x: Int, y: Int, field: Field): ErrorField = {
    var field2 = field.player.put(x, y, field)
    if (y + 1 <= field.size2 - 1)
      var CompPos = myDifficulty.put(field2.field)
      field2.field.player.put(CompPos._1, CompPos._2, field2.field)
    else
      field2
  }

  def setDifficulty(diff: Int) = {
    diff match
      case 0 => myDifficulty = easyStrategy()
      case 1 => myDifficulty = mediumStrategy()
      case 2 => myDifficulty = difficultStrategy()
      case 3 => print("Verfügbar ab AIN 5")
  }
}