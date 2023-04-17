package de.htwg.se.util

import de.htwg.util.globals.*


//--String Building:
def cells(//rowAsString
          row: Int,
          cellWidth: Int = cellWidth,
          matrix: Matrix[String]
         ): String = //slots created horizontally (One Line Text)
  matrix
    .row(row)
    .map(_.toString)
    .map(whiteSpace * ((cellWidth - 1) / 2) + _ + whiteSpace * ((cellWidth - 1) / 2))
    .mkString(
      verticalSeparator,
      verticalSeparator,
      verticalSeparator
    ) + eol // .map = replace in Array with whats given in the parantheses

def mesh(cellWidth: Int = cellWidth, matrix: Matrix[String]): String = //build Grid as String
  val horizontalSeparator = bar(cellWidth, sizeOfDimX)
  (0 until matrix.sizeOfDimY)
    .map(cells(_, cellWidth, matrix))
    .mkString(
      horizontalSeparator,
      horizontalSeparator,
      horizontalSeparator
    ) // eine Linie am Anfang, jeder Eintrag wird getrennt durch Trennlinie und am Ende wird eine zusätzliche hinzugefügt
