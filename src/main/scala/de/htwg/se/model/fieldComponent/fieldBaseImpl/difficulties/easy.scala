package de.htwg.se.model.fieldComponent.fieldBaseImpl.difficulties
import scala.util.Random
import de.htwg.se.model.fieldComponent.FieldInterface

case class easyStrategy() extends de.htwg.se.model.fieldComponent.fieldBaseImpl.difficulties.Strategy {
    override def put(field: FieldInterface) = {Random.nextInt(field.size2)}
}