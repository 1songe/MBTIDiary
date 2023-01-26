package com.example.mbtidiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.RadioButton

class MBTITest : AppCompatActivity() {

    lateinit var mbtiI: RadioButton
    lateinit var mbtiE: RadioButton
    lateinit var mbtiS: RadioButton
    lateinit var mbtiN: RadioButton
    lateinit var mbtiF: RadioButton
    lateinit var mbtiT: RadioButton
    lateinit var mbtiP: RadioButton
    lateinit var mbtiJ: RadioButton

    lateinit var btnResult: Button

    var mbti = ""
    var a = arrayOf<String>("X", "X", "X", "X") // MBTI의 각 자리에 해당하는 유형(I/E, S/N, F/T, P/J)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mbtitest)

        mbtiI = findViewById(R.id.mbtiI)
        mbtiE = findViewById(R.id.mbtiE)
        mbtiS = findViewById(R.id.mbtiS)
        mbtiN = findViewById(R.id.mbtiN)
        mbtiF = findViewById(R.id.mbtiF)
        mbtiT = findViewById(R.id.mbtiT)
        mbtiP = findViewById(R.id.mbtiP)
        mbtiJ = findViewById(R.id.mbtiJ)

        btnResult = findViewById(R.id.btnResult)

        btnResult.setOnClickListener {
            // 첫 번째 라디오 그룹에 해당하는 라디오버튼 선택 결과에 따라 I/E 유형 결정
            if (mbtiI.isChecked) a[0] = "I"
            else if(mbtiE.isChecked) a[0] = "E"

            // 두 번째 라디오 그룹에 해당하는 라디오버튼 선택 결과에 따라 S/N 유형 결정
            if (mbtiS.isChecked) a[1] = "S"
            else if(mbtiN.isChecked) a[1] = "N"

            // 세 번째 라디오 그룹에 해당하는 라디오 버튼 선택 결과에 따라 F/T 유형 결정
            if (mbtiF.isChecked) a[2] = "F"
            else if(mbtiT.isChecked) a[2] = "T"

            // 네 번째 라디오 그룹에 해당하는 라디오 버튼 선택 결과에 따라 P/J 유형 결정
            if (mbtiP.isChecked) a[3] = "P"
            else if(mbtiJ.isChecked) a[3] = "J"

            for (c in a) { // 각 자리의 유형을 다 합친 결과를 하나의 MBTI 유형으로 표시
                mbti += c
            }

            // 결과로 나온 해당 MBTI를 전달하며 검사 결과 화면으로 전환
            var intent = Intent(this, MBTIResult::class.java)
            intent.putExtra("mbti", mbti)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_mbtitest, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.action_home -> { // 메인 화면으로 전환
                val intent = Intent(this, MainActivity::class.java)
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