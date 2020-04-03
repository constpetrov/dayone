package me.konstantinpetrov.convert.orgmode
import me.konstantinpetrov.convert.model.Entry

class OrgModeFormatter(textFormatter: TextFormatter, additionalFormatters: List[TextFormatter]) extends TextFormatter {
  override def format(entry: Entry): String = {
    val result = new StringBuilder()
    result.append(textFormatter.format(entry))
    for (formatter <- additionalFormatters) {
      result.append(formatter.format(entry))
    }
    result.mkString
  }
}
