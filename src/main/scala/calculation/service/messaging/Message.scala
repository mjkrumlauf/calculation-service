package calculation.service.messaging

import calculation.service.domain.Jsonable

/**
  * Created by mjkrumlauf on 7/11/16.
  */
case class Message(val payload: Jsonable, val correlationId: String)
