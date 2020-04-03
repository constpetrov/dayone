package me.konstantinpetrov.convert.model

import java.time.ZonedDateTime
import java.util.TimeZone

case class Entry(text: String,
                 tags: Seq[String] = Seq.empty,
                 timeZone: TimeZone,
                 creationDate: ZonedDateTime,
                 modifiedDate: ZonedDateTime,
                 location: Option[Location] = None,
                 weather: Option[Weather] = None)
