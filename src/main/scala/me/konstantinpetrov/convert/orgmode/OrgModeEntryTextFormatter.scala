package me.konstantinpetrov.convert.orgmode

import me.konstantinpetrov.convert.model.Entry

class OrgModeEntryTextFormatter(level: Int = 4,
                                maxWidth: Int = 80,
                                wordWrap: Boolean = true) {
  def format(entry: Entry): String = {
    var candidatePosition = 0
    var lastNewLinePosition = 0
    val lines = new StringBuilder
    for (i <- 0 until entry.text.length) {
      val candidate = entry.text.charAt(i)
      if (candidate == ' ' ||
        candidate == '.' ||
        candidate == ',') {
        candidatePosition = i
      }
      if (i - lastNewLinePosition >= maxWidth
        && candidatePosition > lastNewLinePosition) {
        lines.append(entry.text.substring(lastNewLinePosition, candidatePosition + 1).trim)
        lines.append('\n')
        lastNewLinePosition = candidatePosition
      }
      if (i == entry.text.length - 1) {
        lines.append(entry.text.substring(lastNewLinePosition, entry.text.length).trim)
      }
    }
    lines.toString()
  }

  def formatTags(entry: Entry): String = {
    entry.tags.size match {
      case 0 => ""
      case _ => entry.tags.mkString(":", ":", ":")
    }
  }

  def levelString(): String = {
    val result = new StringBuilder
    0 until level foreach result.append("*")
    result.append(" ")
    result.mkString
  }
}

// **** Coronavirus                        :Аня:Ева:здоровье:я:работа:Голландия: