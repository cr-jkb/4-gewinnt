package de.htwg.se.model.fieldComponent.fieldBaseImpl.difficulties
import scala.util.Random
import de.htwg.se.model.fieldComponent.FieldInterface
import de.htwg.se.model.fieldComponent.fieldBaseImpl.Stone
object end extends Exception
object SpaceFound extends Exception
object FullRow extends Exception

case class easyStrategy() extends de.htwg.se.model.fieldComponent.fieldBaseImpl.difficulties.Strategy {
    override def put(field: FieldInterface) = {
        findRandom(field)
    }
    
    def findRandom(field: FieldInterface) : (Int, Int) = {
        var validPos = (0,0)
        try {            
            while(true) {
                globalY = Random.nextInt(field.size2)
                print(globalY)
                var randomPos = (matchx(Random.nextInt(field.size2), field), globalY)
                if (field.get(randomPos._1, randomPos._2) == Stone.Empty) { validPos = randomPos; throw end }
            }
            validPos
        } catch { case end => return validPos case _ => print("UNHANDLED EXCEPTION"); (-1, -1) }
    }
}