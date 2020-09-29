package me.konstantinpetrov.convert.orgmode

import java.io.File

import me.konstantinpetrov.convert.model.Entry

import scala.util.matching.Regex

class OrgModeParser {

  val yearHeaderPattern: Regex = """\* (.*)""".r
  val monthHeaderPattern: Regex = """\*{2} (.*)""".r
  val dayHeaderPattern: Regex = """\*{3} (.*)""".r
  val entryHeaderPattern: Regex = """\*{4} (.*)""".r

  def parse(input: File): List[Entry] = {
    val source = scala.io.Source.fromFile(input)
    var year = 2000
    var month = 1
    var day = 1
    var entryHeaderWithTags = ""
    for (line <- source.getLines()) {

      line match {
        case yearHeaderPattern(value: String) => year = value.toInt //todo most probably this is wrong
        case monthHeaderPattern(value: String) => month = value.toInt//todo definitely wrong
        case dayHeaderPattern(value: String) => day = value.toInt//todo definitely wrong
        case entryHeaderPattern(value: String) => entryHeaderWithTags = value
      }

    }
    List.empty
  }

}

