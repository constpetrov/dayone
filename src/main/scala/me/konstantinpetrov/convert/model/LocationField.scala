package me.konstantinpetrov.convert.model

object LocationField extends Enumeration {
  type LocationField = Value
  val COUNTRY, PLACE, LATITUDE, LONGITUDE, LOCALITY, AREA = Value
}
