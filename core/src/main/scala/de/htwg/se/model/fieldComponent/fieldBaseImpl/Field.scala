// Implemented 1-Instance Class for Field State
package de.htwg.se.model
package fieldComponent.fieldBaseImpl
import fieldComponent.FieldInterface
import de.htwg.se.util.PlayerState
import de.htwg.se.util.GameMode

case class Field(
    var matrix: Matrix[Stone],
    var player: PlayerState,
    var mode: GameMode
) extends FieldInterface:

  def this(row: Int = 6, column: Int = 7, filling: Stone = Stone.Empty) =
    this(
      new Matrix(row, column, filling),
      TruePlayerState(),
      PlayerModeStrategy()
    )

  val sizeOfDimY = matrix.sizeOfDimY // vertical
  val sizeOfDimX = matrix.sizeOfDimX // horizontal
  val eol = sys.props("line.separator")
  var error = ""

  // --String Building:
  def bar(cellWidth: Int = 3, cellNum: Int = 3): String =
    (("+" + "-" * cellWidth) * cellNum) + "+" + eol // barrier created horizontally (One Line Text)

  def cells(
      row: Int,
      cellWidth: Int = 3
  ): String = // slots created horizontally (One Line Text)
    matrix
      .row(row)
      .map(_.toString)
      .map(" " * ((cellWidth - 1) / 2) + _ + " " * ((cellWidth - 1) / 2))
      .mkString(
        "|",
        "|",
        "|"
      ) + eol // .map = replace in Array with whats given in the parantheses. Each element given as placeholder

  def mesh(cellWidth: Int = 3): String = // build Grid as String
    val trennLine = bar(cellWidth, sizeOfDimX)
    (0 until sizeOfDimY)
      .map(cells(_, cellWidth))
      .mkString(
        trennLine,
        trennLine,
        trennLine
      ) // eine Linie am Anfang, jeder Eintrag wird getrennt durch Trennlinie und am Ende wird eine zusätzliche hinzugefügt

  def set(x: Int, y: Int, filling: String): FieldInterface =
    filling match {
      case " " => copy(matrix.replaceCell(x, y, Stone.Empty))
      case "X" => copy(matrix.replaceCell(x, y, Stone.X))
      case "O" => copy(matrix.replaceCell(x, y, Stone.O))
    }

  def setPlayer(str: String): PlayerState =
    str match {
      case "false" => player = FalsePlayerState()
      case "true"  => player = TruePlayerState()
    }
    player

  override def toString = mesh()

  // --Mode Layer related:

  def put(x: Int, y: Int): Field =
    val newField = mode.put(x, y, this)
    newField.field.error = newField.error // TODO23
    newField.field

  def get(x: Int, y: Int): Stone = if (x == -1 | y == -1) Stone.X
  else matrix.cell(x, y) // -1 when ComputerStrategy finds a FullRow

  def getPlayerState(): Boolean =
    if (player == TruePlayerState()) true else false

  def getMode(): String =
    if (mode == PlayerModeStrategy()) "multiplayer"
    else "singleplayer"

  def setMode(str: String): GameMode =
    str match
      case "multiplayer" | "multi" =>
        mode = PlayerModeStrategy()
      case ("singleplayer" | "single") =>
        mode = ComputerModeStrategy()
    mode

  def setDifficulty(d: Int) = { mode.setDifficulty(d) }
  def getDifficulty() = { mode.getDifficulty() }

  // --Field related:
  def undo(x: Int, y: Int): Field = // remove the stone at x,y
    if (mode == PlayerModeStrategy())
      if (player == TruePlayerState())
        player = FalsePlayerState()
      else
        player = TruePlayerState()
      copy(matrix.replaceCell(x, y, Stone.Empty)) // write empty Stone
    else
      val field2 = copy(matrix.replaceCell(x, y, Stone.Empty))
      if (y + 1 <= sizeOfDimX - 1)
        copy(field2.matrix.replaceCell(x, y + 1, Stone.Empty))
      else
        field2
