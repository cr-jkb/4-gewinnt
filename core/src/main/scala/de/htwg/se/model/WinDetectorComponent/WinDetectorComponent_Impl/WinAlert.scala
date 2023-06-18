package de.htwg.se.model
package WinDetectorComponent.WinDetectorComponent_Impl
import de.htwg.se.model.fieldComponent.FieldInterface
import de.htwg.se.model.fieldComponent.fieldBaseImpl.Stone

case class WinAlert()
    extends de.htwg.se.model.WinDetectorComponent.WinDetectorInterface {

  var winner: Stone = Stone.Empty
  var maximumSetN: Int = 0
  var allSets: List[(Int, List[(Int, Int)])] = List(
    (0, List((0, 0)))
  ) // contains only Sets of one Player

  override def checkWin(field: FieldInterface): (Boolean, Stone) = {
    (determineWin(field, Stone.Empty), winner)
  } // checks the whole Field

  override def checkWinFor(field: FieldInterface, player: Stone): (Boolean) = {
    determineWin(field, player)
  } // checks given Stones only

  override def checkWinWithNumber(
      field: FieldInterface,
      player: Stone
  ): (Boolean, Int, List[(Int, List[(Int, Int)])]) = {
    (determineWin(field, player), maximumSetN, allSets)
  } // returns Win Bool, Number of max stones in a row, multiDim Array with StonesInRow & Positions
  // List must be sorted

  def checkCombination(
      player: Stone,
      field: FieldInterface,
      h: List[(Int, Int)]
  ): Boolean = // h = CheckSet at (0) contains always the root Stone
    var thisRound = 0
    var winningSet = true
    var subSet: List[(Int, Int)] = List((-1, -1))
    for (i <- h) {
      if (field.get(i._1, i._2) != player) {
        winningSet = false
      } else {
        if (winningSet) { // stop adding to subSet when the first enemy stone breaks the row
          thisRound = thisRound + 1
          if (maximumSetN < thisRound) maximumSetN = thisRound
          subSet = i :: subSet
        }
      }
    }
    allSets = (thisRound, subSet) :: allSets
    if (winningSet & thisRound == 4 & player != Stone.Empty) {
      winner = player;
      println(s"congratulations to player with Stone ${winner}");
    }
    (winningSet & thisRound == 4 & player != Stone.Empty)

  def determineWin(
      field: FieldInterface,
      forPlayer: Stone
  ): Boolean = // O(8)*7*6
    winner = Stone.Empty
    maximumSetN = 0
    var returnBool = false

    val limitV = field.sizeOfDimY
    val limitH = field.sizeOfDimX

    for (i <- 0 until limitV) {
      for (j <- 0 until limitH) {
        val current = field.get(i, j)
        if (current != Stone.Empty) {

          // within x (vertical)
          if (i + 3 < limitV) {
            var cSet = List((i, j)) // checkSet
            for (c <- i + 1 to i + 3) {
              val mP = (c, j)
              cSet = mP :: cSet
            }
            returnBool = checkCombination(current, field, cSet)
          }

          if (returnBool) true

          if (i - 3 >= 0) {
            var cSet = List((i, j)) // checkSet
            for (c <- i - 1 to i - 3) {
              val mP = (c, j)
              cSet = mP :: cSet
            }
            returnBool = checkCombination(current, field, cSet)
          }

          if (returnBool) true

          // within y (horizontal)
          if (j + 3 < limitH) {
            var cSet = List((i, j)) // checkSet
            for (c <- j + 1 to j + 3) {
              val mP = (i, c)
              cSet = mP :: cSet
            }
            returnBool = checkCombination(current, field, cSet)
          }

          if (returnBool) true

          if (j - 3 >= 0) {
            var cSet = List((i, j)) // checkSet
            for (c <- j - 1 to j - 3) {
              val mP = (i, c)
              cSet = mP :: cSet
            }
            returnBool = checkCombination(current, field, cSet)
          }

          if (returnBool) true

          // diagonal
          if (j + 3 < limitH & i + 3 < limitV) { // hoch R
            var cSet = List((i, j)) // checkSet
            for (c <- 1 to 3) {
              val mP = (i + c, j + c)
              cSet = mP :: cSet
            }
            returnBool = checkCombination(current, field, cSet)
          }

          if (returnBool) true

          if (j + 3 < limitH & i - 3 >= 0) { // runter R
            var cSet = List((i, j)) // checkSet
            for (c <- 1 to 3) {
              val mP = (i - c, j + c)
              cSet = mP :: cSet
            }
            returnBool = checkCombination(current, field, cSet)
          }

          if (returnBool) true

          if (j - 3 >= 0 & i - 3 >= 0) { // runter L
            var cSet = List((i, j)) // checkSet
            for (c <- 1 to 3) {
              val mP = (i - c, j - c)
              cSet = mP :: cSet
            }
            returnBool = checkCombination(current, field, cSet)
          }

          if (returnBool) true

          if (j - 3 >= 0 & i + 3 < limitV) { // hoch L
            var cSet = List((i, j)) // checkSet
            for (c <- 1 to 3) {
              val mP = (i + c, j - c)
              cSet = mP :: cSet
            }
            returnBool = checkCombination(current, field, cSet)
          }

          if (returnBool) true

          // könnte man wunderschön funktional programmieren:
          // Pattern: if (L/R) & (Hoch/Runter)
          // mP +- ++ -- -+ c
        }
      }
    }
    false
}
