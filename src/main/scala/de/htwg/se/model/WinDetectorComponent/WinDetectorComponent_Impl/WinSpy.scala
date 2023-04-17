package de.htwg.se.model.WinDetectorComponent
// returns true each alertAfter
import de.htwg.se.model.fieldComponent.FieldInterface
import de.htwg.se.util.Stone

case class WinSpy(alertAfter: Int) extends de.htwg.se.model.WinDetectorComponent.WinDetectorInterface {
    var countedCalls = 0

    override def checkWin(field: FieldInterface): (Boolean, Stone) = {countedCalls = countedCalls +1; ((countedCalls % alertAfter == 0), field.get(0,0))} //checks the whole Game
    override def checkWinFor(field: FieldInterface, player: Stone): (Boolean) = {countedCalls = countedCalls +1; (countedCalls % alertAfter == 0)} //checks given Stones only
    override def checkWinWithNumber(field: FieldInterface, player: Stone): (Boolean, Int, List[(Int, List[(Int, Int)])]) = { countedCalls = countedCalls +1 ; ((countedCalls % alertAfter == 0), countedCalls % 4, List((0, List((0, 0)))))} //returns Win Bool, Number of max stones in a row, multiDim Array with StonesInRow & Positions
}