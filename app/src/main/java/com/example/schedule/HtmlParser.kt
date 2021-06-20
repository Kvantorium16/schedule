package com.example.schedule

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.util.*
import com.example.schedule.ScheduleDataBase.*
import org.jsoup.nodes.Element
import kotlin.collections.ArrayList

class HtmlParser{

    fun getHtmlFromWeb(db : ScheduleDataBase) {
        Thread{
            db.clear_directions_table()
            db.clear_lessons_table()
            try {
                val doc: Document = Jsoup.connect("https://jjpega.pythonanywhere.com/get").get()

                var directions: ArrayList<String> = arrayListOf()
                var i = 0
                while (true){
                    try {
                        val direction: String = doc.select("td[id=\"direction\"]")[i].text()
                        directions.add(direction)
                        i += 1
                    }
                    catch (e: Exception) {
                        break
                    }
                }

                var num_directions: Int = directions.size
                i = 0
                while (i <= num_directions){
                    var group: String = ""
                    var groups: ArrayList<String>
                    try {
                        var tr: Element? = doc.select("tr[id=\"er\"]")[i]
                        try {
                            group = tr!!.select("p[id=\"group\"]").text()
                            groups = group.split(" ") as ArrayList<String>
                            groups = groups.distinct() as ArrayList<String>
                            for (group in groups){
                                db.group_add(group, directions[i])
                            }
                            i += 1
                        }
                        catch (e: Exception){
                            i += 1
                            continue
                        }

                    }
                    catch (e: Exception){
                        break
                    }
                }


                val weekdays: Array<String> = (arrayOf("Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"))

                for (days in weekdays) {
                    for (direction in directions) {
                        i = 0
                        val str = "table[id='" + days + "_" + direction + "']"
                        val day: Elements = doc.select(str)
                        try {
                            while (true){
                                db.lesson_add(day.select("p[id=\"group\"]")[i].text(), days, day.select("p[id=\"time\"]")[i].text())
                                i += 1
                            }
                        }
                        catch (e: Exception){
                            break
                        }
                    }
                }

            } catch (e: Exception) {

            }
        }.start()
    }
}

