package me.konstantinpetrov.convert.orgmode

import me.konstantinpetrov.convert.model.LocationField._
import me.konstantinpetrov.convert.model.{Entry, Location}

class OrgModeLocationFormatter(prefix: String = "  Taken at ", order: List[LocationField] = List(COUNTRY, PLACE),
                               itemSeparator: String = ", ", writeUnknowns: Boolean = false) extends TextFormatter {

  private def formatLocation(location: Location): String = {
    order.map {
      case AREA => location.administrativeArea
      case COUNTRY => location.country
      case PLACE => location.placeName
      case LOCALITY => location.localityName
      case LATITUDE => s"latitude: ${location.latitude}"
      case LONGITUDE => s"longitude: ${location.longitude}"
    }.mkString(prefix, itemSeparator, "\n")
  }

  private def unknownLocation(): String = {
    if (writeUnknowns) {
      s"${prefix}unknown location\n"
    } else {
      ""
    }
  }

  override def format(entry: Entry): String = {
    entry.location match {
      case None => unknownLocation()
      case Some(location) => formatLocation(location)
    }
  }

}
