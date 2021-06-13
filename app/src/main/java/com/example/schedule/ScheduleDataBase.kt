package com.example.schedule
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



/*class ScheduleDataBase {

}*/


class ScheduleDataBase(context: Context) : SQLiteOpenHelper(context, "Schedule", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE Directions(d_id INTEGER PRIMARY KEY AUTOINCREMENT, class_group TEXT, direction TEXT);")
        db.execSQL("CREATE TABLE Lessons(l_id INTEGER PRIMARY KEY AUTOINCREMENT, class_group TEXT, weekday TEXT, time TEXT);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS Directions")
        db!!.execSQL("DROP TABLE IF EXISTS Lessons")
        onCreate(db)
    }


    fun group_add(group_title: String, group_direction: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("class_group", group_title)
        values.put("direction", group_direction)
        val _success = db.insert("Directions", null, values)
        db.close()
        return (Integer.parseInt("$_success") != -1)
    }

    fun lesson_add(group_title: String, weekday: String, time: String) {
        val db = this.writableDatabase
        val newValues = ContentValues()
        newValues.put("class_group", group_title)
        newValues.put("direction", weekday)
        newValues.put("direction", time)
        val insert = db.insert(
            "Lessons",
            null,
            newValues
        )
    }

    fun get_group_list(): ArrayList<String> {
        val db = this.readableDatabase
        var cursor: Cursor = db.rawQuery("select class_group from Directions", null)
        var groups: ArrayList<String> = arrayListOf()
        if (cursor.moveToFirst()) {
            do {
                var group: String = cursor.getString(cursor.getColumnIndex("class_group"))
                groups.add(group)

            } while (cursor.moveToNext())
        }
        return groups
    }

    fun get_direction_by_group(group: String): String {
        val db = this.readableDatabase
        var cursor: Cursor =
            db.rawQuery("select direction from Directions where class_group='" + group + "'", null)
        if (cursor.moveToFirst())
            return cursor.getString(cursor.getColumnIndex("direction"))

        return "not found"
    }

    fun get_lessons_by_group(group: String): ArrayList<ContentValues> {
        val db = this.readableDatabase
        var cursor: Cursor =
            db.rawQuery("select * from Lessons where class_group='" + group + "'", null)
        var lessons: ArrayList<ContentValues> = arrayListOf()
        if (cursor.moveToFirst()) {

            do {
                var lesson = ContentValues()
                var id = cursor.getInt(cursor.getColumnIndex("l_id"))
                var c_group = cursor.getString(cursor.getColumnIndex("class_group"))
                var weekday = cursor.getString(cursor.getColumnIndex("weekday"))
                var time = cursor.getString(cursor.getColumnIndex("time"))
                lesson.put("id", id)
                lesson.put("class_group", c_group)
                lesson.put("weekday", weekday)
                lesson.put("time", time)
                lessons.add(lesson)
            } while (cursor.moveToNext())
        }
        return lessons
    }
}



