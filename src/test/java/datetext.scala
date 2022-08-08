import java.util.Date
import java.text.SimpleDateFormat

object datetext {
  def main(args: Array[String]): Unit = {
    val time = "2022022824234"
    println(time.substring(0,8))
    println(isDate(time.substring(0,8)))
  }
  def isDate(dateStr:String): Boolean ={
    val format = new SimpleDateFormat("yyyyMMdd")
    val newtime :Date = format.parse(dateStr)
    format.format(newtime).equals(dateStr)
  }
}
