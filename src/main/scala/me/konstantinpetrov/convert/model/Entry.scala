package me.konstantinpetrov.convert.model

import java.time.ZonedDateTime
import java.util.TimeZone

case class Entry(text: String,
                 tags: Option[Seq[String]] = None,
                 timeZone: TimeZone,
                 creationDate: ZonedDateTime,
                 modifiedDate: ZonedDateTime,
                 location: Option[Location] = None,
                 weather: Option[Weather] = None)
