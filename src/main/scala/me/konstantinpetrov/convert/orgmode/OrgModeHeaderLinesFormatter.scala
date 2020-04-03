package me.konstantinpetrov.convert.orgmode

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import me.konstantinpetrov.convert.model.Entry

class OrgModeHeaderLinesFormatter extends TextFormatter {

  def createYearHeader(entry: Entry): String = {
    s"* ${DateTimeFormatter.ofPattern("yyyy").format(entry.creationDate)}\n"
  }

  def createMonthHeader(entry: Entry): String = {
    s"** ${DateTimeFormatter.ofPattern("yyyy-MM MMMM").format(entry.creationDate)}\n"
  }

  def createDayHeader(entry: Entry): String = {
    s"*** ${DateTimeFormatter.ofPattern("yyyy-MM-dd EEEE").format(entry.creationDate)}\n"
  }

  override def format(entry: Entry, previousDate: ZonedDateTime): String = {
    val result = new StringBuilder
    if (entry.creationDate.getYear > previousDate.getYear) {
      result.append(createYearHeader(entry))
    }
    if (entry.creationDate.getMonthValue > previousDate.getMonthValue ||
      entry.creationDate.getYear > previousDate.getYear) {
      result.append(createMonthHeader(entry))
    }
    if (entry.creationDate.getDayOfMonth > previousDate.getDayOfMonth ||
      entry.creationDate.getMonthValue > previousDate.getMonthValue ||
      entry.creationDate.getYear > previousDate.getYear) {
      result.append(createDayHeader(entry))
    }
    result.mkString
  }
}
