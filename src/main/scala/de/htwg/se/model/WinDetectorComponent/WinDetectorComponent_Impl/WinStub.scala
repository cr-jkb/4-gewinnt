package de.htwg.se.model.WinDetectorComponent
// returns false
import de.htwg.se.model.fieldComponent.FieldInterface
import de.htwg.se.util.Stone

case class WinStub() extends de.htwg.se.model.WinDetectorComponent.WinDetectorInterface {
    override def checkWin(field: FieldInterface): (Boolean, Stone) = {(false, field.get(0,0))} //checks the whole Game
    override def checkWinFor(field: FieldInterface, player: Stone): (Boolean) = {false} //checks given Stones only
    override def checkWinWithNumber(field: FieldInterface, player: Stone): (Boolean, Int, List[(Int, List[(Int, Int)])]) = { (false, 1, List((0, List((0, 0)))))} //returns Win Bool, Number of max stones in a row, multiDim Array with StonesInRow & Positions
}