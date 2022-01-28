package de.htwg.se.model.WinDetectorComponent

import de.htwg.se.model.fieldComponent.FieldInterface
import de.htwg.se.util.PlayerState
import de.htwg.se.model.fieldComponent.fieldBaseImpl.Stone
import java.lang.reflect.Field

trait WinDetectorInterface {
    def checkWin(field: FieldInterface): (Boolean, Stone) //checks the whole Field
    def checkWinFor(field: FieldInterface, player: Stone): (Boolean) //checks given Stones only
    def checkWinWithNumber(field: FieldInterface, player: Stone): (Boolean, Int, List[(Int, List[(Int, Int)])]) //returns Win Bool, Number of max stones in a row, multiDim Array with StonesInRow & Positions
}