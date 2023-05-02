package de.htwg.se.util

import scala.io.Source
import de.htwg.se.MainModule
import de.htwg.se.model.fieldComponent.FieldInterface
import play.api.libs.json._
import com.google.inject.Guice
import java.io._

object Util {
  def toJsonString(field: FieldInterface): String =
    Json.prettyPrint(fieldToJson(field))

  def fieldToJson(field: FieldInterface) = {
    Json.obj(
      "field" -> Json.obj(
        "mode" -> JsString(field.getMode()),
        "player" -> JsString(if (field.getPlayerState()) "true" else "false"),
        "cells" -> Json.toJson(
          for {
            row <- 0 until field.sizeOfDimY
            col <- 0 until field.sizeOfDimX
          } yield {
            Json.obj(
              "row" -> row,
              "col" -> col,
              "value" -> field.get(row, col).toString
            )
          }
        )
      )
    )
  }
  def jsonToField(source: String): FieldInterface = {
    val json: JsValue = Json.parse(source)
    val injector = Guice.createInjector(new MainModule)
    var field = injector.getInstance(classOf[FieldInterface])
    for (index <- 0 until field.sizeOfDimY * field.sizeOfDimX) {
      val row = (json \\ "row")(index).as[Int]
      val col = (json \\ "col")(index).as[Int]
      val value = (json \\ "value")(index).as[String]

      field = field.set(row, col, value)
    }
    field.setMode((json \\ "mode")(0).as[String])
    field.setPlayer((json \\ "player")(0).as[String])
    field
  }

}
