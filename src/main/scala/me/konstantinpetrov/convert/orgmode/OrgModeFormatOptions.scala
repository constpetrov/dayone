package me.konstantinpetrov.convert.orgmode

import java.time.format.DateTimeFormatter

case class OrgModeFormatOptions(dateTimeFormatter: DateTimeFormatter,
                                weatherFormatter: OrgModeWeatherFormatter,
                                locationFormatter: OrgModeLocationFormatter,
                                entryTextFormatter: OrgModeEntryTextFormatter) {

}
