package com.example.schedule

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onMyButtonClick(view: View?) {
        Toast.makeText(this, "Зачем вы нажали?", Toast.LENGTH_SHORT).show()
    }
}

