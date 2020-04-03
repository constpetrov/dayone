package me.konstantinpetrov.convert.orgmode
import me.konstantinpetrov.convert.model.Entry

class OrgModeWeatherFormatter(weatherPrefix: String = "  Weather: ") extends TextFormatter {
  override def format(entry: Entry): String = {
    entry.weather match {
      case Some(w) => s"$weatherPrefix${w.temperatureCelsius}˚C, ${w.conditionsDescription}\n"
      case None => ""
    }
  }
}
