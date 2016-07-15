package calculation.service.messaging

import java.util

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.serialization.Deserializer

/**
  * Created by mjkrumlauf on 7/15/16.
  */
class KafkaDeserializer extends Deserializer[Message] {
  private val mapper: ObjectMapper = new ObjectMapper

  override def configure(configs: util.Map[String, _], isKey: Boolean) = {}

  override def close() = {}

  override def deserialize(topic: String, data: Array[Byte]): Message = {
    if (null == data) null
    else mapper.readValue(data, classOf[Message])
  }
}
