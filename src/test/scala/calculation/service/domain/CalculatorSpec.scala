package calculation.service.domain

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class CalculatorSpec extends TestKit(ActorSystem("test")) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  val calculator = system.actorOf(Props(new Calculator))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "A Calculator" must {
    "add two numbers and return the sum" in {
      val (add1, add2) = (2, 5)
      this.calculator ! Add(add1, add2)
      expectMsg(AddResult(add1, add2, 7))
    }
  }

  it must {
    "subtract two numbers and return the difference" in {
      val (sub1, sub2) = (10, 7)
      this.calculator ! Subtract(sub1, sub2)
      expectMsg(SubtractResult(sub1, sub2, 3))
    }
  }

  it must {
    "multiply two numbers and return the product" in {
      val (mult1, mult2) = (7, 4)
      this.calculator ! Multiply(mult1, mult2)
      expectMsg(MultiplicationResult(mult1, mult2, 28))
    }
  }

  it must {
    "divide two numbers and return the quotient" in {
      val (dividend, divisor) = (3, 2)
      this.calculator ! Divide(dividend, divisor)
      expectMsg(DivisionResult(dividend, divisor, 1.5))
    }
  }
}
