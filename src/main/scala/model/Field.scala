package model

case class Field(lines: Int, rows: Int, cellWidth: Int):
	def this(lines: Int, rows: Int, cellWidth: Int) = this((spielbrett_separator(rows, cellWidth) + spielbrett_slots(rows, cellWidth)) * lines + spielbrett_separator(rows, cellWidth))
	def spielbrett_separator(rows:Int, cellWidth:Int): String = {
		return (("+" + ("-"*cellWidth))*rows + "+" + eol)
	}
	def spielbrett_slots(rows:Int, cellWidth:Int): String = {
		return (("|" + (" "*cellWidth))*rows + "|" + eol)
	}

	def put(spieler: Boolean, userLine: Int, userRow: Int) =
		val sb = new StringBuilder(this)
		val index = ((1 + cellWidth) * rows + 3) * (userLine * 2 - 1) + ((cellWidth + 1) * (userRow - 1) + cellWidth / 2 + 1)
		if (spieler) {
			sb(index) = 'O'
		} else {
			sb(index) = 'X'
		}
		this = sb.toString

	def reset() =
		this = new Field(6, 7, 3)
