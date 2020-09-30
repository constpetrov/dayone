package me.konstantinpetrov.convert.orgmode

import java.io.File
import java.time.{ZoneId, ZonedDateTime}
import java.util.TimeZone

import me.konstantinpetrov.convert.model.Entry

import scala.collection.mutable
import scala.util.matching.Regex

class OrgModeParser {

  val yearHeaderPattern: Regex = """\* ([\d]{4})""".r
  val monthHeaderPattern: Regex = """\*{2} [\d]{4}-([\d]{2}).*""".r
  val dayHeaderPattern: Regex = """\*{3} [\d]{4}-[\d]{2}-([\d]{2}).*""".r
  val entryHeaderPattern: Regex = """\*{4} (.*)""".r
  val entryExactDatePattern: Regex = """.*Entered on \[(.*)\]""".r

  def parse(input: File): List[Entry] = {
    val source = scala.io.Source.fromFile(input)
    parseLines(source.getLines())
  }

  private def getTagsFromHeader(entryHeaderWithTags: String) = {
    Some(entryHeaderWithTags.split(":").toList.tail)
  }

  private def createTextFromHeaderAndLines(entryHeaderWithTags: String, linesList: List[String]) = {
    val text = StringBuilder.newBuilder.append(entryHeaderWithTags.substring(0, entryHeaderWithTags.indexOf(":")))
    linesList.foreach(line => {
      if (text.endsWith(" ")) {
        text.append(line)
      } else {
        text.append(" ").append(line)
      }
    })
    text.toString()
  }

  private def parseLines(lines: Iterator[String]) = {
    var year = 2000
    var month = 1
    var day = 1
    var entryHeaderWithTags = ""
    var linesList: mutable.MutableList[String] = new mutable.MutableList[String]
    val entries: mutable.MutableList[Entry] = new mutable.MutableList[Entry]

    for (line <- lines) {

      line match {
        case yearHeaderPattern(value: String) => year = value.toInt
        case monthHeaderPattern(value: String) => month = value.toInt
        case dayHeaderPattern(value: String) => day = value.toInt
        case entryHeaderPattern(value: String) => entryHeaderWithTags = value
        case entryExactDatePattern(value: String) => {
          val tags = getTagsFromHeader(entryHeaderWithTags)
          val text = createTextFromHeaderAndLines(entryHeaderWithTags, linesList.toList)
          val local = Entry(text, tags, TimeZone.getTimeZone(ZoneId.systemDefault()), ZonedDateTime.now(), ZonedDateTime.now(), None, None)
          entries += local
        }
        case _ => linesList += line
      }

    }
    entries.toList
  }
}

