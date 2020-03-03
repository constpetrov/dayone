package convert.model

case class Weather(temperatureCelsius: Double,
                   weatherServiceName: String,
                   windBearing: Double,
                   conditionsDescription: String,
                   pressureMB: Double,
                   visibilityKM: Double,
                   relativeHumidity: Double,
                   windSpeedKPH: Double,
                   weatherCode: String,
                   windChillCelsius: Double)
