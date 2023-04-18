package de.htwg.se
package fileIOComponent
import de.htwg.se.fieldComponent.FieldInterface

trait FileIOInterface {
  def load: FieldInterface
  def save(field: FieldInterface): Unit
}
