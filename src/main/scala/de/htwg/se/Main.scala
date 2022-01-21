// Main Class
package de.htwg.se

import aview.TUI
import aview.GUI
import controller.controllerComponent.ControllerInterface
import com.google.inject.Guice
@main
def main: Unit = {
  val injector = Guice.createInjector(new MainModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val gui = GUI(controller)
  val tui = TUI(controller)
}

def myFunc =
  import java.io._
  val pw = new PrintWriter(new File("abc.txt"))
  pw.write("example")
  pw.close

 /* val source:String = Source.fromFile("myfile.json").getLines.mkString
  val json : JsValue = Json.parse(source)

def fromXML(node: scala.xml.Node) : Array[Int] = {
  val name = (node \ "name").text //.toDouble
  val file = scala.xml.XML.LoadFile("myFile.xml") */
