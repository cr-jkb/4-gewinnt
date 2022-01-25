package de.htwg.se.aview

import de.htwg.se.controller.controllerComponent.ControllerInterface
import scala.swing._
import de.htwg.se.util.Observer
import scala.swing.event._
import javax.swing.{BorderFactory, ImageIcon}
import scala.swing.Swing.LineBorder
import javax.imageio.ImageIO
import scala.util.{Try, Success, Failure}
//import scala.language.postfixOps
import java.io.File
import scala.swing.{
  Alignment,
  Label,
  Orientation,
  GridPanel,
  FlowPanel,
  BoxPanel,
  Dimension
}

//GridPanels new GridPanel(rows, cols)
val empty = new ImageIcon(ImageIO.read(new File("res/empty.png")))
val selector_red = new ImageIcon(ImageIO.read(new File("res/red2-lq.png")))
val selector_yellow = new ImageIcon(ImageIO.read(new File("res/yellow2-lq.png")))
var selector_visible:Boolean = false
var selector_pos = 0

class GUI(controller: ControllerInterface) extends Observer:
  controller.add(this)
  //border = BorderFactory.createLineBorder(Color.BLACK, 5)
  var button = Array.ofDim[Label](controller.field.size, controller.field.size2)
  var selectors = Array.ofDim[Label](controller.field.size2)
  override def update: Unit = redraw()
  override def kill: Unit = {
    //task.cancel() 
    System.exit(0)
  }

  val task = new java.util.TimerTask {
      def run() = println(s"Beep! ${selector_pos}")
      /*try {
      if (selector_visible) {
        selectors(selector_pos).icon = selector_red
      } else {
        selectors(selector_pos).icon = empty
      }*/
      selector_visible = !selector_visible /*} match {
        case f: Failure[String] => "fail" }*/
  }

  val MainIcon = ImageIO.read(new File("res/logo.jpg"))
  
  val frame = new Frame {
    title = "4-Gewinnt - Gruppe 15"
    override def closeOperation(): Unit = controller.quit
    iconImage = MainIcon
    minimumSize = new java.awt.Dimension(900,1000)
    centerOnScreen

    val spielfeld = getField(controller.field.size, controller.field.size2)
    val selector = getSelector(controller.field.size2)
    
    val t = new java.util.Timer()
    //t.schedule(task, 1000L, 1000L)
    

    val optionen = new GridPanel(2, 2) {
      border = LineBorder(java.awt.Color.WHITE, 20)
      background = java.awt.Color.WHITE
      val undoButton = new Button("Undo") {
        icon = new ImageIcon(ImageIO.read(new File("res/undo.png")))
        reactions += { case event.ButtonClicked(_) =>
          controller.undo
        }
      }
      undoButton.preferredSize_=(new Dimension(30,50))
      contents += undoButton
      val redoButton = new Button("Redo") { 
        icon = new ImageIcon(ImageIO.read(new File("res/redo2.png")))
        reactions += { case event.ButtonClicked(_) =>
          controller.redo
        }
      }
      redoButton.preferredSize_=(new Dimension(30,50))
      contents += redoButton
      val ModeSelect = new GridPanel(1,2) {
        border = BorderFactory.createEmptyBorder(0,20,10,0)
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
        //listenTo(keys)
        reactions += { 
          case event.KeyPressed(_, Key.Left, _, _) => moveSelLeft
          case event.KeyPressed(_, Key.Right, _, _) => moveSelRight
          case KeyPressed(_, Key.Enter, _, _) => putSel
        }
      }
      contents += ModeSelect
      
      val gameModes = new GridPanel(1,2) {
        border = BorderFactory.createEmptyBorder(20, 50, 0, 0)
        val easyRB = new RadioButton("Easy") {
          enabled = false
        }
        val mediumRB = new RadioButton("Medium") {
          enabled = false
        }
        val hardRB = new RadioButton("Hard") {
          enabled = false
        }
        val fullRB = new RadioButton("Invincible") {
          enabled = false
        }
        //val gruppe2 = new ButtonGroup(singlePlayerRB, multiPlayerRB)
        //gruppe2.select(singlePlayerRB)
        contents += easyRB
        contents += mediumRB
        contents += hardRB
        contents += fullRB
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
      contents += new Menu("Difficulty") {
        mnemonic = Key.D
        contents += new MenuItem(Action("Easy") { controller.newField })
        contents += new MenuItem(Action("Medium") { controller.quit })
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
        } else {
          button(index)(index2).icon = new ImageIcon(ImageIO.read(new File("res/slot2.png")))
        }

  //def closeOperation(): Unit =
    //controller.quit

  def getField(size: Int, size2: Int): GridPanel = 
    new GridPanel(size, size2) {
        //border = LineBorder(java.awt.Color.GRAY, 2)
        background = new Color(0,131,255)
        for (index <- 0 until controller.field.size;
            index2 <- 0 until controller.field.size2)
            button(index)(index2) = new Label(controller.field.get(index, index2).toString) {
              //background = java.awt.Color.white
              //icon = new ImageIcon(ImageIO.read(new File("res/slot.png")))
              listenTo(mouse.clicks)
              reactions += { case m: MousePressed => 
                controller.put(index, index2)
              }
              reactions += { case event.ButtonClicked(_) =>
                
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

  def putSel = controller.put(0, selector_pos)
  
  def getSelector(sizeW: Int): GridPanel =
    new GridPanel(0, sizeW) {
      for (i <- 0 until sizeW)
        selectors(i) = new Label {
          icon = empty //new ImageIcon(ImageIO.read(new File("res/red2-lq.png")))
        }
        contents += selectors(i)
    }
  /*  
  def moveSelLeft: Int =
    if (selector_pos > 0) selector_pos--

  def moveSelRight: Int =
    if (selector_pos > 0) selector_pos++*/

  def moveSelLeft =
    //optionally pause timer here
    if (selector_pos > 0) {
      selectors(selector_pos).icon = empty
      selector_pos = selector_pos -1      
    }
    updateSelImg
  
  def updateSelImg = selectors(selector_pos).icon = selector_red

  def moveSelRight =
    if (selector_pos < controller.field.size2) {
      selectors(selector_pos).icon = empty
      selector_pos = selector_pos +1
    }
    updateSelImg
  