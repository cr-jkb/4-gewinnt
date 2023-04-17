// Interface for tracking Stone Symbols (whose turn it is) - will be used in model/fieldComponent/______PlayerState.scala {2}
package de.htwg.se.model.fieldComponent

import de.htwg.se.model.fieldComponent.fieldElements.Game

trait PlayerState {
  var player1Name :String;
  var player2Name :String;

  def put(x: Int, y: Int, field: Game): (Game, Error)
}