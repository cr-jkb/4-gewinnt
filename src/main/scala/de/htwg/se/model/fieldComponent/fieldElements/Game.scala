//Game holds an Matrix that holds Stones and instancizes system-related String Representations including the GameState
// - SinglePlayer || Multiplayer
// - Player1 putting || Player2 putting
// - Stones as Strings in Field

package de.htwg.se.model.fieldComponent.fieldElements


import de.htwg.se.model.fieldComponent.fieldElements.*
import de.htwg.se.model.fieldComponent.gameState.*
import de.htwg.se.model.fieldComponent.{FieldInterface, GameModeInterface, PlayerState}
import de.htwg.se.util.{Matrix, Stone}

case class Game(
                 var field: Matrix[Stone],
                 var player: PlayerState,
                 var mode: GameModeInterface
) extends FieldInterface {

  def this(row: Int = vierGewinntRowNum, column: Int = vierGewinntColNum, filling: Stone = Stone.Empty) =
    this(
      new Matrix(row, column, filling),
      TruePlayerState(),
      PlayerModeStrategy()
    )
  /*
  val sizeOfDimY = field.sizeOfDimY //vertical
  val sizeOfDimX = field.sizeOfDimX //horizontal*/
  /*var error = ""*/


  def setPlayer(str: String): PlayerState =
    str match {
      case "false" => player = FalsePlayerState()
      case "true" => player = TruePlayerState()
    }
    player

  def getMode(): String =
    if (mode == PlayerModeStrategy()) "player" else "computer"

  override def toString = "Player is "+ getPlayerState() + eol + "in " + getMode() + eol + field.toString()

  def put(x: Int, y: Int): Game =
    mode.put(x, y, this)._1
  //TODO23 How to notify about Errors?

  /*def setCell() : Game*/

  def get(x: Int, y: Int): Stone = if (x == -1 | y == -1) Stone.X
  else field.cell(x, y) //-1 when ComputerStrategy finds a FullRow

  def getPlayerState(): Boolean =
    if (player == TruePlayerState()) true else false

  def setMode(str: String): GameModeInterface =
    str match
      case "player" =>
        mode = PlayerModeStrategy()
        mode
      case "computer" =>
        mode = ComputerModeStrategy()
        mode

  def setDifficulty(d: Int) = {
    mode.setDifficulty(d)
  }

  //--Game related:
  def undo(x: Int, y: Int): Game = //remove the stone at x,y

    if (mode == PlayerModeStrategy())
      if (player == TruePlayerState())
        player = FalsePlayerState()
      else
        player = TruePlayerState()

    copy(field.replaceCell(x, y, Stone.Empty))

}