package de.htwg.se
package controller.controllerComponent

trait Observer: // Interface
  def update: Unit
  def kill: Unit

trait Observable: // Implemented Interface
  var subscribers: Vector[Observer] = Vector()
  def add(s: Observer) = { subscribers = subscribers :+ s }
  def remove(s: Observer) = {
    subscribers =
      subscribers.filterNot(o => o == s) // remove the one that's "not" matching
  }
  def notifyObservers = subscribers.foreach(o => o.update)
  def killObservers = subscribers.foreach(o => o.kill)
