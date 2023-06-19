package de.htwg.se

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
import de.htwg.se.model.fieldComponent.fieldBaseImpl.Stone

case class winPop(controller: ControllerInterface) {

  def ret: Dialog = new Dialog() {
    modal = true
    centerOnScreen
    var winnerColor = ""
    if controller.getWinner().equals(Stone.X.toString()) then
      winnerColor = "Yellow"
    else winnerColor = "Red"
    title = "winner: " + winnerColor
    contents = new BoxPanel(Orientation.Vertical) {
      minimumSize = new Dimension(450, 420)
      maximumSize = new Dimension(450, 420)
      preferredSize = new Dimension(450, 420)

      contents += new Label(
        winnerColor + " has won!"
      ) {
        font = new Font("Arial", 3, 50)
        if winnerColor.equals("Red") then foreground = Color.RED
        else foreground = Color.YELLOW
        border = BorderFactory.createEmptyBorder(10, 0, 10, 0)
      }
      contents += new Label("restart game") {
        listenTo(mouse.clicks)
        font = new Font("Arial", 3, 30)
        foreground = Color.BLACK
        border = BorderFactory.createRaisedBevelBorder
        reactions += { case e: MouseClicked =>
          controller.newField
          dispose()
        }
      }
      contents += new Label() {
        icon = new ImageIcon("res/LogoFull.jpg")
      }
    }
    resizable = false
  }
}
