package com.example.mbtidiary

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DiaryInfo : AppCompatActivity() {
    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase

    lateinit var edtDate: TextView
    lateinit var edtWeather: TextView
    lateinit var edtMBTI: TextView
    lateinit var edtMusic: TextView
    lateinit var edtDiary: TextView

    lateinit var imgMBTI: ImageView

    lateinit var btnRemove: Button
    lateinit var btnList: Button

    lateinit var str_date: String
    lateinit var str_weather: String
    lateinit var mbti: String
    lateinit var str_diary: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary_info)

        edtDate = findViewById(R.id.edtDate)
        edtWeather = findViewById(R.id.edtWeather)
        edtMBTI = findViewById(R.id.edtMBTI)
        edtMusic = findViewById(R.id.edtMusic)
        edtDiary = findViewById(R.id.edtDiary)

        imgMBTI = findViewById(R.id.imgMBTI)

        btnRemove = findViewById(R.id.btnRemove)
        btnList = findViewById(R.id.btnList)

        val intent = intent
        str_date = intent.getStringExtra("date").toString()

        dbManager = DBManager(this, "mbtiDiaryDB", null, 1)
        sqlitedb = dbManager.readableDatabase

        var cursor: Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM mbtiDiary WHERE date = '" + str_date + "';", null)

        if (cursor.moveToNext()) {
            str_weather = cursor.getString(cursor.getColumnIndexOrThrow("weather")).toString() // 날씨 정보 추출
            mbti = cursor.getString(cursor.getColumnIndexOrThrow("MBTI")).toString() // MBTI 정보 추출
            str_diary = cursor.getString(cursor.getColumnIndexOrThrow("diary")).toString() // 일기 내용 추출
        }

        cursor.close()
        sqlitedb.close()
        dbManager.close()

        when(mbti) { // MBTI별 추천 노래 설정 및 이미지 표시
            "ISFP" -> { edtMusic.text = "백예린 - Bye Bye my Blue"; imgMBTI.setImageResource(R.drawable.isfp) }
            "ISFJ" -> { edtMusic.text = "블랙핑크 – Forever Young"; imgMBTI.setImageResource(R.drawable.isfj) }
            "ISTP" -> { edtMusic.text = "Dosii – Love me more"; imgMBTI.setImageResource(R.drawable.istp) }
            "ISTJ" -> { edtMusic.text = "아이유 – 누구나 비밀은 있다."; imgMBTI.setImageResource(R.drawable.istj) }
            "INFP" -> { edtMusic.text = "선우정아 – 도망가자"; imgMBTI.setImageResource(R.drawable.infp) }
            "INFJ" -> { edtMusic.text = "태연 – Blue"; imgMBTI.setImageResource(R.drawable.infj) }
            "INTP" -> { edtMusic.text = "기리보이 – The time is now"; imgMBTI.setImageResource(R.drawable.intp) }
            "INTJ" -> { edtMusic.text = "Jason Mraz – I’m yours"; imgMBTI.setImageResource(R.drawable.intj)}
            "ESFP" -> { edtMusic.text = "One direction – What makes you beautiful"; imgMBTI.setImageResource(R.drawable.esfp) }
            "ESFJ" -> { edtMusic.text = "Bruno Major – Nothing"; imgMBTI.setImageResource(R.drawable.esfj) }
            "ESTP" -> { edtMusic.text = "방탄소년단 - Dynamite"; imgMBTI.setImageResource(R.drawable.estp) }
            "ESTJ" -> { edtMusic.text = "하진 – We all lie"; imgMBTI.setImageResource(R.drawable.estj) }
            "ENFP" -> { edtMusic.text = "Carly Rae Jepsen – Call me maybe"; imgMBTI.setImageResource(R.drawable.enfp) }
            "ENFJ" -> { edtMusic.text = "Lauv – Paris in the rain"; imgMBTI.setImageResource(R.drawable.enfj) }
            "ENTP" -> { edtMusic.text = "블랙핑크 – Pretty Savage"; imgMBTI.setImageResource(R.drawable.entp) }
            "ENTJ" -> { edtMusic.text = "Stefflon Don – 16 shots"; imgMBTI.setImageResource(R.drawable.entj) }
        }

        edtDate.text = str_date
        edtWeather.text = str_weather
        edtMBTI.text = mbti
        edtDiary.text = str_diary
        if (edtDiary.text == "") edtDiary.text = "작성한 일기 없음" // 작성한 일기가 없는 경우

        btnRemove.setOnClickListener { // 삭제 버튼
            dbManager = DBManager(this, "mbtiDiaryDB", null, 1)
            sqlitedb = dbManager.readableDatabase

            sqlitedb.execSQL("DELETE FROM mbtiDiary WHERE date = '" + str_date + "';")
            sqlitedb.close()
            dbManager.close()

            val intent = Intent(this, DiaryList::class.java)
            startActivity(intent) // 목록 화면으로 전환
        }

        btnList.setOnClickListener { // 목록 버튼
            val intent = Intent(this,  DiaryList::class.java)
            startActivity(intent) // 목록 화면으로 전환
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_diary_info, menu)
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
            R.id.action_remove -> { // 해당 일기 삭제 및 목록 화면으로 전환
                dbManager = DBManager(this, "mbtiDiaryDB", null, 1)
                sqlitedb = dbManager.readableDatabase

                sqlitedb.execSQL("DELETE FROM mbtiDiary WHERE date = '" + str_date + "';")
                sqlitedb.close()
                dbManager.close()

                val intent = Intent(this, DiaryList::class.java)
                startActivity(intent)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}