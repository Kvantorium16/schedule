package com.example.schedule

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.util.*
import com.example.schedule.ScheduleDataBase.*

class HtmlParser{

    fun getHtmlFromWeb(db : ScheduleDataBase) {
        val groups: ArrayList<String> = arrayListOf()
        val directions: ArrayList<String> = arrayListOf()
        Thread{
            try {
                val doc: Document = Jsoup.connect("https://jjpega.pythonanywhere.com/get").get()
                val group: Elements = doc.select("p[id=\"group\"]")
                for (index in group.indices) {
                    groups.add(group[index].text())
                }
                groups.distinct()

                val direction: Elements = doc.select("td[id=\"direction\"]")
                for (index in direction.indices) {
                    directions.add(direction[index].text())
                }
                directions.distinct()

                for (index in groups.indices) {
                    db.group_add(groups[index], directions[index])
                }

                val weekdays: Array<String> = (arrayOf("Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"))

                for (direction in directions) {
                    for (days in 0..6) {
                        val str = "table[id='" + weekdays[days] + "_" + direction + "']"
                        val day: Elements = doc.select(str)
                        db.lesson_add(day.select("p[id=\"group\"]").text(), weekdays[days], day.select("p[id=\"time\"]").text())
                    }
                }

            } catch (e: Exception) {

            }
        }.start()
    }
}

