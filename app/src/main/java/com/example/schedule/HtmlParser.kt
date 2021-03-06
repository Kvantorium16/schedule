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
            try {
                val doc: Document = Jsoup.connect("https://jjpega.pythonanywhere.com/get").get()
                db.clear_directions_table()
                db.clear_lessons_table()

                val direction: Elements? = doc.select("td[id=\"direction\"]")
                var directions: ArrayList<String> = arrayListOf()
                if (direction != null) {
                    for (dir in direction){
                        directions.add(dir.text())
                    }
                }

                var num_directions: Int = directions.size
                var i = 0
                var groups: List<String> = arrayListOf()
                var temp: Elements?
                var tr: Elements? = doc.select("tr[id=\"er\"]")
                while (i < num_directions){
                    groups = arrayListOf()
                    temp = tr!![i].select("p[id=\"group\"]")
                    if (temp != null) {
                        for (group in temp){
                            groups.add(group.text())
                        }
                        groups = groups.distinct()
                        for (group in groups){
                            db.group_add(group, directions[i])
                        }
                    }
                    i += 1
                }

                val weekdays: Array<String> = (arrayOf("Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"))
                for (days in weekdays) {
                    for (direction in directions) {
                        i = 0
                        val day: Elements = doc.select("table[id='" + days + "_" + direction + "']")
                        val g: Int = day.select("p[id=\"group\"]").size
                        while (i < g) {
                            db.lesson_add(day.select("p[id=\"group\"]")[i].text(), days, day.select("p[id=\"time\"]")[i].text())
                            i += 1
                        }
                    }
                }

            } catch (e: Exception) {
                println(e)
            }
        }.start()
    }
}
