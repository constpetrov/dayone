package me.konstantinpetrov.convert

import java.io.File

import org.scalatest.FunSpec

class JsonParserTest extends FunSpec {

  describe("A JsonParser") {

    it("should read and parse file") {
      val subject = new JsonParser()
      val result = subject.parse(new File("simple.json"))
      print(result)
    }

  }


}
