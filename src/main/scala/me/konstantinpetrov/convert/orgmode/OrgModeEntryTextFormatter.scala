package me.konstantinpetrov.convert.orgmode

import me.konstantinpetrov.convert.model.Entry

class OrgModeEntryTextFormatter(level: Int = 4,
                                maxWidth: Int = 80,
                                wordWrap: Boolean = true) {
  def format(entry: Entry): String = {
    val tags = formatTags(entry)

    var firstLine = true
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
      if (i - lastNewLinePosition >= calculateMaxWidth(firstLine, tags, level)
        && candidatePosition > lastNewLinePosition) {
        if (firstLine) {
          lines.append(levelString())
        }
        lines.append(entry.text.substring(lastNewLinePosition, candidatePosition + 1).trim)
        if (firstLine) {
          lines.append(spaces(lines.size, tags.size))
          lines.append(tags)
        }
        lines.append('\n')
        firstLine = false
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
      case _ => entry.tags.mkString(" :", ":", ":")
    }
  }

  def levelString(): String = {
    val result = new StringBuilder()
    for (_ <- 0 until level) result.append("*")
    result.append(" ")
    result.mkString
  }

  def calculateMaxWidth(firstLine: Boolean, tags: String, level: Int): Int = {
    if (firstLine) {
      maxWidth - level - 1 - tags.length
    } else {
      maxWidth
    }
  }

  def spaces(lineLength: Int, tagsLength: Int): String = {
    if (tagsLength == 0) return ""

    val spaces = new StringBuilder
    for (_ <- 0 until (maxWidth - lineLength - tagsLength)) spaces.append(" ")
    spaces.mkString
  }
}

