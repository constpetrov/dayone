package me.konstantinpetrov.convert.orgmode

import me.konstantinpetrov.convert.model.Entry

class OrgModeEntryTextFormatter (maxWidth: Int, wordWrap: Boolean) {
  def format(entry: Entry) : String = {
    entry.text
  }
}
