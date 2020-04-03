package me.konstantinpetrov.convert.model

case class Location(localityName: Option[String],
                    country: Option[String],
                    administrativeArea: Option[String],
                    placeName: Option[String],
                    longitude: Option[Double],
                    latitude: Option[Double])
