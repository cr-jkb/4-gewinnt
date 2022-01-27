package de.htwg.se.aview

import org.scalatest.wordspec.AnyWordSpec

class GUISpec extends AnyWordSpec {
    "The GUI of 4-Gewinnt Gr15" should {
        "be scalable and resizable" in { }
        "be reactive and react to mouse clicks on field and options" in { }
        "be reactive and react to keyboard arrow presses by moving the selector or dropping a stone when enter" in { }
        "put stones using the controller" in { }
        "have 2 ToggleButtons for ModeSelect, 4 ToggleButtons for Difficulty Settings" in {}
        "have a menu bar consisting of SaveGame(Import, Export), NewGame, Quit/Exit" in {}
        "refresh according to the Observer Notify Subscriber Method" in {}
        "exit according to the Observer Kill Subscriber Method" in {} //FALSE
    }
}