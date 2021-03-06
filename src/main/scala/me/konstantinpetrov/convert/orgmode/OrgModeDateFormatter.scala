package me.konstantinpetrov.convert.orgmode

import java.time.format.DateTimeFormatter

import me.konstantinpetrov.convert.model.Entry

class OrgModeDateFormatter(prefix: String = "  Entered on ", formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd E HH:mm")) extends TextFormatter {
  override def format(entry: Entry): String = {
    s"$prefix[${entry.creationDate.format(formatter)}]\n"
  }
}
