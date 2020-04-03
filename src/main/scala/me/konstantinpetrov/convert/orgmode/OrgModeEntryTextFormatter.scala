package me.konstantinpetrov.convert.orgmode

import me.konstantinpetrov.convert.model.Entry

class OrgModeEntryTextFormatter(level: Int = 4,
                                maxWidth: Int = 80,
                                wordWrap: Boolean = true) extends TextFormatter {
  override def format(entry: Entry): String = {
    val tags = formatTags(entry)

    var firstLine = true
    var candidatePosition = 0
    var lastNewLinePosition = 0
    val lines = new StringBuilder
    val text = entry.text.replace("\\", "")
      .replace("\\.", ".")
      .replace("\n", " ")
    for (i <- 0 until text.length) {
      val candidate = text.charAt(i)
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
        lines.append(text.substring(lastNewLinePosition, candidatePosition + 1).trim)
        if (firstLine) {
          lines.append(spaces(lines.size, tags.length))
          lines.append(tags)
        }
        lines.append('\n')
        firstLine = false
        lastNewLinePosition = candidatePosition
      }
      if (i == text.length - 1) {
        if (firstLine) {
          lines.append(levelString())
        }
        lines.append(text.substring(lastNewLinePosition, text.length).trim)
        if (firstLine) {
          lines.append(spaces(lines.size, tags.length))
          lines.append(tags)
        }
        firstLine = false
      }
    }
    lines.append("\n").toString()
  }

  private def formatTags(entry: Entry): String = {
    entry.tags match {
      case None => ""
      case Some(tags) => tags.size match {
        case 0 => ""
        case _ => tags.mkString(" :", ":", ":")
      }
    }
  }

  private def levelString(): String = {
    val result = new StringBuilder()
    for (_ <- 0 until level) result.append("*")
    result.append(" ")
    result.mkString
  }

  private def calculateMaxWidth(firstLine: Boolean, tags: String, level: Int): Int = {
    if (firstLine) {
      maxWidth - level - 1 - tags.length
    } else {
      maxWidth
    }
  }

  private def spaces(lineLength: Int, tagsLength: Int): String = {
    if (tagsLength == 0) return ""

    val spaces = new StringBuilder
    for (_ <- 0 until (maxWidth - lineLength - tagsLength)) spaces.append(" ")
    spaces.mkString
  }
}

