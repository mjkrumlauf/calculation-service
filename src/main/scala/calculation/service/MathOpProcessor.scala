package calculation.service

import akka.actor.{ActorRef, ActorSystem}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Sink, Source}
import calculation.service.messaging.{KafkaDeserializer, KafkaSerializer, Message}
import com.softwaremill.react.kafka.{ConsumerProperties, ProducerMessage, ProducerProperties, ReactiveKafka}
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.{Deserializer, Serializer, StringDeserializer, StringSerializer}
import org.reactivestreams.{Publisher, Subscriber}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * Created by mjkrumlauf on 7/11/16.
  */
@Component
class MathOpProcessor @Autowired()(
  implicit private val actorSystem: ActorSystem,
  private val calculator: ActorRef
)  {

  implicit val materializer = ActorMaterializer()

  val kafka = new ReactiveKafka()
  val publisher: Publisher[ConsumerRecord[String, Message]] = kafka.consume(ConsumerProperties(
    bootstrapServers = "localhost:9092",
    topic = "ingoing-math-op",
    groupId = "group-math-op",
    keyDeserializer = new StringDeserializer,
    valueDeserializer = new KafkaDeserializer().asInstanceOf[Deserializer[Message]]
  ))
  val subscriber: Subscriber[ProducerMessage[String, Message]] = kafka.publish(ProducerProperties(
    bootstrapServers = "localhost:9092",
    topic = "outgoing-math-result",
    keySerializer = new StringSerializer,
    valueSerializer = new KafkaSerializer().asInstanceOf[Serializer[Message]]
  ))

  Source.fromPublisher(publisher)
    .map(consumerRecord =>
      ProducerMessage(consumerRecord.value().correlationId, consumerRecord.value()))
    .to(Sink.fromSubscriber(subscriber)).run()
}
