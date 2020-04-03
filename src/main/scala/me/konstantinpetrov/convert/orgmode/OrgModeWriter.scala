package me.konstantinpetrov.convert.orgmode

import java.io.{File, FileOutputStream, OutputStreamWriter}
import java.time.{Instant, ZoneId, ZonedDateTime}

import me.konstantinpetrov.convert.model.Entry
import me.konstantinpetrov.convert.orgmode.OrgModeField._

import scala.collection.mutable
import scala.util.Try

class OrgModeWriter(options: List[OrgModeField]) {
  def write(outputFile: File, entries: List[Entry]): Try[Unit] = {
    val formatters: mutable.Queue[TextFormatter] = new mutable.Queue[TextFormatter]()
    options foreach {
      case WEATHER => formatters.enqueue(new OrgModeWeatherFormatter)
      case LOCATION => formatters.enqueue(new OrgModeLocationFormatter())
      case DATE => formatters.enqueue(new OrgModeDateFormatter())
      case _ =>
    }
    val headerLinesFormatter: Option[OrgModeHeaderLinesFormatter] =
      if (options.contains(HEADER_DATE)) {
        Some(new OrgModeHeaderLinesFormatter)
      } else {
        None
      }

    var previousDate = ZonedDateTime.ofInstant(Instant.EPOCH, ZoneId.systemDefault())
    val formatter = new OrgModeFormatter(new OrgModeEntryTextFormatter(), formatters.toList)
    Try({
      val writer = new OutputStreamWriter(new FileOutputStream(outputFile))
      entries.foreach(entry => {
        val headerLines = headerLinesFormatter match {
          case Some(f) => f.format(entry, previousDate)
          case None => ""
        }
        val piece = formatter.format(entry)
        writer.write(headerLines)
        writer.write(piece)
        previousDate = entry.creationDate
      })
      writer.close()
    })
  }
}
