package de.htwg.se.model
package fieldComponent.fieldBaseImpl
import de.htwg.se.util.GameMode
import de.htwg.se.model.fieldComponent.FieldInterface
import de.htwg.se.model.ComputerStrategyComponent.ComputerStrategy
import de.htwg.se.model.ComputerStrategyComponent.ComputerStrategyComponent_Impl.easyStrategy
import de.htwg.se.model.ComputerStrategyComponent.ComputerStrategyComponent_Impl.difficultStrategy
import de.htwg.se.model.ComputerStrategyComponent.ComputerStrategyComponent_Impl.mediumStrategy

case class ComputerModeStrategy() extends GameMode {
  var myDifficulty: ComputerStrategy = easyStrategy()

  override def put(x: Int, y: Int, field: Field): ErrorField = {
    var field2 = field.player.put(x, y, field)
    if (y + 1 <= field.sizeOfDimX - 1)
      var CompPos = myDifficulty.put(field2.field)
      field2.field.player.put(CompPos._1, CompPos._2, field2.field)
    else field2
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
      case -1 => //just to have it conciously handled (loading gamestate is set to multiplayer)
  }

  override def getDifficulty(): Int = {
    myDifficulty match
      case easyStrategy() =>
        0
      case mediumStrategy() =>
        1
      case difficultStrategy() =>
        2
  }
}
