import java.util.Scanner
import scala.io.StdIn.readLine
@main
def hello: Unit = {
	
	println(eol + "Hochschule fuer Technik, Wirtschaft & Gestaltung")
	println("AIN SOFTWARE-ENGINEERING WiSe 21/22")
	println("        ### GRUPPE 15 ###")
	println(eol + ">  Willkommen zu 4-Gewinnt!  <")
	var spielFeld = SpielBrett(rows, lines, cellWidth)
	var input: String = ""

	while (input != "q") {
		println(spielFeld)
		println(eol + "Made with heart in Constance")
		input = readLine()
		spielFeld = processInputLine(input, spielFeld, rows, cellWidth, lines)
		if (input == "i") {
			spieler = !spieler
		}
	}
}
val eol = sys.props("line.separator")
val rows = 2
val lines = 3
val cellWidth = 1
var spieler: Boolean = false // false = X, true = O

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

def processInputLine(input: String, spielFeld:String, rows:Int, cellWidth:Int, lines:Int): String = {
	input match {
		case "q" => spielFeld
		case "n" => SpielBrett(rows, lines, cellWidth)
		case "i" =>
			val line = new Scanner(System.in)
			val userLine = line.nextInt 
			val userRow = line.nextInt
			if (checkValid(userLine, userRow, lines, rows, cellWidth, spielFeld)) {
				val sb = new StringBuilder(spielFeld)
				val index = ((1 + cellWidth) * rows + 3) * (userLine * 2 - 1) + ((cellWidth + 1) * (userRow - 1) + cellWidth / 2 + 1)
				if (spieler) {
					sb(index) = 'O'
				} else {
					sb(index) = 'X'
				}
				return sb.toString()
			}
			spieler = !spieler
			spielFeld
	}
}

def checkValid(userLine:Int, userRow:Int, lines:Int, rows:Int, cellWidth:Int, spielFeld:String): Boolean = {
	val sb = new StringBuilder(spielFeld)
	val index = ((1 + cellWidth) * rows + 3) * (userLine * 2 - 1) + ((cellWidth + 1) * (userRow - 1) + cellWidth / 2 + 1)
	val index2 = ((1 + cellWidth) * rows + 3) * ((userLine + 1) * 2 - 1) + ((cellWidth + 1) * (userRow - 1) + cellWidth / 2 + 1)
	if (userRow <= rows && userRow >= 1 && userLine <= lines && userLine >= 1 && (userLine.equals(lines) || sb(index2).equals('X') || sb(index2).equals('O')) && sb(index).equals(' ')) {
		true
	}
	print("ERROR" + eol)
	false
}