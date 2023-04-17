package de.htwg.se.util

import de.htwg.globals.*
import de.htwg.se.model.fieldComponent.fieldElements.Stone
import util.Stone

enum Stone (stringRepresentation: String):
  case X extends Stone("X")
  case O extends Stone("O")
  case Empty extends Stone(" ")

  override def toString = stringRepresentation

  def set(x: Int, y: Int, filling: String): FieldInterface =
    filling match {
      case " " => copy(matrix.replaceCell(x, y, Stone.Empty))
      case "X" => copy(matrix.replaceCell(x, y, Stone.X))
      case "O" => copy(matrix.replaceCell(x, y, Stone.O))
    }
