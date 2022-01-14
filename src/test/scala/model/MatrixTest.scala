package de.htwg.se.model.fieldComponent.fieldBaseImpl

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class MatrixSpec extends AnyWordSpec {
  "A Matrix is a tailor-made immutable data type that contains a two-dimentional Vector of something. A Matrix" when {
    "empty " should {
      "be created by using a dimention and a sample cell" in {
        val matrix = new Matrix[String](2, 2, "x")
        matrix.size should be(2)
      }
      "for test purposes only be created with a Vector of Vectors" in {
        val testMatrix = Matrix(Vector(Vector("x")))
        testMatrix.size should be(1)
      }

    }
    "filled" should {
      val matrix = new Matrix[String](2, 2, "x")
      "give access to its cells" in {
        matrix.cell(0, 0) should be("x")
      }
      "replace cells and return a new data structure" in {
        val returnedMatrix = matrix.replaceCell(0, 0, "o")
        matrix.cell(0, 0) should be("x")
        returnedMatrix.cell(0, 0) should be("o")
      }
      "be filled using fill operation" in {
        val returnedMatrix = matrix.fill("x")
        returnedMatrix.cell(0, 0) should be("x")
      }
    }
  }
}
