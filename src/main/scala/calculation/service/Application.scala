package calculation.service

import akka.actor.{ActorRef, ActorSystem, Props}
import calculation.service.domain.Calculator
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.{EnableAutoConfiguration, SpringBootApplication}
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}

/**
  * Created by mjkrumlauf on 5/14/16.
  */
object Application {

  def main(args: Array[String]) : Unit = {
    val applicationContext: ApplicationContext = SpringApplication.run(classOf[Config])
  }
}

@Configuration
@EnableAutoConfiguration
@ComponentScan
class Config {
  @Bean def actorSystem: ActorSystem = ActorSystem("CalculationSystem")
  @Bean def calculator: ActorRef = actorSystem.actorOf(Props(new Calculator))
}
