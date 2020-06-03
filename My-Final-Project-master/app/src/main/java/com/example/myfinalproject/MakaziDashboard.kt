package com.example.myfinalproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

internal class MakaziDashboard : AppCompatActivity(),
    View.OnClickListener {
    private var host_card: CardView? = null
    private var offices_card: CardView? = null
    private var calendar_card: CardView? = null
    private var residential_card: CardView? = null
    private var contact_card: CardView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_makazi_dashboard)
        host_card = findViewById<View>(R.id.host_card) as CardView
        residential_card = findViewById<View>(R.id.residential_card) as CardView
        offices_card = findViewById<View>(R.id.offices_card) as CardView
        calendar_card = findViewById<View>(R.id.calendar_card) as CardView
        contact_card = findViewById<View>(R.id.contact_card) as CardView

        //add onclicklistener to the cards
        host_card!!.setOnClickListener(this)
        residential_card!!.setOnClickListener(this)
        offices_card!!.setOnClickListener(this)
        calendar_card!!.setOnClickListener(this)
        contact_card!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val i: Intent
        when (v.id) {
            R.id.calendar_card -> {
                i = Intent(this, Calendar::class.java)
                startActivity(i)
            }
            R.id.host_card -> {
                i = Intent(this, Host::class.java)
                startActivity(i)
            }
            R.id.offices_card -> {
                i = Intent(this, Offices::class.java)
                startActivity(i)
            }
            R.id.residential_card -> {
                i = Intent(this, ResidentialActivity::class.java)
                startActivity(i)
            }

            R.id.contact_card -> {
                i = Intent(this, Contact::class.java)
                startActivity(i)
            }

            else -> {
            }
        }
    }
}