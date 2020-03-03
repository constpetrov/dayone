package me.konstantinpetrov.convert.model

case class Location(region: Region,
                    localityName: String,
                    country: String,
                    administrativeArea: String,
                    placeName: String)
