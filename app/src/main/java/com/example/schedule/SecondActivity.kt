package com.example.schedule

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout)

        val dbHandler: ScheduleDataBase = ScheduleDataBase(applicationContext)
        val message = intent.getStringExtra("Directions")
        val description : TextView  = findViewById(R.id.textView2)
        val direction : TextView = findViewById(R.id.textView)
        val icon : ImageView = findViewById(R.id.imageView)
        when (message) {
            "IT-квантум" -> {
                direction.text = "IT-квантум"
                description.text = "Углубленное изучение программирования, сетевых технологий. Освоение высокоуровневых языков программирования: C++, C#, Java, Arduino, Rasberry Pi и Интернет Вещей (IoT)."
                icon.setImageResource(R.drawable.itkvant)
            }
            "Геоквантум" -> {
                direction.text = "Геоквантум"
                description.text = "Работа с дистанционным зондированием Земли, оубчение картографии и проектированию виртуальных карт местности."
                icon.setImageResource(R.drawable.geokvanticon)
            }
            "Аэроквантум" -> {
                direction.text = "Аэроквантум"
                description.text = "Для большинства людей квадрокоптер ― это игрушка, от которой фанатеют, как взрослые, так и дети. Но это не так и мы это докажем!"
                icon.setImageResource(R.drawable.airokvanticon)
            }
            "Космоквантум" -> {
                direction.text = "Космоквантум"
                description.text = "Изучение физико-математических основ космонавтики, электротехники, радиотехники, электроники и фотоники, конструирование малых искусственных спутников Земли."
                icon.setImageResource(R.drawable.kosmokvanticon)
            }
            "Робоквантум" -> {
                direction.text = "Робоквантум"
                description.text = "Изучение передовых технологий в области электроники, мехатроники и программирования, конструирование и программирование роботов."
                icon.setImageResource(R.drawable.robokvanticon)
            }
            "Нейроквантум" -> {
                direction.text = "Нейроквантум"
                description.text = "Исследование механизмов работы человеческого мозга и разработка на их основе новых искуственных систем и человеко-машинных интерфейсов."
                icon.setImageResource(R.drawable.neirokvanticon)
            }
            "3D-моделирование" -> {
                direction.text = "3D-моделирование"
                description.text = "Создание прототипов устройств, создание моделей, работа с 3D- графикой и анимация конструкторских решений."
                icon.setImageResource(R.drawable.logo230)
            }
            "Прототипирование" -> {
                direction.text = "Прототипирование"
                description.text = "Создание прототипов устройств, создание моделей, работа с 3D- графикой и анимация конструкторских решений."
                icon.setImageResource(R.drawable.logo230)
            }
            "VR/AR-квантум" -> {
                direction.text = "VR/AR-квантум"
                description.text = "Создание приложений виртуальной и дополненной реальности на игровой платформе. Работа с очками и шлемами дополненной и виртуальной реальности."
                icon.setImageResource(R.drawable.it)
            }
            "Наноквантум" -> {
                direction.text = "Наноквантум"
                description.text = "Изучение современных методов и средств лазерной технологии, работа на лазерном оборудовании."
                icon.setImageResource(R.drawable.laser)
            }
        }
        val timetable : TableLayout = findViewById(R.id.timetable)
        for (group in dbHandler.get_group_list(message!!)) {
            for (lesson in dbHandler.get_lessons_by_group(group)) {
                val testRow = layoutInflater.inflate(R.layout.tablerow_layout, timetable, false) as TableRow
                testRow.findViewById<TextView>(R.id.lesson_group).text = group
                testRow.findViewById<TextView>(R.id.lesson_weekday).text = lesson.get("weekday") as String?
                testRow.findViewById<TextView>(R.id.lesson_time).text = lesson.get("time") as String?
                timetable.addView(testRow)
            }
        }
    }
}
