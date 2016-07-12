package calculation.service.domain

abstract class MathOp(op: String)

object ops {
  val AddOp = "+"
  val SubtractOp = "-"
  val MultOp = "*"
  val DivOp = "/"
  val ExpOp = "^"
}

import ops._

case class Add(nbr1: Int, nbr2: Int) extends MathOp(AddOp)
case class Subtract(nbr1: Int, nbr2: Int) extends MathOp(SubtractOp)
case class Multiply(nbr1: Int, nbr2: Int) extends MathOp(MultOp)
case class Divide(nbr1: Double, nbr2: Int) extends MathOp(DivOp)
case class Exponentiate(base: Double, exponent: Double) extends MathOp(ExpOp)

abstract class MathResult(op: String)

case class AddResult(nbr: Int, nbr2: Int, result: Int) extends MathResult(AddOp)
case class SubtractResult(nbr1: Int, nbr2: Int, result: Int) extends MathResult(SubtractOp)
case class MultiplicationResult(nbr1: Int, nbr2: Int, result: Int) extends MathResult(MultOp)
case class DivisionResult(nbr1: Double, nbr2: Int, result: Double) extends MathResult(DivOp)
case class ExponentiationResult(base: Double, exponent: Double, result: Double) extends MathResult(ExpOp)

