// Implemented 1-Instance Class for Field State

package de.htwg.se.model.fieldComponent.fieldBaseImpl

import de.htwg.se.model.fieldComponent.FieldInterface
import de.htwg.se.util.PlayerState
import de.htwg.se.util.ModeStrategy

case class Field(matrix: Matrix[Stone], var player: PlayerState, var mode: ModeStrategy) extends FieldInterface:

  def this(row: Int = 6, column: Int = 7, filling: Stone = Stone.Empty) = this(new Matrix(row, column, filling), TruePlayerState(), PlayerModeStrategy())

  val size = matrix.size //vertical
  val size2 = matrix.size2 //horizontal
  val eol = sys.props("line.separator")

  //--String Building:
  def bar(cellWidth: Int = 3, cellNum: Int = 3) : String = (("+" + "-" * cellWidth) * cellNum) + "+" + eol //barrier created horizontally (One Line Text)

  def cells(row: Int, cellWidth: Int = 3) : String = //slots created horizontally (One Line Text)
    matrix.row(row).map(_.toString).map(" " * ((cellWidth - 1) / 2) + _ + " " * ((cellWidth - 1) / 2)).mkString("|", "|", "|") + eol // .map = replace in Array with whats given in the parantheses. Each element given as placeholder

  def mesh(cellWidth: Int = 3) : String = //build Grid as String
    val trennLine = bar(cellWidth, size2)
    print(writeToFile("myStorage.xml", meshToXml()) + eol)
    //print(meshToJSON("myJson"))
    (0 until size).map(cells(_, cellWidth)).mkString(trennLine, trennLine, trennLine) // eine Linie am Anfang, jeder Eintrag wird getrennt durch Trennlinie und am Ende wird eine zusätzliche hinzugefügt

  def meshToXml() : String =
    var x = "<xml>" + eol
    for (i <- (0 until size2-1)) {
      x += matrix.row(i).map(_.toString).mkString("<row"+i+">", "|", "</row"+i+">"+eol)
    }    
    x + "</xml>"

  def meshFromXml(fileName : String) : Field =
    val file = scala.xml.XML.loadFile(fileName)
    var myField : Field = new Field
    for (i <- (size2-1 until 0)) {
      val rrow = (file \\ ("row"+i)).text.split("|")
      for (j <- (size-1 until 0)) {
        rrow(j) match {          
          case "X" => myField = copy(matrix.replaceCell(i, j, Stone.X))
          case "O" => myField = copy(matrix.replaceCell(i, j, Stone.O))
          case " " => myField = copy(matrix.replaceCell(i, j, Stone.Empty))
          case _ => print("XML parsing failed")
        }
      }
    }
    copy(matrix)

  def fullMeshToJSON(fileName : String) :String =
    import play.api.libs.json._
    //print(matrix)
    val jsonArrayofMesh = Json.toJson(matrix.getRows.map(_.toString))
    //Json.writes(jsonArrayofMesh)
    "x"

  def meshToJSON(fileName : String) :String =
    import play.api.libs.json._
    var jsonString = Json.toJson("")
    for (i <- (0 until size2-1)) {
      //jsonString. + Json.toJson("dh")

      val json: JsValue = Json.obj(
      "row" + i.toString -> matrix.row(i).map(_.toString).mkString("|"))

    }  
    //x + "</xml>"
    "x"

    /*//import play.api.libs.json._

    implicit val meshWrites = new Writes[this] { maybe works outside this class in controller
      def writes(location: Location) = Json.obj(
        "lat" -> location.lat,
        "long" -> location.long
      )
    } */


    //def meshFromJson : String = "x" FOLIE 20


  import java.io._
  def writeToFile(fileName : String, input : String) : String =    
    val pw = new PrintWriter(new File(fileName))
    pw.write(input)
    pw.close
    input

  override def toString = mesh()

  //--Mode Layer related:

  def put(x: Int, y: Int): Field =
    mode.put(x, y, this)

  def get(x: Int, y: Int): Stone = matrix.cell(x, y)

  def getPlayerState(): Boolean = if (player == TruePlayerState()) true else false

  def setMode(str: String): ModeStrategy =
    str match 
      case "player" => 
        mode = PlayerModeStrategy()
        mode
      case "computer" =>
        mode = ComputerModeStrategy()
        mode

  //--Field related:
  def undo(x: Int, y: Int): Field = //remove the stone at x,y
    if (player == TruePlayerState())
      player = FalsePlayerState()
    else
      player = TruePlayerState()
    copy(matrix.replaceCell(x, y, Stone.Empty)) //write empty Stone