package aview

import controller.Controller
import scala.swing._
import util.Observer
import scala.swing.event._
import scala.swing.Swing.LineBorder

class GUI(controller: Controller) extends Observer:
  controller.add(this)

  var button = Array.ofDim[Button](controller.field.size, controller.field.size2)
  override def update: Unit = redraw()
  val frame = new Frame {
    title = "4-Gewinnt"

    val spielfeld = new GridPanel(controller.field.size, controller.field.size2) {
      border = LineBorder(java.awt.Color.GRAY, 2)
      background = java.awt.Color.GRAY
      for (index <- 0 to controller.field.size - 1)
        for (index2 <- 0 to controller.field.size2 - 1)
          button(index)(index2) = new Button(controller.field.get(index, index2).toString) {
            reactions += { case event.ButtonClicked(_) =>
              controller.put(index, index2)
              button(index)(index2).text = controller.field.get(index, index2).toString
            }
          }
          contents += button(index)(index2)
    }

    val optionen = new GridPanel(2, 2) {
      border = LineBorder(java.awt.Color.WHITE, 20)
      background = java.awt.Color.WHITE
      val undoButton = new Button("Undo") {
        reactions += { case event.ButtonClicked(_) =>
          controller.undo
        }
      }
      undoButton.preferredSize_=(new Dimension(30,50))
      contents += undoButton
      val redoButton = new Button("Redo") {
        reactions += { case event.ButtonClicked(_) =>
          controller.redo
        }
      }
      redoButton.preferredSize_=(new Dimension(30,50))
      contents += redoButton
      val playerRadioButton = new RadioButton("Player") {
        reactions += { case event.ButtonClicked(_) =>
          controller.setMode("player")
        }
      }
      playerRadioButton.preferredSize_=(new Dimension(30, 50))
      contents += playerRadioButton
      val computerRadioButton = new RadioButton("Computer") {
        reactions += { case event.ButtonClicked(_) =>
          controller.setMode("computer")
        }
      }
      computerRadioButton.preferredSize_=(new Dimension(30, 50))
      val gruppe1 = new ButtonGroup(playerRadioButton, computerRadioButton)
      gruppe1.select(playerRadioButton)
      contents += computerRadioButton
    }

    contents = new GridPanel(2, 1) {
      contents += spielfeld
      contents += optionen
    }
    size = new Dimension(500, 500)
    centerOnScreen()
    open()
  }

  def redraw(): Unit =
    for (index <- 0 to controller.field.size - 1)
      for (index2 <- 0 to controller.field.size2 - 1)
        button(index)(index2).text = controller.field.get(index, index2).toString