package com.example.mbtidiary

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var edtMBTI: TextView

    lateinit var btnTest: Button
    lateinit var btnList: ImageButton
    lateinit var btnStat: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtMBTI = findViewById<TextView>(R.id.edtMBTI)

        btnTest = findViewById<Button>(R.id.btnTest)
        btnList = findViewById<ImageButton>(R.id.btnList)
        btnStat = findViewById<ImageButton>(R.id.btnStat)

        edtMBTI.text = " ? ? ? ? "
        edtMBTI.paintFlags = Paint.UNDERLINE_TEXT_FLAG // 밑줄 표시

        btnTest.setOnClickListener { // 검사 버튼 - MBTI 테스트 화면으로 전환
            var intent = Intent(this, MBTITest::class.java)
            startActivity(intent)
        }
        btnList.setOnClickListener { // 목록 버튼 - 목록 화면으로 전환
            var intent = Intent(this, DiaryList::class.java)
            startActivity(intent)
        }
        btnStat.setOnClickListener { // 통계 버튼 - 통계 화면으로 전환
            var intent = Intent(this, MBTIStatics::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
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