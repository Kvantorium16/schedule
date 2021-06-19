package com.example.schedule

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.util.*
import com.example.schedule.ScheduleDataBase.*

class HtmlParser{

    fun getHtmlFromWeb() {
        val groups: ArrayList<String> = arrayListOf()
        val directions: ArrayList<String> = arrayListOf()
        val ma = MainActivity()
        val db = ScheduleDataBase(ma)
        Thread{
            try {
                val doc: Document = Jsoup.connect("https://jjpega.pythonanywhere.com/get").get()
                val group: Elements = doc.select("p[id=\"group\"]")
                for (index in group.indices) {
                    groups.add(group[index].toString())
                }
                groups.distinct()

                val direction: Elements = doc.select("td[id=\"direction\"]")
                for (index in direction.indices) {
                    directions.add(direction[index].toString())
                }
                directions.distinct()

                for (index in groups.indices) {
                    db.group_add(groups[index], directions[index])
                }

                val weekdays: Array<String> = (arrayOf("Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"))

                val days = 0
                for (index in directions) {
                    while (days <= 7) {
                        val day: Elements = doc.select("table[id=\"$weekdays[$days]_$directions[$index]\"]")
                        db.lesson_add(day.select("p[id=\"group\"]").toString(), weekdays[days], day.select("p[id=\"time\"]").toString())
                    }
                }

            } catch (e: Exception) {

            }
        }.start()
    }
}

