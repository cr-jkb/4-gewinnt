package de.htwg.se.model.fieldComponent.fieldBaseImpl

import de.htwg.se.util.ModeStrategy
import de.htwg.se.model.fieldComponent.FieldInterface
import scala.compiletime.ops.boolean
import scala.collection.immutable.HashMap
import scala.collection.immutable.ListSet

case class PlayerModeStrategy() extends ModeStrategy {
  override def put(x: Int, y: Int, field: Field): ErrorField = {
    val mF = field.player.put(x, y, field) // hand parameters through to the next layer (which is player)
    //val won = determineWin(mF) //destroys field somehow (not handed through afterwards) probably because of the warning
    mF
  }
}