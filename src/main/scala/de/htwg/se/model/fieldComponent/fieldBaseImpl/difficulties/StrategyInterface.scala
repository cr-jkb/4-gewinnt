package de.htwg.se.model.fieldComponent.fieldBaseImpl.difficulties

import scala.compiletime.ops.int
import de.htwg.se.model.fieldComponent.FieldInterface

trait Strategy {
    def put(field : FieldInterface) : Int
}

//easy = random
//medium plays in center and often puts above the player - follows one win strategy
//hard blocks 2 connected stones and 3 connected stones (does not prevent schachmatt) - follows one win strategy
//invincible blocks 3 connected stones (predicts schachmatt pattern) - follows multiple win strategies