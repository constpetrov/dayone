package me.konstantinpetrov.convert

import java.io.File
import java.util.TimeZone

import me.konstantinpetrov.convert.model.Entry
import upickle.default

class JsonParser {

  def parse(inputFile: File): List[Entry] = {
    implicit val timeZoneRW: default.ReadWriter[TimeZone] = upickle.default.macroRW[TimeZone]
    implicit val entryRW: default.ReadWriter[Entry] = upickle.default.macroRW[Entry]
    val jsonString: String = os.read(os.Path(File))
    val data = ujson.read(jsonString)
    default.read[List[Entry]](data(0)("entries"))
  }

}
