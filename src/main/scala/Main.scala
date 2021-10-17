@main
def hello: Unit =
	
	println(eol + "Hochschule fuer Technik, Wirtschaft & Gestaltung")
	println("AIN SOFTWARE-ENGINEERING WiSe 21/22")
	println("        ### GRUPPE 15 ###")
	println(eol + ">  Willkommen zu 4-Gewinnt!  <")
	println(SpielBrett())

	println(eol + "Made with heart in Constance")
val eol = sys.props("line.separator")

def SpielBrett(rows:Int = 7, lines:Int = 6, cellWidth:Int = 3)
: String = {
	return ((spielbrett_separator(rows, cellWidth) +
		spielbrett_slots(rows, cellWidth))*lines +
		spielbrett_separator(rows, cellWidth))
}

def spielbrett_separator(rows:Int, cellWidth:Int): String = {
	return (("+" + ("-"*cellWidth))*rows + "+" + eol)
}

def spielbrett_slots(rows:Int, cellWidth:Int): String = {
	return (("|" + (" "*cellWidth))*rows + "|" + eol)
}
