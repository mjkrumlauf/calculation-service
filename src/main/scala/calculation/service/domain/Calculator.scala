package calculation.service.domain

import akka.actor.Actor

class Calculator extends Actor {
  def receive = {
    case Add(n1, n2) =>
      println(s"Calculating $n1 + $n2")
      sender() ! AddResult(n1, n2, n1 + n2)
    case Subtract(n1, n2) =>
      println(s"Calculating $n1 - $n2")
      sender() ! SubtractResult(n1, n2, n1 - n2)
    case Multiply(n1, n2) =>
      println(s"Calculating $n1 * $n2")
      sender() ! MultiplicationResult(n1, n2, n1 * n2)
    case Divide(n1, n2) =>
      println(s"Calculating $n1 / $n2")
      sender() ! DivisionResult(n1, n2, n1 / n2)
    case Exponentiate(n1, n2) =>
      println(s"Calculating $n1 to the $n2 power")
      sender() ! ExponentiationResult(n1, n2, Math.pow(n1, n2))
  }
}

