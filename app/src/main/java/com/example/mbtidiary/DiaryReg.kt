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

        dbManager = DBManager(this, "mbtiDiaryDB", null, 1)

        var mbti = intent.getStringExtra("mbti") // 검사한 MBTI 결과 전달 받기
        edtMBTI.text = mbti // MBTI 결과 표시(사용자가 직접 입력 X)

        var calendar = Calendar.getInstance()
        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var day = calendar.get(Calendar.DAY_OF_MONTH)

        btnCalendar.setOnClickListener { // 달력 모양의 버튼 클릭 시 날짜 선택할 수 있게끔 DatePickerDialog 표시
            val datePickerDialog = DatePickerDialog(this, { _, year, month, day ->
                edtDate.text = year.toString() + "." + (month + 1).toString() + "." + day.toString()
            }, year, month, day)
            datePickerDialog.show()
        }

        btnRegister.setOnClickListener { // 등록 버튼
            // 각 정보 추출
            var str_date = edtDate.text
            var str_weather: String = ""
            var str_diary = edtDiary.text.toString()

            if (str_date == "클릭하여 날짜 선택") str_date = "날짜 선택 안 함" // 날짜 선택 안 한 경우

            when (rWeather.checkedRadioButtonId) { // 날씨 정보 추출
                R.id.sunny -> str_weather = sunny.text.toString()
                R.id.cloudy -> str_weather = cloudy.text.toString()
                R.id.rainy -> str_weather = rainy.text.toString()
                R.id.snowy -> str_weather = snowy.text.toString()
                else -> str_weather = "날씨 선택 안 함" // 아무 라디오 버튼도 선택하지 않은 경우
            }

            // DB에 기록(저장)
            sqlitedb = dbManager.writableDatabase
            sqlitedb.execSQL("INSERT INTO mbtiDiary VALUES ('" + str_date + "', '" + str_weather + "', '" + mbti + "', '" + str_diary + "')")
            sqlitedb.close()

            // 일기 정보 화면으로 전환 - 작성한 다이어리 표시
            val intent = Intent(this, DiaryInfo::class.java)
            intent.putExtra("date", str_date)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_diary_reg, menu)
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
            R.id.action_list -> { // 목록 화면으로 전환
                val intent = Intent(this, DiaryList::class.java)
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