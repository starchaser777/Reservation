package kr.ac.kopo.reservation2004

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.Chronometer
import android.widget.DatePicker
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import android.widget.TextView
import android.widget.TimePicker

class MainActivity : AppCompatActivity() {
    lateinit var chrono : Chronometer
//    lateinit var btnStart : Button
//    lateinit var btnDone : Button
    lateinit var rg : RadioGroup
    lateinit var datePick : DatePicker
    lateinit var timePick : TimePicker
    lateinit var textResult : TextView
    var selectedYear : Int = 0
    var selectedMonth : Int = 0
    var selectedDay : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chrono = findViewById<Chronometer>(R.id.chrono)
//        btnStart = findViewById<Button>(R.id.btnStart)
//        btnDone = findViewById<Button>(R.id.btnDone)
        rg = findViewById<RadioGroup>(R.id.rg)
        datePick = findViewById<DatePicker>(R.id.datePick)
        timePick = findViewById<TimePicker>(R.id.timePick)
        textResult = findViewById<TextView>(R.id.textResult)

        rg.visibility = View.INVISIBLE
        datePick.visibility = View.INVISIBLE
        timePick.visibility = View.INVISIBLE


        chrono.setOnClickListener {
            chrono.base = SystemClock.elapsedRealtime()
            chrono.start()
            rg.visibility = View.VISIBLE
            chrono.setTextColor(Color.MAGENTA)
        }

        textResult.setOnLongClickListener {
            chrono.stop()
            chrono.setTextColor(Color.CYAN)
            selectedYear = datePick.year
            selectedMonth = datePick.month + 1
            selectedDay = datePick.dayOfMonth

            textResult.setText("" + selectedYear + "년 " + selectedMonth + "월 " + selectedDay + "일 ")
            textResult.append("" + timePick.currentHour + "시 ")
            textResult.append("" + timePick.currentMinute + "분")
            textResult.append(" 예약 완료됨.")

            rg.visibility = View.INVISIBLE
            datePick.visibility = View.INVISIBLE
            timePick.visibility = View.INVISIBLE

            return@setOnLongClickListener true
        }

        rg.setOnCheckedChangeListener(rgListener)

//        btnStart.setOnClickListener {
//            chrono.base = SystemClock.elapsedRealtime()
//            chrono.start()
//            chrono.setTextColor(Color.MAGENTA)
//        }
//
//        btnDone.setOnClickListener {
//            chrono.stop()
//            chrono.setTextColor(Color.CYAN)
//            selectedYear = datePick.year
//            selectedMonth = datePick.month + 1
//            selectedDay = datePick.dayOfMonth
//
//            textResult.setText("" + selectedYear + "년 " + selectedMonth + "월 " + selectedDay + "일 ")
//            textResult.append("" + timePick.currentHour + "시 ")
//            textResult.append("" + timePick.currentMinute + "분")
//        }
//
//        calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
//            selectedYear = year
//            selectedMonth = month + 1
//            selectedDay = dayOfMonth
//        }
    }

    var rgListener = OnCheckedChangeListener { group, checkedId ->
        datePick.visibility = View.INVISIBLE
        timePick.visibility = View.INVISIBLE
        when(rg.checkedRadioButtonId) {
            R.id.rbDate -> datePick.visibility = View.VISIBLE
            R.id.rbTime -> timePick.visibility = View.VISIBLE
        }
    }
}
