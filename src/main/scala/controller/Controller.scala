package controller

import model.Field
import util.Observable

case class Controller(field: Field) extends Observable:
	def put(spieler: Boolean, line: Int, row: Int) = copy(field.put(spieler, line, row))
	def reset() = copy(field.reset())