package me.konstantinpetrov.convert.orgmode

import me.konstantinpetrov.convert.model.Entry

class OrgModeEntryTextFormatter(maxWidth: Int = 50, wordWrap: Boolean = true) {
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
      if (i - lastNewLinePosition >= maxWidth) {
        if (candidatePosition > lastNewLinePosition) {
          lines.append(entry.text.substring(lastNewLinePosition, candidatePosition + 1).trim)
          lines.append('\n')
          lastNewLinePosition = candidatePosition
        }
      }
      if (i == entry.text.length - 1) {
        lines.append(entry.text.substring(lastNewLinePosition, entry.text.length).trim)
      }
    }
    lines.toString()
  }
}