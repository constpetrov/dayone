package me.konstantinpetrov.convert.dayone

import java.io.File
import java.time.ZonedDateTime
import java.util.TimeZone

import me.konstantinpetrov.convert.model._
import upickle.default

class JsonParser {

  def parse(inputFile: File): List[Entry] = {
    implicit val timeZoneRW: default.ReadWriter[TimeZone] = upickle.default.readwriter[String].bimap[TimeZone](
      x => x.getDisplayName,
      str => TimeZone.getTimeZone(str)
    )
    implicit val zonedDateTimeRW: default.ReadWriter[ZonedDateTime] = upickle.default.readwriter[String].bimap[ZonedDateTime](
      x => x.toString,
      str => ZonedDateTime.parse(str)
    )
    implicit val coordinatesRW: default.ReadWriter[Coordinates] = upickle.default.macroRW[Coordinates]
    implicit val regionRW: default.ReadWriter[Region] = upickle.default.macroRW[Region]
    implicit val locationRW: default.ReadWriter[Location] = upickle.default.macroRW[Location]
    implicit val weatherRW: default.ReadWriter[Weather] = upickle.default.macroRW[Weather]
    implicit val entryRW: default.ReadWriter[Entry] = upickle.default.macroRW[Entry]
    val jsonString: String = os.read(os.Path(inputFile.getAbsolutePath))
    val data = ujson.read(jsonString)
    default.read[List[Entry]](data("entries"))
  }

}
