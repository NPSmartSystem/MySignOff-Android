 package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

 class MainActivity : AppCompatActivity() {
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         window.requestFeature(Window.FEATURE_ACTION_BAR)
         supportActionBar?.hide()
         setContentView(R.layout.activity_main)
         val camBut: ImageButton = findViewById(R.id.CameraBut)
         val LockButton: Button = findViewById(R.id.lockButFrag)
         val IPVal2: String = intent.getStringExtra("IPValT").toString()
         val ipcomm: String = IPVal2
         val ipcomm2: String = "http://" + ipcomm + "/toggle"
         var i = true
         LockButton.setOnClickListener {
             if (i) {
                 Toast.makeText(this@MainActivity, "My SignOff Unlocked!", Toast.LENGTH_LONG).show()
                 GlobalScope.launch {
                     val url = URL(ipcomm2)
                     val con: HttpURLConnection = url.openConnection() as HttpURLConnection
                        try {
                          val `in`: InputStream = BufferedInputStream(con.inputStream)
                          `in`.read()
                        } finally {
                          con.disconnect()
                        }
                 }
                     i = false
             }
                 else if (!i) {
                     Toast.makeText(this@MainActivity, "My SignOff Locked!", Toast.LENGTH_LONG).show()
                     GlobalScope.launch {
                         val url2 = URL(ipcomm2)
                         val con2: HttpURLConnection = url2.openConnection() as HttpURLConnection
                             try {
                                 val inp: InputStream = BufferedInputStream(con2.inputStream)
                                 inp.read()
                             } finally {
                                 con2.disconnect()
                             }
                     }
                     i = true
                 }
             }
             camBut.setOnClickListener {
                 val intent = Intent(this, Video::class.java)
                 intent.putExtra("IPValT2", IPVal2)
                 startActivity(intent)
             }
         }
     }