package model

case class Field(matrix: Matrix[Stone]):
  def this(row: Int = 6, column: Int = 7, filling: Stone = Stone.Empty) = this(new Matrix(row, column, filling))
  val size = matrix.size
  val size2 = matrix.size2
  val eol = sys.props("line.separator")
  def bar(cellWidth: Int = 3, cellNum: Int = 3) = (("+" + "-" * cellWidth) * cellNum) + "+" + eol
  def cells(row: Int, cellWidth: Int = 3) =
    matrix.row(row).map(_.toString).map(" " * ((cellWidth - 1) / 2) + _ + " " * ((cellWidth - 1) / 2)).mkString("|", "|", "|") + eol
  def mesh(cellWidth: Int = 3) =
    (0 until size).map(cells(_, cellWidth)).mkString(bar(cellWidth, size2), bar(cellWidth, size2), bar(cellWidth, size2))
  override def toString = mesh()
  def put(stone: Stone, x: Int, y: Int) = copy(matrix.replaceCell(x, y, stone))
  def get(x: Int, y: Int): Stone = matrix.cell(x, y)