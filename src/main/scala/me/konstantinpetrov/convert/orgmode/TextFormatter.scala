package me.konstantinpetrov.convert.orgmode

import java.time.ZonedDateTime

import me.konstantinpetrov.convert.model.Entry

trait TextFormatter {
  def format(entry: Entry): String = {???}
  def format(entry: Entry, previousDate: ZonedDateTime): String = {???}

}
