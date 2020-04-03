package me.konstantinpetrov.convert.orgmode

import java.nio.file.Files
import java.time.{ZoneId, ZonedDateTime}
import java.util.TimeZone

import me.konstantinpetrov.convert.model.Entry
import org.scalatest.FunSpec

class OrgModeWriterTest extends FunSpec {
  describe("OrgModeWriter") {

    it("should write entries to file") {
      val file = Files.createTempFile("testing", "org").toFile
      val firstDate = ZonedDateTime.of(2020, 2, 1, 15, 33, 0, 0, ZoneId.systemDefault())
      val secondDate = ZonedDateTime.of(2020, 2, 3, 15, 33, 0, 0, ZoneId.systemDefault())
      val thirdDate = ZonedDateTime.of(2020, 3, 3, 15, 33, 0, 0, ZoneId.systemDefault())
      val enties = List(
        Entry(text = "First entry",
          creationDate = firstDate,
          modifiedDate = firstDate,
          timeZone = TimeZone.getTimeZone(ZoneId.systemDefault())
        ),
        Entry(text = "Second entry",
          creationDate = secondDate,
          modifiedDate = secondDate,
          timeZone = TimeZone.getTimeZone(ZoneId.systemDefault())
        ),
        Entry(text = "Third entry",
          creationDate = thirdDate,
          modifiedDate = thirdDate,
          timeZone = TimeZone.getTimeZone(ZoneId.systemDefault()))
      )

      val subject = new OrgModeWriter(List(OrgModeField.HEADER_DATE, OrgModeField.DATE))

      subject.write(file, enties)

      assert(file.exists())

      val contents: String = os.read(os.Path(file.getAbsolutePath))
      assert(contents ==
        """* 2020
          |** 2020-02 February
          |*** 2020-02-01 Saturday
          |**** First entry
          |  Entered on [2020-02-01 Sat 15:33]
          |
          |*** 2020-02-03 Monday
          |**** Second entry
          |  Entered on [2020-02-03 Mon 15:33]
          |
          |** 2020-03 March
          |*** 2020-03-03 Tuesday
          |**** Third entry
          |  Entered on [2020-03-03 Tue 15:33]
          |
          |""".stripMargin)
    }
  }
}
