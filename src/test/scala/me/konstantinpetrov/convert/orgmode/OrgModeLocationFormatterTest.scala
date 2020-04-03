package me.konstantinpetrov.convert.orgmode

import java.time.{ZoneId, ZonedDateTime}
import java.util.TimeZone

import me.konstantinpetrov.convert.model.LocationField._
import me.konstantinpetrov.convert.model.{Coordinates, Entry, Location, Region}
import org.scalatest.FunSpec

private class OrgModeLocationFormatterTest extends FunSpec {
  describe("OrgModeLocationFormatter") {
    it("should format location to a human readable form") {
      val testLocation = Location(
        localityName = "Locality name",
        administrativeArea = "Administrative area",
        country = "Country",
        placeName = "Place name",
        region = Region(Coordinates(latitude = 10.0d, longitude = 20.0d), 5.0d))

      val subject = new OrgModeLocationFormatter("Taken at ", List(COUNTRY, PLACE))
      val result = subject.format(Entry(
        location = Some(testLocation),
        text = "",
        creationDate = ZonedDateTime.now,
        modifiedDate = ZonedDateTime.now,
        timeZone = TimeZone.getTimeZone(ZoneId.systemDefault())))

      assert(result == "Taken at Country, Place name\n")
    }

    it("should not write unknown location by default") {
      val subject = new OrgModeLocationFormatter("Taken at ", List(COUNTRY, PLACE))
      val result = subject.format(Entry(
        location = None,
        text = "",
        creationDate = ZonedDateTime.now,
        modifiedDate = ZonedDateTime.now,
        timeZone = TimeZone.getTimeZone(ZoneId.systemDefault())))

      assert(result == "")
    }

    it("should write unknown location if asked to") {
      val subject = new OrgModeLocationFormatter(prefix = "Taken at ", List(COUNTRY, PLACE), writeUnknowns = true)
      val result = subject.format(Entry(
        location = None,
        text = "",
        creationDate = ZonedDateTime.now,
        modifiedDate = ZonedDateTime.now,
        timeZone = TimeZone.getTimeZone(ZoneId.systemDefault())))

      assert(result == "Taken at unknown location\n")
    }
  }
}
