  package util

  import de.htwg.se.util.Observer

  trait Observer: // Interface
    def update: Unit

    def kill: Unit

  trait Observable: // Implemented Interface as Object
    private var subscribers: Vector[Observer] = Vector()

    def add(s: Observer) = {
      subscribers = subscribers :+ s
    }

    def remove(s: Observer) = {
      subscribers =
        subscribers.filter(o => o != s)
    }

    def notifyObservers = subscribers.foreach(o => o.update)

    def killObservers = subscribers.foreach(o => o.kill)
/*
}*/
