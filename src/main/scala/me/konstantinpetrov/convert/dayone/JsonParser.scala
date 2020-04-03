package me.konstantinpetrov.convert.dayone

import java.io.File
import java.time.ZonedDateTime
import java.util.TimeZone

import me.konstantinpetrov.convert.model._
import spray.json._

class JsonParser {

  def parse(inputFile: File): List[Entry] = {
    def deserializationError(str: String): Nothing = {
      throw new Exception(str)
    }

    implicit object TimeZoneFormat extends JsonFormat[TimeZone] {
      def write(m: TimeZone): JsString = JsString(s"${m.toString}")

      def read(json: JsValue): TimeZone = json match {
        case JsString(c) => TimeZone.getTimeZone(c)
        case _ => deserializationError("String expected")
      }
    }

    implicit object ZonedDateTimeFormat extends JsonFormat[ZonedDateTime] {
      def write(m: ZonedDateTime): JsString = JsString(s"${m.toString}")

      def read(json: JsValue): ZonedDateTime = json match {
        case JsString(c) => ZonedDateTime.parse(c)
        case _ => deserializationError("String expected")
      }
    }

    object MyJsonProtocol extends DefaultJsonProtocol {
      implicit val locationFormat: RootJsonFormat[Location] = jsonFormat6(Location)
      implicit val weatherFormat: RootJsonFormat[Weather] = jsonFormat10(Weather)
      implicit val entryFormat: RootJsonFormat[Entry] = jsonFormat7(Entry)
      implicit val metaFormat: RootJsonFormat[Meta] = jsonFormat1(Meta)
      implicit val mainFormat: RootJsonFormat[MainModel] = jsonFormat2(MainModel)
    }


    import MyJsonProtocol._
    val source = scala.io.Source.fromFile(inputFile)
    val jsonString = try source.mkString finally source.close()

    val data = jsonString.parseJson
    data.convertTo[MainModel].entries
  }

}
