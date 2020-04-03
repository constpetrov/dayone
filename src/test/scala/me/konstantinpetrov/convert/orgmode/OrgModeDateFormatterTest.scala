package me.konstantinpetrov.convert.orgmode

import java.time.{ZoneId, ZonedDateTime}
import java.util.TimeZone

import me.konstantinpetrov.convert.model.Entry
import org.scalatest.FunSpec

private class OrgModeDateFormatterTest extends FunSpec {
  describe("OrgModeDateFormatter") {
    it("should format creation date to a human readable form") {

      val subject = new OrgModeDateFormatter("  Entered on ")
      val result = subject.format(Entry(
        location = None,
        text = "",
        creationDate = ZonedDateTime.of(2020, 4,3,12,18,4,3,ZoneId.systemDefault()),
        modifiedDate = ZonedDateTime.now,
        timeZone = TimeZone.getTimeZone(ZoneId.systemDefault())))

      assert(result == "  Entered on [2020-04-03 12:18]\n")
    }
  }
}
