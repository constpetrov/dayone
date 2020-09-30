package me.konstantinpetrov.convert.orgmode

import java.io.File

import org.scalatest.FunSpec

class OrgModeParserTest extends FunSpec {
  describe("An OrgModeParser") {
    it("should parse simple file") {
      val subject = new OrgModeParser
      val entry = subject.parse(new File("src/test/resources/simple.org")).head
      assert(entry.tags.get.size == 3)
      assert(entry.text == "Header text, not quite long. some entry text, several lines is better than one.")
      assert(entry.creationDate.getHour == 21)
      assert(entry.creationDate.getMinute == 11)
      assert(entry.creationDate.toString.startsWith("2016-01-09T21:11"))
    }

    it("should parse a file with several entries") {
      val subject = new OrgModeParser
      val entries = subject.parse(new File("src/test/resources/multiple-entries.org"))
      assert(entries.size == 4)
      assert(entries(2).text == "Line of heading and text, text, text, text, a bit more of text.")
      assert(entries(2).creationDate.getHour == 12)
      assert(entries(2).creationDate.getMinute == 51)
      assert(entries(2).creationDate.toString.startsWith("2016-03-01T12:51"))
    }
  }

}
