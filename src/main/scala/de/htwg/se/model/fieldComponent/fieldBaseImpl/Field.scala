// Implemented 1-Instance Class for Field State

package de.htwg.se.model.fieldComponent.fieldBaseImpl

import de.htwg.se.model.fieldComponent.FieldInterface
import de.htwg.se.util.PlayerState
import de.htwg.se.util.ModeStrategy

case class Field(var matrix: Matrix[Stone], var player: PlayerState, var mode: ModeStrategy) extends FieldInterface:

  def this(row: Int = 6, column: Int = 7, filling: Stone = Stone.Empty) = this(new Matrix(row, column, filling), TruePlayerState(), PlayerModeStrategy())

  val size = matrix.size //
  val size2 = matrix.size2
  val eol = sys.props("line.separator")

  //--String Building:
  def bar(cellWidth: Int = 3, cellNum: Int = 3) : String = (("+" + "-" * cellWidth) * cellNum) + "+" + eol //barrier created horizontally (One Line Text)

  def cells(row: Int, cellWidth: Int = 3) : String = //slots created horizontally (One Line Text)
    matrix.row(row).map(_.toString).map(" " * ((cellWidth - 1) / 2) + _ + " " * ((cellWidth - 1) / 2)).mkString("|", "|", "|") + eol

  def mesh(cellWidth: Int = 3) : String = //build Grid as String
    (0 until size).map(cells(_, cellWidth)).mkString(bar(cellWidth, size2), bar(cellWidth, size2), bar(cellWidth, size2))

  override def toString = mesh()

  //--Mode Layer related:

  def put(x: Int, y: Int): Field =
    mode.put(x, y, this)

  def set(x: Int, y: Int, filling: String): FieldInterface =
    filling match {
      case " " => copy(matrix.replaceCell(x, y, Stone.Empty))
      case "X" => copy(matrix.replaceCell(x, y, Stone.X))
      case "O" => copy(matrix.replaceCell(x, y, Stone.O))
    }

  def get(x: Int, y: Int): Stone = matrix.cell(x, y)

  def getPlayerState(): Boolean = if (player == TruePlayerState()) true else false

  def getMode(): String = if (mode == PlayerModeStrategy()) "player" else "computer"

  def setMode(str: String): ModeStrategy =
    str match 
      case "player" => 
        mode = PlayerModeStrategy()
        mode
      case "computer" =>
        mode = ComputerModeStrategy()
        mode

  def setPlayer(str: String): PlayerState =
    str match {
      case "false" => player = FalsePlayerState()
      case "true" => player = TruePlayerState()
    }
    player

  //--Field related:
  def undo(x: Int, y: Int): Field = //remove the stone at x,y
    if (mode == PlayerModeStrategy())
      if (player == TruePlayerState())
        player = FalsePlayerState()
      else
        player = TruePlayerState()
      copy(matrix.replaceCell(x, y, Stone.Empty)) //write empty Stone
    else
      val field2 = copy(matrix.replaceCell(x, y, Stone.Empty))
      if (y + 1 <= size2 - 1)
        copy(field2.matrix.replaceCell(x, y + 1, Stone.Empty))
      else
        field2