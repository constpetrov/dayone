package me.konstantinpetrov.convert.orgmode

import me.konstantinpetrov.convert.model.LocationField._
import me.konstantinpetrov.convert.model.{Entry, Location}

class OrgModeLocationFormatter(prefix: String, order: List[LocationField],
                               itemSeparator: String = ", ", writeUnknowns: Boolean = false) {

  def formatLocation(location: Location): String = {
    order.map {
      case AREA => location.administrativeArea
      case COUNTRY => location.country
      case PLACE => location.placeName
      case LOCALITY => location.localityName
      case LATITUDE => s"latitude: ${location.region.center.latitude}"
      case LONGITUDE => s"longitude: ${location.region.center.longitude}"
      case RADIUS => s"radius: ${location.region.radius}"
    }.mkString(prefix, itemSeparator, "\n")
  }

  def unknownLocation(): String = {
    if (writeUnknowns) {
      s"${prefix}unknown location\n"
    } else {
      ""
    }
  }

  def format(entry: Entry): String = {
    entry.location match {
      case None => unknownLocation()
      case Some(location) => formatLocation(location)
    }
  }

}
