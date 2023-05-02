package de.htwg.se
package fileIOComponent

import play.api.libs.json.JsValue

trait FileIOInterface {
  def load: JsValue
  def save(json: JsValue): Unit
}
