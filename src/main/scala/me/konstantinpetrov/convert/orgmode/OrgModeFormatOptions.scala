package me.konstantinpetrov.convert.orgmode

case class OrgModeFormatOptions(dateTimeFormatter: OrgModeDateFormatter,
                                weatherFormatter: OrgModeWeatherFormatter,
                                locationFormatter: OrgModeLocationFormatter,
                                entryTextFormatter: OrgModeEntryTextFormatter) {

}
