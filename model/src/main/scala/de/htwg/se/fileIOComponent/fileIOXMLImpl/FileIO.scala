package de.htwg.se
package fileIOComponent.fileIOXMLImpl
/*import com.google.inject.Guice
import de.htwg.se.fileIOComponent.FileIOInterface
import de.htwg.se.fieldComponent.FieldInterface
import de.htwg.se.MainModule
import com.fasterxml.jackson.core.PrettyPrinter
import scala.xml.XML

class FileIO extends FileIOInterface {

  override def load: FieldInterface = {
    val file = scala.xml.XML.loadFile("res/field.xml")
    val injector = Guice.createInjector(new MainModule)
    var field = injector.getInstance(classOf[FieldInterface])
    val cellNodes = (file \\ "cell")
    for (cell <- cellNodes) {
      val row: Int = (cell \ "@row").text.toInt
      val col: Int = (cell \ "@col").text.toInt
      val value: String = (cell \ "@value").text
      field = field.set(row, col, value)
    }
    field.setMode((file \\ "field" \ "@mode").text)
    field.setPlayer((file \\ "field" \ "@player").text)
    field
  }

  override def save(field: FieldInterface): Unit =
    XML.save("res/field.xml", fieldToXml(field))

  def fieldToXml(field: FieldInterface): xml.Node = {
    <field mode= {field.getMode()} player={
      if (field.getPlayerState()) "true" else "false"
    }>
      {
      for {
        row <- 0 until field.sizeOfDimY
        col <- 0 until field.sizeOfDimX
      } yield cellToXml(field, row, col) // + sys.props("line.separator")
    }
    </field>
  }

  def cellToXml(field: FieldInterface, row: Int, col: Int) = {
    <cell row={row.toString} col={col.toString} value={
      field.get(row, col).toString
    }> {}
    </cell>

  }
}*/
