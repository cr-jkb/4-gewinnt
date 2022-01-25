package de.htwg.se.model.fieldComponent.fieldBaseImpl

import de.htwg.se.util.ModeStrategy
import de.htwg.se.model.fieldComponent.FieldInterface
import scala.compiletime.ops.boolean
import scala.collection.immutable.HashMap
import scala.collection.immutable.ListSet

case class PlayerModeStrategy() extends ModeStrategy {
  override def put(x: Int, y: Int, field: Field): Field = {
    val mF = field.player.put(x, y, field) // hand parameters through to the next layer (which is player)
    //determineWin(mF) //destroys field somehow (not handed through afterwards) probably because of the warning
    mF
  }

  def checkCombination(player: Stone, field: Field, h: List[(Int, Int)]): Boolean =
    for (i <- h) {
      if (field.get(i._1, i._2) != player) { false }
    }
    true

  def determineWin(field: Field): Boolean = // O(8)*7*6
    val limitV = field.matrix.size
    val limitH = field.matrix.size2
    for (i <- 0 to limitV) {
      for (j <- 0 to limitH) {
        val current = field.matrix.cell(i,j)

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
          // mP +-c

      }
    }
      
    false

}