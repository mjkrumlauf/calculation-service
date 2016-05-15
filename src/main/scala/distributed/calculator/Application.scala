package distributed.calculator

import akka.actor.{ActorRef, ActorSystem, Props}
import distributed.calculator.domain.CalculatorActor
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.{AnnotationConfigApplicationContext, Bean, Configuration}

/**
  * Created by mjkrumlauf on 5/14/16.
  */
@SpringBootApplication
object Application {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[Application])
  }
}

class Application {
  val appContext: ApplicationContext = new AnnotationConfigApplicationContext(classOf[Config])
}

@Configuration
class Config {
  @Bean def actorSystem: ActorSystem = ActorSystem("calculatorActor")
  @Bean def calculator: ActorRef = actorSystem.actorOf(Props(new CalculatorActor))
}
