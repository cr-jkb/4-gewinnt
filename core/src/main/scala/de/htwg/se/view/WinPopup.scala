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
import java.awt.Image;
import scala.swing.event.MouseClicked
import javax.swing.BorderFactory
import de.htwg.se.controller.controllerComponent.ControllerInterface
import de.htwg.se.model.fieldComponent.fieldBaseImpl.Stone

case class winPop(controller: ControllerInterface) {

  def ret: Dialog = new Dialog() {
    modal = true
    centerOnScreen
    var winnerName = ""
    var winnerColor = Color.black
    if controller.getWinner().equals(Stone.X.toString()) then
      winnerName = "Yellow"
      winnerColor = Color.YELLOW
    else if (controller.getMode() == "singleplayer")
      winnerName = "Computer"
      winnerColor = Color.RED
    else
      winnerName = "Red"
      winnerColor = Color.RED
    title = "winner: " + winnerName

    contents = new BoxPanel(Orientation.Vertical) {
      minimumSize = new Dimension(450, 420)
      maximumSize = new Dimension(450, 420)
      preferredSize = new Dimension(450, 420)

      var winnerPic = new ImageIcon("res/Winner.jpg")
      var winnerImage: Image = winnerPic
        .getImage()
        .getScaledInstance(
          300,
          300,
          java.awt.Image.SCALE_SMOOTH
        )

      contents += new Label() {
        icon = new ImageIcon(winnerImage)
        /* setHorizontalAlignment = SwingConstants.CENTER; */
      }

      contents += new Label(
        winnerName + " has won!"
      ) {
        font = new Font("Arial", 3, 45)
        foreground = winnerColor
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
    }
    resizable = false
  }
}
