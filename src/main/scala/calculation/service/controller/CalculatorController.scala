package calculation.service.controller

import akka.actor.ActorRef
import calculation.service.domain.Add
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{RequestMapping, RestController}

/**
  * Created by mjkrumlauf on 7/11/16.
  */
@RestController
@RequestMapping(path = Array("/calculator/v1"))
class CalculatorController @Autowired()(private val calculator: ActorRef) {

  @RequestMapping(path = Array("/add"))
  def add() = calculator ! Add(2, 2)
}
