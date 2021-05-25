package com.example.schedule


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.viewpager.widget.ViewPager
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    var objViewPager: ViewPager? = null
    lateinit var cardAdapter : Adapter
    var models: List<Model>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // For open second activity
        val button = findViewById<Button>(R.id.main_button)
        button.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        // For spinner

         val spinner = findViewById<Spinner>(R.id.directions_spinner);

         val adapter : ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this, R.array.directions, android.R.layout.simple_spinner_dropdown_item)
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         spinner.adapter = adapter

        // For cards

        models = ArrayList()
        (models as ArrayList<Model>).add(
                Model(
                        R.drawable.robot,
                        "Робоквантум",
                        "Изучение передовых технологий в области электроники, мехатроники и программирования, конструирование и программирование роботов."
                )
        )
        (models as ArrayList<Model>).add(
                Model(
                        R.drawable.neirokvant,
                        "Нейроквантум",
                        "Исследование механизмов работы человеческого мозга и разработка на их основе новых искуственных систем и человеко-машинных интерфейсов."
                )
        )
        (models as ArrayList<Model>).add(
                Model(
                        R.drawable.cosmokvant,
                        "Космоквантум",
                        "Изучение физико-математических основ космонавтики, электротехники, радиотехники, электроники и фотоники, конструирование малых искусственных спутников Земли."
                )
        )
        (models as ArrayList<Model>).add(
                Model(
                        R.drawable.airkvant,
                        "Аэроквантум",
                        "Для большинства людей квадрокоптер ― это игрушка, от которой фанатеют, как взрослые, так и дети. Но это не так и мы это докажем! "
                )
        )
        (models as ArrayList<Model>).add(
                Model(
                        R.drawable.programkvant,
                        "IT-квантум",
                        "Углубленное изучение программирования, сетевых технологий. Освоение высокоуровневых языков программирования: C++, C#, Java, Arduino, Rasberry Pi и Интернет Вещей (IoT)."
                )
        )
        (models as ArrayList<Model>).add(
                Model(
                        R.drawable.geokvant,
                        "Геоквантум",
                        "Работа с дистанционным зондированием Земли, обучение картографии и проектированию виртуальных карт местности."
                )
        )

        cardAdapter = Adapter(models, this)

        objViewPager = findViewById(R.id.viewPager)
        objViewPager?.adapter = cardAdapter
        objViewPager?.setPadding(130, 0, 130, 0)

    }





}