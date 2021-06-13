package com.example.schedule

import org.jsoup.Jsoup
import org.jsoup.select.Elements

class HtmlParser
{
    fun getHtmlFromWeb() : ArrayList<String>
    {
        val groups: ArrayList<String> = arrayListOf()
        try
        {
            val doc = Jsoup.connect("https://jjpega.pythonanywhere.com/get")
                .data("query", "Java")
                .userAgent("Opera")
                .timeout(1000).ignoreHttpErrors(true)
                .get()
            while (true)
                try
                {
                    val title: String = doc.title()
                    val link: Elements = doc.select("td[id=direction]")
                    groups.add(title)
                    groups.add(link.toString())
                } catch (e: Exception)
                {
                    break
                }

        } catch (e: Exception)
        {
            groups.add("Error")
        }
        return groups
    }
}