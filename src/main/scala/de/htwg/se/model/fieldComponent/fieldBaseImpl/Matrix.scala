// Implemented 1-Instance Class

package de.htwg.se.model.fieldComponent.fieldBaseImpl

case class Matrix[T](rows: Vector[Vector[T]]):
  def this(row: Int, column: Int, filling: T) =
    this(Vector.tabulate(row, column) { (row, col) => filling })
  val sizeOfDimY: Int = rows.size
  val sizeOfDimX: Int = rows(0).size
  def cell(row: Int, col: Int): T = rows(row)(col)
  def row(row: Int) = rows(row)
  def getRows: Vector[Vector[T]] = rows
  /*   def fill(filling: T): Matrix[T] = copy(Vector.tabulate(size, size) {
    (row, col) => filling
  }) */
  def replaceCell(row: Int, col: Int, cell: T): Matrix[T] = copy(
    rows.updated(row, rows(row).updated(col, cell))
  )
