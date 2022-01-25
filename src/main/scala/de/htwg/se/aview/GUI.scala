package de.htwg.se.aview

import de.htwg.se.controller.controllerComponent.ControllerInterface
import scala.swing._
import de.htwg.se.util.Observer
import scala.swing.event._
import javax.swing.{BorderFactory, ImageIcon}
import scala.swing.Swing.LineBorder
import javax.imageio.ImageIO
import java.io.File

//GridPanels new GridPanel(rows, cols)

class GUI(controller: ControllerInterface) extends Observer:
  controller.add(this)
  //border = BorderFactory.createLineBorder(Color.BLACK, 5)
  var button = Array.ofDim[Button](controller.field.size, controller.field.size2)
  override def update: Unit = redraw()
  override def kill: Unit = System.exit(0)

  val MainIcon = ImageIO.read(new File("res/logo.jpg"))
  
  val frame = new Frame {
    title = "4-Gewinnt - Gruppe 15"
    override def closeOperation(): Unit = controller.quit
    iconImage = MainIcon

    val spielfeld = getField(controller.field.size, controller.field.size2)
    val selector = getSelector(controller.field.size)

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
      val ModeSelect = new GridPanel(2,1) {
        val playerRadioButton = new RadioButton("Singleplayer") {
          reactions += { case event.ButtonClicked(_) =>
            controller.setMode("player")
          }
        }
        playerRadioButton.preferredSize_=(new Dimension(30, 50))
        contents += playerRadioButton
        val computerRadioButton = new RadioButton("Multiplayer") {          
          reactions += { case event.ButtonClicked(_) =>
            controller.setMode("computer")
          }
        }
        computerRadioButton.preferredSize_=(new Dimension(30, 50))
        val gruppe1 = new ButtonGroup(playerRadioButton, computerRadioButton)
        gruppe1.select(playerRadioButton)
        contents += computerRadioButton
      }
      contents += ModeSelect
      
      val gameModes = new GridPanel(2,1) {
        val singlePlayerRB = new RadioButton("SinglePlayer") {
          enabled = false
        }
        val multiPlayerRB = new RadioButton("Multiplayer") {
          enabled = false
        }
        val gruppe2 = new ButtonGroup(singlePlayerRB, multiPlayerRB)
        gruppe2.select(singlePlayerRB)
        contents += singlePlayerRB
        contents += multiPlayerRB
      }
      contents += gameModes
    }

    val myMenu = new MenuBar {
      contents += new Menu("Options") {
        mnemonic = Key.O
        contents += new MenuItem(Action("New Game") { controller.newField })
        contents += new MenuItem(Action("Quit") {
        controller.quit
        close})
      }
    }
    


    val frameContents = new BoxPanel(Orientation.Vertical) {
      contents += myMenu
      contents += selector
      contents += spielfeld
      contents += optionen
    }

    contents = frameContents
    

    size = new Dimension(500, 500)
    centerOnScreen()
    open()
  }

  def redraw(): Unit =
    for (index <- 0 to controller.field.size - 1)
      for (index2 <- 0 to controller.field.size2 - 1)
        //button(index)(index2).icon = controller.field.get(index, index2).toString
        if (controller.field.get(index, index2).toString.equals("X")) {
          button(index)(index2).icon = new ImageIcon(ImageIO.read(new File("res/slotyellow.png")))
        } else if (controller.field.get(index, index2).toString.equals("O")) {
          button(index)(index2).icon = new ImageIcon(ImageIO.read(new File("res/slotred.png")))                    
        }

  //def closeOperation(): Unit =
    //controller.quit

  def getField(size: Int, size2: Int): GridPanel = 
    new GridPanel(size, size2) {
        //border = LineBorder(java.awt.Color.GRAY, 2)
        background = java.awt.Color.BLACK
        for (index <- 0 until controller.field.size;
            index2 <- 0 until controller.field.size2)
            button(index)(index2) = new Button(controller.field.get(index, index2).toString) {
              background = java.awt.Color.white
              icon = new ImageIcon(ImageIO.read(new File("res/slot.png")))
              reactions += { case event.ButtonClicked(_) =>
                controller.put(index, index2)
                //button(index)(index2).text = controller.field.get(index, index2).toString
                /*if (controller.field.get(index, index2).toString.equals("X")) {
                  icon = new ImageIcon(ImageIO.read(new File("res/slotyellow.png")))
                } else if (controller.field.get(index, index2).toString.equals("O")) {
                  icon = new ImageIcon(ImageIO.read(new File("res/slotred.png")))                    
                }*/
              }
            }
            contents += button(index)(index2)
      }
  
  def getSelector(sizeW: Int): GridPanel =
    new GridPanel(0, sizeW) {
      for (i <- 0 until sizeW)
        val select = new Label {
          icon = new ImageIcon(ImageIO.read(new File("res/red2-lq.png")))
        }
        contents += select
    }