package de.htwg.se.model.fieldComponent.fieldBaseImpl

case class Matrix[T](rows: Vector[Vector[T]]):
  def this(row: Int, column: Int, filling: T) = this(Vector.tabulate(row, column) { (row, col) => filling })
  val size: Int = rows.size
  val size2: Int = rows(0).size
  def cell(row: Int, col: Int): T = rows(row)(col)
  def row(row: Int) = rows(row)
  def fill(filling: T): Matrix[T] = copy(Vector.tabulate(size, size) { (row, col) => filling })
  def replaceCell(row: Int, col: Int, cell: T): Matrix[T] = copy(rows.updated(row, rows(row).updated(col, cell)))