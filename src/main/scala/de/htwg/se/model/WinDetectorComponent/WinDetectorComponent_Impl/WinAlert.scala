package de.htwg.se.model.WinDetectorComponent

import de.htwg.se.model.fieldComponent.fieldBaseImpl.Stone
import de.htwg.se.model.fieldComponent.FieldInterface

case class WinAlert() extends de.htwg.se.model.WinDetectorComponent.WinDetectorInterface {
  
override def checkWin(field: FieldInterface): (Boolean, Stone) = {(false, field.get(0,0))} //checks the whole Field
override def checkWinFor(field: FieldInterface, player: Stone): (Boolean) = {false} //checks given Stones only
override def checkWinWithNumber(field: FieldInterface, player: Stone): (Boolean, Int, List[(Int, List[(Int, Int)])]) = { (false, 1, List((0, List((0, 0)))))} //returns Win Bool, Number of max stones in a row, multiDim Array with StonesInRow & Positions
//List must be sorted

var notifyEstWin = false
  def checkCombination(player: Stone, field: FieldInterface, h: List[(Int, Int)]): Boolean = //move to global
    if (!notifyEstWin)
      for (i <- h) {
        if (field.get(i._1, i._2) != player) { false }
      }
      true
    else
      true 

  def determineWin(field: FieldInterface): Boolean = // O(8)*7*6
    val limitV = field.size
    val limitH = field.size2
    for (i <- 0 to limitV) {
      for (j <- 0 to limitH) {
        val current = field.get(i,j)

        //within x (vertical)
        if (i+3 < limitV) {
          var cSet = List((i, j)) //checkSet
          for (c <- i+1 to i+3) {
            val mP = (c, j)
            cSet = mP :: cSet
          }
          checkCombination(current, field, cSet)
        }
        
        if (i-3 >= 0) {
          var cSet = List((i, j)) //checkSet
          for (c <- i-1 to i-3) {
            val mP = (c, j)
            cSet = mP :: cSet
          }
          checkCombination(current, field, cSet)
        }

        //within y (horizontal)
        if (j+3 < limitH) {
          var cSet = List((i, j)) //checkSet
          for (c <- j+1 to j+3) {
            val mP = (i, c)
            cSet = mP :: cSet
          }
          checkCombination(current, field, cSet)
        }

        if (j-3 >= 0) {
          var cSet = List((i, j)) //checkSet
          for (c <- j-1 to j-3) {
            val mP = (i, c)
            cSet = mP :: cSet
          }
          checkCombination(current, field, cSet)
        }

        //diagonal
        if (j+3 < limitH & i+3 < limitV) { //hoch R
          var cSet = List((i, j)) //checkSet
          for (c <- 1 to 3) {
            val mP = (i+c, j+c)
            cSet = mP :: cSet
          }         
          checkCombination(current, field, cSet)
        }

        if (j+3 < limitH & i-3 >= 0) { //runter R
          var cSet = List((i, j)) //checkSet
          for (c <- 1 to 3) {
            val mP = (i-c, j+c)
            cSet = mP :: cSet
          }         
          checkCombination(current, field, cSet)
        }

        if (j-3 >= 0 & i-3 >= 0) { //runter L
          var cSet = List((i, j)) //checkSet
          for (c <- 1 to 3) {
            val mP = (i-c, j-c)
            cSet = mP :: cSet
          }         
          checkCombination(current, field, cSet)
        }

        if (j-3 >= 0 & i+3 < limitV) { //hoch L
          var cSet = List((i, j)) //checkSet
          for (c <- 1 to 3) {
            val mP = (i+c, j-c)
            cSet = mP :: cSet
          }         
          checkCombination(current, field, cSet)
        }

        //könnte man wunderschön funktional programmieren:
          //Pattern: if (L/R) & (Hoch/Runter)
          // mP +- ++ -- -+ c

      }
    }
      
    false
  }