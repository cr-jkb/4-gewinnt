package de.htwg.se.model.fieldComponent.gameState

import de.htwg.se.model.fieldComponent.GameModeInterface
import de.htwg.se.model.fieldComponent.fieldElements.Game
/*import de.htwg.se.model.fieldComponent.FieldInterface*/
import de.htwg.se.model.fieldComponent.gameState.difficulties.ComputerStrategy
import de.htwg.se.model.fieldComponent.gameState.difficulties.easyStrategy
import de.htwg.se.model.fieldComponent.gameState.difficulties.mediumStrategy
import de.htwg.se.model.fieldComponent.gameState.difficulties.difficultStrategy

case class ComputerModeStrategy() extends GameModeInterface {
  var myDifficulty: ComputerStrategy = easyStrategy()

  override def put(x: Int, y: Int, field: Game): (Game, Error) = {
    var playerHasSetNewStoneField = field.player.put(x, y, field)
    if (!playerHasSetNewStoneField._2.isEmpty()) then print(playerHasSetNewStoneField._2)
    playerHasSetNewStoneField = playerHasSetNewStoneField._1
    if (y + 1 <= field.sizeOfDimX - 1)
      var CompPos = myDifficulty.put(playerHasSetNewStoneField.field)
      (playerHasSetNewStoneField.field.player.put(CompPos._1, CompPos._2, playerHasSetNewStoneField.field), "")
    else (playerHasSetNewStoneField, "No more space in column")
  }

  override def setDifficulty(diff: Int) = {
    diff match
      case 0 =>
        myDifficulty = easyStrategy();
        println("Computer spielt ab jetzt auf Easy")
      case 1 =>
        myDifficulty = mediumStrategy();
        println("Computer spielt ab jetzt auf Medium")
      case 2 =>
        myDifficulty = difficultStrategy();
        println("Computer spielt ab jetzt auf Schwer")
      case 3 => println("Verfuegbar ab AIN 5")
  }
}
