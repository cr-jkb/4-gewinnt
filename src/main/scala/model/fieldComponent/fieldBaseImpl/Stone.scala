package model.fieldComponent.fieldBaseImpl

enum Stone(stringRepresentation: String):
  override def toString = stringRepresentation
  case X extends Stone("X")
  case O extends Stone("O")
  case Empty extends Stone(" ")