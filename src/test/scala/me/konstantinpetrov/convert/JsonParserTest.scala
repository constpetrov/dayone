package me.konstantinpetrov.convert

import java.io.File
import java.time.{ZoneId, ZonedDateTime}

import org.scalatest.FunSpec

class JsonParserTest extends FunSpec {

  describe("A JsonParser") {

    it("should read and parse a simple file") {
      val subject = new JsonParser()
      val result = subject.parse(new File("src/test/resources/simple.json"))
      assert(result.size == 2)
      assert(result.forall(e => e.tags.isEmpty))
      assert(result.forall(e => e.creationDate.isBefore(ZonedDateTime.of(2016, 1, 11, 0, 0, 0, 0, ZoneId.of("CET")))))
    }

    it("should read and parse a file with '\n'") {
      val subject = new JsonParser()
      val result = subject.parse(new File("src/test/resources/new-lines.json"))
      assert(result.size == 1)
      assert(result.head.text.contains("\n"))
    }

    it("should read and parse a file with '\"'") {
      val subject = new JsonParser()
      val result = subject.parse(new File("src/test/resources/quotes.json"))
      assert(result.size == 1)
      assert(result.head.text.contains("\""))
    }

  }

}
