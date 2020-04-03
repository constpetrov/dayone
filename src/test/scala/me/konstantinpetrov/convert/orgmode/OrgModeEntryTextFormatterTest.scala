package me.konstantinpetrov.convert.orgmode

import java.time.{ZoneId, ZonedDateTime}
import java.util.TimeZone

import me.konstantinpetrov.convert.model.Entry
import org.scalatest.FunSpec

class OrgModeEntryTextFormatterTest extends FunSpec {

  describe("OrgModeTextFormatter") {
    it("should wrap text close to the desired width preserving words") {
      val subject = new OrgModeEntryTextFormatter(maxWidth = 15)
      val testEntry = Entry(text = "One swan on the bridge, testing, testing, everything seems to be in order.",
        creationDate = ZonedDateTime.now,
        modifiedDate = ZonedDateTime.now,
        timeZone = TimeZone.getTimeZone(ZoneId.systemDefault()))
      val result = subject.format(testEntry)
      assert(result ==
        """**** One swan
          |on the bridge,
          |testing,
          |testing,
          |everything
          |seems to be in
          |order.""".stripMargin)
    }

    it("should wrap text close to the desired width preserving words, adding tags") {
      val subject = new OrgModeEntryTextFormatter(maxWidth = 30)
      val testEntry = Entry(text = "One swan on the bridge, testing, testing, everything seems to be in order.",
        creationDate = ZonedDateTime.now,
        modifiedDate = ZonedDateTime.now,
        timeZone = TimeZone.getTimeZone(ZoneId.systemDefault()),
        tags = List("tag_1", "tag_2"))
      val result = subject.format(testEntry)
      assert(result ==
        """**** One swan on :tag_1:tag_2:
          |the bridge, testing, testing,
          |everything seems to be in
          |order.""".stripMargin)
    }
  }
}
