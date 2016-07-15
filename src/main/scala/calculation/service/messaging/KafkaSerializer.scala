package calculation.service.messaging

import java.util

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.serialization.Serializer

/**
  * Created by mjkrumlauf on 7/15/16.
  */
class KafkaSerializer extends Serializer[Message] {
  private val mapper: ObjectMapper = new ObjectMapper

  override def configure(configs: util.Map[String, _], isKey: Boolean) = {}

  override def serialize(topic: String, data: Message): Array[Byte] = {
    if (null == data) null
    else this.mapper.writeValueAsBytes(data)
  }

  override def close() = {}
}
