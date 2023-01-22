package com.example.mbtidiary

import android.app.DatePickerDialog
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import java.util.Calendar

class DiaryReg : AppCompatActivity() {
    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase

    lateinit var edtDate: TextView
    lateinit var edtMBTI: TextView

    lateinit var edtDiary: EditText

    lateinit var rWeather: RadioGroup
    lateinit var sunny: RadioButton
    lateinit var cloudy: RadioButton
    lateinit var rainy: RadioButton
    lateinit var snowy: RadioButton

    lateinit var btnCalendar: ImageButton
    lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary_reg)

        edtDate = findViewById(R.id.edtDate)
        edtMBTI = findViewById(R.id.edtMBTI)

        edtDiary = findViewById(R.id.edtDiary)

        rWeather = findViewById(R.id.rWeather)
        sunny = findViewById(R.id.sunny)
        cloudy = findViewById(R.id.cloudy)
        rainy = findViewById(R.id.rainy)
        snowy = findViewById(R.id.snowy)

        btnCalendar = findViewById(R.id.btnCalendar)
        btnRegister = findViewById(R.id.btnRegister)

        var mbti = intent.getStringExtra("mbti")
        edtMBTI.text = mbti

//        val myCalendar = Calendar.getInstance()
//
//        val datePickerDialog = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
//            myCalendar.set(Calendar.YEAR, year)
//            myCalendar.set(Calendar.MONTH, month)
//            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//        }

        var calendar = Calendar.getInstance()
        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var day = calendar.get(Calendar.DAY_OF_MONTH)

        btnCalendar.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this, { _, year, month, day ->
                edtDate.text = year.toString() + "." + (month + 1).toString() + "." + day.toString()
            }, year, month, day)
            datePickerDialog.show()
        }

        btnRegister.setOnClickListener {
            var str_date = edtDate.text
            var str_weather: String = ""
            var str_diary = edtDiary.text.toString()

            when (rWeather.checkedRadioButtonId) {
                R.id.sunny -> str_weather = sunny.text.toString()
                R.id.rainy -> str_weather = rainy.text.toString()
                R.id.snowy -> str_weather = snowy.text.toString()
                else -> str_weather = "날씨 선택 안 함"
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_diary_reg, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.action_home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_test -> {
                val intent = Intent(this, MBTITest::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_list -> {
                val intent = Intent(this, DiaryList::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_stat -> {
                val intent = Intent(this, MBTIStatics::class.java)
                startActivity(intent)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}