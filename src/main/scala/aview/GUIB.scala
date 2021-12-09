package aview

import controller.Controller
import model.Stone
import model.Move
import util.Observer
import scala.util.{Try,Success,Failure}
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.Color
import java.awt.Image.ImageConsumer
import java.awt.Image
import javax.swing.{JFrame, JButton, JPanel, JGridPanel}
import scala.List
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem

object myGUI extends App {

    val frame = new JFrame("4Gewinnt - Gruppe 15")
    val gameBoard = new GridPanel
    
    frame.getContentPane.add(gameBoard, BorderLayout.CENTER)
    gameBoard.setBackground(Color.green)
    var left = 0
    var top = 0
    var standardWidth = 50
    var standardHeight = standardWidth
    val fields = new model.Matrix[JPanel]
    for i <- 0 until 10:
        val field = new JPanel
        field.setBackground(color.gray)
        gameBoard.add(field)
        field.setSize(standardWidth, standardHeight)
        //field.setLocationRelativeTo()
        left += field.width
        if (i%6 = 0)
            top += field.height
        fields[i] = field
    
    //gameBoard.add()
    // frame.setDefaultCloseOperation()

    val myMenu = new JMenu
    val myMenuBar = new JMenuBar
    val mItem = new JMenuItem
    myMenu.add()

    mItem //undo, redo as image button?
    //quit

    //disabled RadioButtons? for Multiplayer

    frame.setSize(new Dimension(600, 400))
    frame.setLocationRelativeTo(null)
    frame.setVisible(true)


    def createGameBoardPanels(Matrix) {

    }
}