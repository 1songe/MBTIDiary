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

class MBTIStatics : AppCompatActivity() {
    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase

    lateinit var tISFP: TextView
    lateinit var tISFJ: TextView
    lateinit var tISTP: TextView
    lateinit var tISTJ: TextView
    lateinit var tINFP: TextView
    lateinit var tINFJ: TextView
    lateinit var tINTP: TextView
    lateinit var tINTJ: TextView
    lateinit var tESFP: TextView
    lateinit var tESFJ: TextView
    lateinit var tESTP: TextView
    lateinit var tESTJ: TextView
    lateinit var tENFP: TextView
    lateinit var tENFJ: TextView
    lateinit var tENTP: TextView
    lateinit var tENTJ: TextView
    lateinit var tMBTI: TextView

    lateinit var imgMBTI: ImageView

    lateinit var btnMain: Button

    lateinit var mbti: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mbtistatics)

        tISFP = findViewById(R.id.tISFP)
        tISFJ = findViewById(R.id.tISFJ)
        tISTP = findViewById(R.id.tISTP)
        tISTJ = findViewById(R.id.tISTJ)
        tINFP = findViewById(R.id.tINFP)
        tINFJ = findViewById(R.id.tINFJ)
        tINTP = findViewById(R.id.tINTP)
        tINTJ = findViewById(R.id.tINTJ)
        tESFP = findViewById(R.id.tESFP)
        tESFJ = findViewById(R.id.tESFJ)
        tESTP = findViewById(R.id.tESTP)
        tESTJ = findViewById(R.id.tESTJ)
        tENFP = findViewById(R.id.tENFP)
        tENFJ = findViewById(R.id.tENFJ)
        tENTP = findViewById(R.id.tENTP)
        tENTJ = findViewById(R.id.tENTJ)
        tMBTI = findViewById(R.id.tMBTI)

        imgMBTI = findViewById(R.id.imgMBTI)

        btnMain = findViewById(R.id.btnMain)

        dbManager = DBManager(this, "mbtiDiaryDB", null, 1)
        sqlitedb = dbManager.readableDatabase

        var cursor: Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM mbtiDiary", null)

        val mbtiTypes = arrayOf("ISFP", "ISFJ", "ISTP", "ISTJ", "INFP", "INFJ", "INTP", "INTJ",
            "ESFP", "ESFJ", "ESTP", "ESTJ", "ENFP", "ENFJ", "ENTP", "ENTJ")
        var nMBTI = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

        for (i in mbtiTypes.indices) {
            while (cursor.moveToNext()) {
                mbti = cursor.getString(cursor.getColumnIndexOrThrow("MBTI")).toString()

                if (mbti == mbtiTypes[i]) nMBTI[i]++
            }
            cursor.moveToPosition(-1) // cursor를 첫 행 이전으로 이동
        }

        cursor.close()
        sqlitedb.close()
        dbManager.close()

        tISFP.text = nMBTI[0].toString()
        tISFJ.text = nMBTI[1].toString()
        tISTP.text = nMBTI[2].toString()
        tISTJ.text = nMBTI[3].toString()
        tINFP.text = nMBTI[4].toString()
        tINFJ.text = nMBTI[5].toString()
        tINTP.text = nMBTI[6].toString()
        tINTJ.text = nMBTI[7].toString()
        tESFP.text = nMBTI[8].toString()
        tESFJ.text = nMBTI[9].toString()
        tESTP.text = nMBTI[10].toString()
        tESTJ.text = nMBTI[11].toString()
        tENFP.text = nMBTI[12].toString()
        tENFJ.text = nMBTI[13].toString()
        tENTP.text = nMBTI[14].toString()
        tENTJ.text = nMBTI[15].toString()

        // 최대 빈도 MBTI 찾기
        var max = 0
        for (i in nMBTI.indices) {
            if (nMBTI[i] > nMBTI[max]) max = i
        }

        // 동일 빈도의 MBTI 개수 구하기
        var count = -1
        for (c in nMBTI) {
            if (c == nMBTI[max]) count++
        }

        if (nMBTI[max] == 0) mbti = "XXXX" // 검사한 MBTI가 없을 경우
        else {
            if (count == 0) mbti = mbtiTypes[max] // 최대 빈도 MBTI가 하나만 존재하는 경우
            else mbti = mbtiTypes[max] + " 외 " + count.toString()
        }

        when(mbti) {
            "ISFP" -> imgMBTI.setImageResource(R.drawable.isfp)
            "ISFJ" -> imgMBTI.setImageResource(R.drawable.isfj)
            "ISTP" -> imgMBTI.setImageResource(R.drawable.istp)
            "ISTJ" -> imgMBTI.setImageResource(R.drawable.istj)
            "INFP" -> imgMBTI.setImageResource(R.drawable.infp)
            "INFJ" -> imgMBTI.setImageResource(R.drawable.infj)
            "INTP" -> imgMBTI.setImageResource(R.drawable.intp)
            "INTJ" -> imgMBTI.setImageResource(R.drawable.intj)
            "ESFP" -> imgMBTI.setImageResource(R.drawable.esfp)
            "ESFJ" -> imgMBTI.setImageResource(R.drawable.esfj)
            "ESTP" -> imgMBTI.setImageResource(R.drawable.estp)
            "ESTJ" -> imgMBTI.setImageResource(R.drawable.estj)
            "ENFP" -> imgMBTI.setImageResource(R.drawable.enfp)
            "ENFJ" -> imgMBTI.setImageResource(R.drawable.enfj)
            "ENTP" -> imgMBTI.setImageResource(R.drawable.entp)
            "ENTJ" -> imgMBTI.setImageResource(R.drawable.entj)
            else -> imgMBTI.setImageResource(R.drawable.unknown)
        }

        tMBTI.text = mbti

        btnMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_mbtistatics, menu)
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
        }

        return super.onOptionsItemSelected(item)
    }
}