package de.htwg.se.model.fieldComponent.fieldBaseImpl

case class ErrorField(field: Field, error: String) {
  def returnField: Field = field
}

case class Error(errorMessage: String) {
  /* type errorMessage = String */
  def isEmpty(): Boolean = { errorMessage.length == 0 }
}

/* object Successful = new Error("") */

