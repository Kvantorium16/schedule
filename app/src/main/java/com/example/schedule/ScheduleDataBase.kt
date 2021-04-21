package com.example.schedule

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class ScheduleDataBase(context: Context) : SQLiteOpenHelper(context, "Schedule", null, "DATABASE_VERSION") {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE Directions(d_id INTEGER PRIMARY KEY AUTOINCREMENT, class_group TEXT, direction TEXT);");
        db.execSQL("CREATE TABLE Lessons(l_id INTEGER PRIMARY KEY AUTOINCREMENT, class_group TEXT, weekday TEXT, time TEXT);");
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun group_add(db: SQLiteDatabase, group_title : String, group_direction : String) {
        val newValues = ContentValues();
        newValues.put("class_group", group_title);
        newValues.put("direction", group_direction);
        val insert = db.insert(
            "Directions",
            null,
            newValues
        );
    }

    fun lesson_add(db: SQLiteDatabase, group_title: String, weekday: String, time: String){
        val newValues = ContentValues();
        newValues.put("class_group", group_title);
        newValues.put("direction", weekday);
        newValues.put("direction", time);
        val insert = db.insert(
            "Lessons",
            null,
            newValues
        );
    }

    fun get_group_list(db: SQLiteDatabase){
        val cursor: Cursor = db.query("Directions", arrayOf("NAME", "DESCRIPTION"),
            null, null, null, null);

        val newValues = ContentValues();
        newValues.put("class_group", );
        newValues.put("direction", );
    }
}