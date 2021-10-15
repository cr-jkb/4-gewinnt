@main
//test test Moin!
def hello: Unit =
  println("Willkommen zu 4-Gewinnt!")
  val ints = List.range(0,6)
  for i <- ints
  do spielbrett
  print(spielbrett_separator)

val eol = sys.props("line.separator")
def spielbrett: Unit =
  print(spielbrett_separator)
  print(spielbrett_slots)

val spielbrett_separator = "+---+---+---+---+---+---+---+" + eol 
val spielbrett_slots = "|   |   |   |   |   |   |   |" + eol

val grid = (spielbrett_separator + spielbrett_slots) * 6 + spielbrett_separator
def sep(cellWidth:Int = 3) = ("+" + ("-") *cellWidth)*6 + eol
