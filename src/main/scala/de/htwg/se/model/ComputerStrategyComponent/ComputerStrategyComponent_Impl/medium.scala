package de.htwg.se.model.fieldComponent.fieldBaseImpl.difficulties

import de.htwg.se.model.fieldComponent.FieldInterface
import de.htwg.se.model.WinDetectorComponent.WinStub
import de.htwg.se.model.WinDetectorComponent.WinDetectorInterface
import de.htwg.se.model.fieldComponent.fieldBaseImpl.Stone
import scala.languageFeature.postfixOps
import scala.util.{Try, Success, Failure}

case class mediumStrategy()
    extends de.htwg.se.model.fieldComponent.fieldBaseImpl.difficulties.ComputerStrategy {
  var plannedNext = (0, 0)
  val WinCheck: WinDetectorInterface = WinStub()
  var limitV = 0
  var limitH = 0

  override def put(field: FieldInterface) = {
    limitV = field.sizeOfDimY; limitH = field.sizeOfDimX;
    if (evalNext(field)) plannedNext else evalNew(field)
  }

//   def evalNext(field: FieldInterface): Boolean = { // true if there is a better reaction (prioritizing)
//     object FoundWin extends Exception

//     val myDataSet = WinCheck.checkWinWithNumber(
//       field,
//       ComputerStone
//     ) // limit Scanning on my own Stones
//     try {
//       if (myDataSet._2 == 3) { // 3er Row found
//         for (x <- myDataSet._3) { // jede Row
//           var possiblePos = (0, 0)
//           if (x._1 == 3) { // Wenn Set aus 3*StonePos besteht
//             val freePossibilities = relevantPos(
//               field,
//               predict(x._2)
//             ) // übergebe Set; bekomme freie Möglichkeiten
//             for (i <- 1 until freePossibilities.size) { // skip ZombieElement
//               possiblePos = freePossibilities(i)
//               val matchedPos = matchx(possiblePos._1, field)
//               if (matchedPos == possiblePos._1) {
//                 plannedNext = possiblePos; throw FoundWin
//               }
//             }
//           }
//         }
//         plannedNext = (0, 0)
//         false
//       } else false
//     } catch { case FoundWin => true }
//   }

  def evalNext(field: FieldInterface): Boolean = {
    object FoundWin extends Exception
    val myDataSet = WinCheck.checkWinWithNumber(field, ComputerStone)

    Try {
      if (myDataSet._2 == 3) {
        myDataSet._3.find { x =>
          x._1 == 3 && {
            val freePossibilities = relevantPos(field, predict(x._2))
            freePossibilities.drop(1).exists { possiblePos =>
              val matchedPos = matchx(possiblePos._1, field)
              if (matchedPos == possiblePos._1) {
                plannedNext = possiblePos
                throw FoundWin
              } else false
            }
          }
        }

        plannedNext = (0, 0)
        false
      } else false
    }.getOrElse(true)
  }

  /* returned jetzt an Pos 0 ein leeres Element */
  def relevantPos(
      field: FieldInterface,
      predictList: List[(Int, Int)]
  ): List[(Int, Int)] = { // removes Positions that are already filled (DO NOT INCLUDE matchX and check only if Stone.Empty)
    // predictList.filter(checkStonePosIfEmpty(field)) failed due to field needed
    var myNewList = List((-1, -1))
    for (x <- predictList) {
      if (field.get(x._1, x._2) == Stone.Empty) { myNewList = x :: myNewList }
    }
    myNewList
  }

  // def checkStonePosIfEmpty(field: FieldInterface, x: (Int, Int)) : Boolean = field.get(x._1, x._2) == Stone.Empty

  /* predict takes scalable StoneSet of minSize = 2 */
  /* returns always List of size 2 containing PosTuples of -1 if impossible due to Field Dimensions */
  def predict(StoneSet: List[(Int, Int)]): List[(Int, Int)] = {
    val xDiff = (StoneSet(0)._1 - StoneSet(1)._1) // Delta X
    val yDiff = (StoneSet(0)._2 - StoneSet(1)._2) // Delta Y

    if (xDiff == 0) { // horizontal (2 cases) da x invariabel
      val invariabel = StoneSet(0)._1
      var mL = List(
        (invariabel, StoneSet(0)._2 - 1)
      ) // -1 returns no EmptyStone if asked via field.get (case 1 L)
      val upperY = StoneSet(StoneSet.size - 1)._2 + 1 // (case 2 R)
      if (upperY < limitH) { // noch im Spielfeld
        val mP = (invariabel, upperY)
        mL = mP :: mL
      }
      mL
    } else if (yDiff == 0) { // vertical (2 cases) da y invariabel
      val invariabel = StoneSet(0)._2
      var mL = List(
        (StoneSet(0)._2 - 1, invariabel)
      ) // -1 returns no EmptyStone if asked via field.get (case 1 O)
      val upperX = StoneSet(StoneSet.size - 1)._2 + 1 // (case 2 U)
      if (upperX < limitV) { // noch im Spielfeld
        val mP = (upperX, invariabel)
        mL = mP :: mL
      }
      mL
    } else { // diagonal (4 directional cases -> 2 omnidirectional cases) Legende: CurrentCase = Equivalent zu (mostLeftStone // mostRightStone)
      if (xDiff == 1 & yDiff == 1) { // R+U = L+O (x-1; y-1 // x+1; y+1) => 1;1
        returnDiagonal(
          1,
          1,
          StoneSet(StoneSet.size - 1),
          StoneSet(0)
        ) // wir gehen in den ersten beiden Steinen nach rechts und unten. Der linkeste Stein (mostLeftStone) liegt somit im Subtract und die erste SteinPos wird daher als 2. Tupel übergeben, da wir sonst einen Stein zurück bekommen der bereits im ursprünglichen Set enthalten ist
      } else if (xDiff == -1 & yDiff == 1) { // R+O = L+U (x+1; y-1 // x-1; y+1) => -1;1
        returnDiagonal(-1, 1, StoneSet(StoneSet.size - 1), StoneSet(0))
      } else if (xDiff == 1 & yDiff == -1) { // L+U = R+O (x+1; y-1 // x-1; y+1)
        returnDiagonal(
          -1,
          1,
          StoneSet(0),
          StoneSet(StoneSet.size - 1)
        ) // wir gehen in den ersten beiden Steinen nach links unten. Der linkeste Stein findet sich per Subtract über das letzte StonePosTupel
      } else { // L+O = R+U (x-1; y-1 // x+1; y+1)
        returnDiagonal(1, 1, StoneSet(0), StoneSet(StoneSet.size - 1))
      }

    }
  }

  def returnDiagonal(
      xChange: Int,
      yChange: Int,
      StoneSetAdd: (Int, Int),
      StoneSetSubtract: (Int, Int)
  ): List[(Int, Int)] = {
    var mL: List[(Int, Int)] = List((0, 0))

    // addValues
    var newX = StoneSetAdd._1 + xChange
    var newY = StoneSetAdd._2 + yChange
    if (newX < limitV & newX > -1 & newY < limitH & newY > -1) { // akzeptiere nur innerhalb des Spielfeldes
      mL = List((newX, newY))
    } else { mL = List((-1, -1)) }

    // subtractValues
    newX = StoneSetSubtract._1 - xChange
    newY = StoneSetSubtract._2 - yChange
    if (newX < limitV & newX > -1 & newY < limitH & newY > -1) { // akzeptiere nur innerhalb des Spielfeldes
      val mP = (newX, newY)
      mL = mP :: mL
    } else { mL = (-1, -1) :: mL }

    mL
  }
  /* ^ Prove:
        (x-1; y-1 // x+1; y+1)

        f(a1, b1) =
        a - 1
        b - 1
        a + 1
        b + 1

        (x+1; y-1 // x-1; y+1)

        f(-1, 1) =
        a - -1
        b - 1
        a + -1
        b + 1   */

  def evalNew(field: FieldInterface): (Int, Int) = { // try getting 3 and 4
    val mE = easyStrategy()
    mE.findRandom(field)
  }
}
