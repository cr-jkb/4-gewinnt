package de.htwg.se.model.fieldComponent.fieldBaseImpl.difficulties

import de.htwg.se.model.fieldComponent.FieldInterface

case class mediumStrategy() extends de.htwg.se.model.fieldComponent.fieldBaseImpl.difficulties.Strategy {
    var plannedNext = 0
    override def put(field: FieldInterface) = {if(evalNext) plannedNext else evalNew}

    def evalNext:Boolean = { //If still best solution
        true
    }

    def evalNew:Int = { //try getting 3 and 4
        0
    }
}