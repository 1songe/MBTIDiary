package com.example.mbtidiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MBTIResult : AppCompatActivity() {
    lateinit var edtMBTI: TextView
    lateinit var edtMBTIExp: TextView
    lateinit var imgMBTI: ImageView

    lateinit var btnWriteDiary: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mbtiresult)

        var mbti = intent.getStringExtra("mbti")

        edtMBTI = findViewById(R.id.edtMBTI)
        edtMBTIExp = findViewById(R.id.edtMBTIExp)
        imgMBTI = findViewById(R.id.imgMBTI)

        btnWriteDiary = findViewById(R.id.btnWriteDiary)

        edtMBTI.text = mbti + "!"

        when(mbti) { // 해당 결과의 MBTI에 대한 간단한 설명 표시 및 걸맞는 이미지 표시
            "ISFP" -> { edtMBTIExp.text = "\"호기심 많은 예술가\"\n항시 새로운 것을 찾아 시도하거나 도전형 준비가 되어 있는 융통성 있는 성격의 매력 넘치는 예술가형"; imgMBTI.setImageResource(R.drawable.isfp) }
            "ISFJ" -> { edtMBTIExp.text = "\"용감한 수호자\"\n소중한 이들을 수호하는 데 심혈을 기울이는 헌신적이며\n성실한 방어자형"; imgMBTI.setImageResource(R.drawable.isfj) }
            "ISTP" -> { edtMBTIExp.text = "\"만능 재주꾼\"\n대담하고 현실적인 성향으로 다양한 도구 사용에 능숙한\n탐험형"; imgMBTI.setImageResource(R.drawable.istp) }
            "ISTJ" -> { edtMBTIExp.text = "\"청렴결백한 논리주의자\"\n사실에 근거하여 사고하며 이들의 행동이나 결정 사항에\n한 치의 의심을 사지 않는 현실주의자형"; imgMBTI.setImageResource(R.drawable.istj) }
            "INFP" -> { edtMBTIExp.text = "\"열정적인 중재자\"\n상냥한 성격의 이타주의자로 건강하고 밝은 사회 건설에\n앞장서는 낭만형"; imgMBTI.setImageResource(R.drawable.infp) }
            "INFJ" -> { edtMBTIExp.text = "\"선의의 옹호자\"\n조용하고 신비로우며 샘솟는 영감으로 지칠 줄 모르는\n이상주의자"; imgMBTI.setImageResource(R.drawable.infj) }
            "INTP" -> { edtMBTIExp.text = "\"논리적인 사색가\"\n끊임없이 새로운 지식에 목말라 하는 혁신가형"; imgMBTI.setImageResource(R.drawable.intp) }
            "INTJ" -> { edtMBTIExp.text = "\"용의주도한 전략가\"\n상상력이 풍부하여 철두철미한 계획을 세우는 전략가형"; imgMBTI.setImageResource(R.drawable.intj)}
            "ESFP" -> { edtMBTIExp.text = "\"자유로운 영혼의 연예인\"\n주위에 있으면 인생이 지루할 새가 없을 정도로 즉흥적이며\n열정과 에너지가 넘치는 연예인형"; imgMBTI.setImageResource(R.drawable.esfp) }
            "ESFJ" -> { edtMBTIExp.text = "\"사교적인 외교관\"\n타인을 향한 세심한 관심과 사교적인 성향으로 사람들 내에서 인기가 많으며, 타인을 돕는데 열성적인 세심형"; imgMBTI.setImageResource(R.drawable.esfj) }
            "ESTP" -> { edtMBTIExp.text = "\"모험을 즐기는 사업가\"\n벼랑 끝의 아슬아슬한 삶을 진정으로 즐길 줄 아는 이들로\n명색한 두뇌와 에너지, 그리고 뛰어난 직관력을 가지고 있는 유형"; imgMBTI.setImageResource(R.drawable.estp) }
            "ESTJ" -> { edtMBTIExp.text = "\"엄격한 관리자\"\n사물이나 사람을 관리하는 데 타의 추종을 불허하는 뛰어난\n실력을 갖춘 관리자형"; imgMBTI.setImageResource(R.drawable.estj) }
            "ENFP" -> { edtMBTIExp.text = "\"재기발랄한 활동가\"\n창의적이며 항상 웃을 거리를 찾아다니는 활발한 성격으로\n사람들과 자유롭게 어울리기를 좋아하는 넘치는 열정의\n소유자"; imgMBTI.setImageResource(R.drawable.enfp) }
            "ENFJ" -> { edtMBTIExp.text = "\"정의로운 사회운동가\"\n넘치는 카리스마와 영향력으로 청중을 압도하는 리더형"; imgMBTI.setImageResource(R.drawable.enfj) }
            "ENTP" -> { edtMBTIExp.text = "\"뜨거운 논쟁을 즐기는 변론가\"\n지적인 도전을 두려워하지 않는 똑똑한 호기심형"; imgMBTI.setImageResource(R.drawable.entp) }
            "ENTJ" -> { edtMBTIExp.text = "\"대담한 통솔자\"\n대담하면서도 상상력이 풍부한 강한 의지의 소유자로 다양한 방법을 모색하거나 여의치 않을 경우 새로운 방안을 창출하는 리더형"; imgMBTI.setImageResource(R.drawable.entj) }
        }

        btnWriteDiary.setOnClickListener { // 일기 작성 화면으로 전환
            var intent = Intent(this, DiaryReg::class.java)
            intent.putExtra("mbti", mbti) // 검사한 결과의 MBTI 정보 전달
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_mbtiresult, menu)
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