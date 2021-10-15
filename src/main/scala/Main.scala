@main
def hello: Unit =
  println("Welcome to 4-Gewinnt!" + eol)
  println(SpielBrett())

val eol = sys.props("line.separator")

def SpielBrett(breite:Int = 7, hoehe:Int = 6, cellWidth:Int = 3): String = {
	return ((spielbrett_seperator(breite, cellWidth) + spielbrett_slots(breite, cellWidth))*hoehe + spielbrett_seperator(breite, cellWidth))
}

def spielbrett_seperator(breite:Int, cellWidth:Int): String = {
	return (("+"+("-"*cellWidth))* breite + "+" + eol)
}

def spielbrett_slots(breite:Int, cellWidth:Int): String = {
	return (("|"+(" "*cellWidth))* breite + "|" + eol)
}
