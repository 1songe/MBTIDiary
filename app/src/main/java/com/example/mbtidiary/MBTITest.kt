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
    var a = arrayOf<String>("X", "X", "X", "X")

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
            if (mbtiI.isChecked) a[0] = "I"
            else if(mbtiE.isChecked) a[0] = "E"

            if (mbtiS.isChecked) a[1] = "S"
            else if(mbtiN.isChecked) a[1] = "N"

            if (mbtiF.isChecked) a[2] = "F"
            else if(mbtiT.isChecked) a[2] = "T"

            if (mbtiP.isChecked) a[3] = "P"
            else if(mbtiJ.isChecked) a[3] = "J"

            for (c in a) {
                mbti += c
            }

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
            R.id.action_home -> {
                val intent = Intent(this, MainActivity::class.java)
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