package me.konstantinpetrov.convert

import java.io.File

import me.konstantinpetrov.convert.dayone.JsonParser
import me.konstantinpetrov.convert.orgmode.{OrgModeField, OrgModeWriter}

/**
 * @author ${user.name}
 */
object App {
  /* todo
  I want to have some more arguments, such as:
  - input and output file names
    -i, -o
  - entry fields and their order for all types
    -f hdlw
  - date and time formats for all date parts
    -y "yyyy"
    -m "yyyy-MM EEE"
    -D "yyyy-MM-dd eee"
    -e "  Entered on [yyyy-MM-dd eee HH:mm]"
  - tags inclusion, composition and separator
   */
  def main(args: Array[String]) {
    if (args.length < 2) {
      println(
        """
          |For use, set first parameter to be input file name, and
          |second parameter to be output file name.""".stripMargin)
    }
    val inputFileName = args(0)
    val outputFileName = args(1)
    val parser = new JsonParser
    val entries = parser.parse(new File(inputFileName))

    val writer = new OrgModeWriter(List(OrgModeField.HEADER_DATE, OrgModeField.DATE, OrgModeField.LOCATION, OrgModeField.WEATHER))
    writer.write(new File(outputFileName), entries)
  }

}
