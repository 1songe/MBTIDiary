package com.example.mbtidiary

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.setPadding

class DiaryList : AppCompatActivity() {
    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase
    lateinit var layout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary_list)

        dbManager = DBManager(this, "mbtiDiaryDB", null, 1)
        sqlitedb = dbManager.readableDatabase

        layout = findViewById(R.id.layout)

        var cursor: Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM mbtiDiary", null)

        var num: Int = 0
        while (cursor.moveToNext()) {

            // 각각의 정보 추출
            var str_date = cursor.getString(cursor.getColumnIndexOrThrow("date")).toString()
            var str_weather = cursor.getString(cursor.getColumnIndexOrThrow("weather")).toString()
            var mbti = cursor.getString(cursor.getColumnIndexOrThrow("MBTI")).toString()
            var str_diary = cursor.getString(cursor.getColumnIndexOrThrow("diary")).toString()

            var layout_item: LinearLayout = LinearLayout(this)
            layout_item.orientation = LinearLayout.VERTICAL
            layout_item.id = num

            var edtDate: TextView = TextView(this)
            edtDate.text = str_date
            edtDate.textSize = 30f
            edtDate.setBackgroundColor(Color.LTGRAY)
            edtDate.setPadding(30)
            layout_item.addView(edtDate)

            var edtWeather: TextView = TextView(this)
            edtWeather.text = str_weather
            edtWeather.setPadding(30, 30, 30, 0)
            layout_item.addView(edtWeather)

            var edtMBTI: TextView = TextView(this)
            edtMBTI.text = mbti
            edtMBTI.setPadding(30, 0, 30, 0)
            layout_item.addView(edtMBTI)

            var edtDiary: TextView = TextView(this)
            edtDiary.text = str_diary
            edtDiary.setPadding(30, 0, 30, 0)
            layout_item.addView(edtDiary)

            layout_item.setOnClickListener { // 선택 시 해당 일기로 이동
                val intent = Intent(this, DiaryInfo::class.java)
                intent.putExtra("date", str_date)
                startActivity(intent)
            }

            layout.addView(layout_item)
            num++
        }

        cursor.close()
        sqlitedb.close()
        dbManager.close()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_diary_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.action_home -> { // 메인 화면으로 전환
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_test -> { // MBTI 테스트 화면으로 전환
                val intent = Intent(this, MBTITest::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_stat -> { // 통계 화면으로 전환
                val intent = Intent(this, MBTIStatics::class.java)
                startActivity(intent)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}