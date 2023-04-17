package de.htwg.globals
//Systembezogene Variablen : Entweder bei Field oder bei Controller einzuordnen

val eol = sys.props("line.separator")
val verticalSeparator: String = "|"
val whiteSpace: String = " "
val cellWidth: Int = 3
def bar(cellWidth: Int = 3, cellNum: Int = 3): String =
  (("+" + "-" * cellWidth) * cellNum) + "+" + eol //barrier created horizontally (One Line Text)

val vierGewinntRowNum = 6
val vierGewinntColNum = 7