package com.example.schedule

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.viewpager.widget.ViewPager

class SecondActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout)
        val message = intent.getStringExtra("Directions")
        val description : TextView  = findViewById(R.id.textView2)
        val direction : TextView = findViewById(R.id.textView)
        when (message) {
            "IT-квантум" -> {
                direction.text = "IT-квантум"
                description.text = "Углубленное изучение программирования, сетевых технологий. Освоение высокоуровневых языков программирования: C++, C#, Java, Arduino, Rasberry Pi и Интернет Вещей (IoT)."
            }
            "Геоквантум" -> {
                direction.text = "Геоквантум"
                description.text = "Работа с дистанционным зондированием Земли, оубчение картографии и проектированию виртуальных карт местности."
            }
            "Аэроквантум" -> {
                direction.text = "Аэроквантум"
                description.text = "Для большинства людей квадрокоптер ― это игрушка, от которой фанатеют, как взрослые, так и дети. Но это не так и мы это докажем!"
            }
            "Космоквантум" -> {
                direction.text = "Космоквантум"
                description.text = "Изучение физико-математических основ космонавтики, электротехники, радиотехники, электроники и фотоники, конструирование малых искусственных спутников Земли."
            }
            "Робоквантум" -> {
                direction.text = "Робоквантум"
                description.text = "Изучение передовых технологий в области электроники, мехатроники и программирования, конструирование и программирование роботов."
            }
            "Нейроквантум" -> {
                direction.text = "Нейроквантум"
                description.text = "Исследование механизмов работы человеческого мозга и разработка на их основе новых искуственных систем и человеко-машинных интерфейсов."
            }
        }

    }
}
