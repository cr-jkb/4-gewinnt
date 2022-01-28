package de.htwg.se.aview

import scala.swing.{
  Dialog,
  Label,
  Dimension,
  Orientation,
  BoxPanel,
  Font,
  Button,
  event
}
import java.awt.Color
import javax.swing.ImageIcon
import scala.swing.event.MouseClicked
import javax.swing.BorderFactory
import de.htwg.se.controller.controllerComponent.ControllerInterface


case class winPop(controller: ControllerInterface) {

  def ret: Dialog = new Dialog() {
    modal = true
    centerOnScreen
    title = "winner: " + controller.getPlayer
    contents = new BoxPanel(Orientation.Vertical) {
      minimumSize = new Dimension(362, 420)
      maximumSize = new Dimension(362, 420)
      preferredSize = new Dimension(362, 420)
      contents += new Label() {
        icon = new ImageIcon("res/LogoFull.jpg")
      }
      contents += new Label(
        controller.getPlayer.toString + " won"
      ) {
        font = new Font("Arial", 3, 60)
        border = BorderFactory.createEmptyBorder(10, 0, 10, 0)
      }
      contents += new Label("restart game") {
        listenTo(mouse.clicks)
        font = new Font("Arial", 3, 60)
        foreground = Color.RED
        border = BorderFactory.createRaisedBevelBorder
        reactions += { case e: MouseClicked =>
          controller.newField
          dispose()
        }
      }
    }
    resizable = false
  }
}
