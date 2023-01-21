package com.example.mbtidiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    lateinit var btnTest: Button
    lateinit var btnList: ImageButton
    lateinit var btnStat: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnTest = findViewById<Button>(R.id.btnTest)
        btnList = findViewById<ImageButton>(R.id.btnList)
        btnStat = findViewById<ImageButton>(R.id.btnStat)

        btnTest.setOnClickListener {
            var intent = Intent(this, MBTITest::class.java)
            startActivity(intent)
        }
        btnList.setOnClickListener {
            var intent = Intent(this, DiaryList::class.java)
            startActivity(intent)
        }
        btnStat.setOnClickListener {
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