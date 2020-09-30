package me.konstantinpetrov.convert.orgmode

import java.io.File

import org.scalatest.FunSpec

class OrgModeParserTest extends FunSpec {
  describe("An OrgModeParser") {
    it("should parse simple file"){
      val subject = new OrgModeParser
      subject.parse(new File("src/test/resources/simple.org"))
    }
  }

}
