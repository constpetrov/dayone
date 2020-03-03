package me.konstantinpetrov.convert.model

import java.time.ZonedDateTime
import java.util.TimeZone

case class Entry(text: String,
                 tags: Array[String],
                 timeZone: TimeZone,
                 creationDate: ZonedDateTime,
                 modifiedDate: ZonedDateTime,
                 location: Location,
                 weather: Weather)
