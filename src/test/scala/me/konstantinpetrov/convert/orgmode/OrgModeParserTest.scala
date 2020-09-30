package me.konstantinpetrov.convert.orgmode

import java.io.File

import org.scalatest.FunSpec

class OrgModeParserTest extends FunSpec {
  describe("An OrgModeParser") {
    it("should parse simple file"){
      val subject = new OrgModeParser
      val entry = subject.parse(new File("src/test/resources/simple.org")).head
      assert(entry.tags.get.size == 3)
      assert(entry.text == "Header text, not quite long. some entry text, several lines is better than one.")
    }
  }

}
