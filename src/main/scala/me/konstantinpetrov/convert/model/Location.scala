package me.konstantinpetrov.convert.model

case class Location(localityName: String,
                    country: String,
                    administrativeArea: String,
                    placeName: String,
                    longitude: Double,
                    latitude: Double)
