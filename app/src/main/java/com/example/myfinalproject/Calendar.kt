package com.example.myfinalproject

import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_calendar.*

internal class Calendar : AppCompatActivity() {
    var calendarView: CalendarView? = null
    var mDate: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        calendarView = findViewById<View>(R.id.mCalendarView) as CalendarView
        mDate = findViewById<View>(R.id.mDate) as TextView
        calendarView!!.setOnDateChangeListener { view, i, i1, i2 ->
            val date = (i1 + 1).toString() + "/" + 12 + "/" + i
            mDate!!.text = date


            mBtnSaveMyDate.setOnClickListener {




            }
        }
    }
}