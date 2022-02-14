package com.example.movieinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Splash_Screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val thread:Unit=object:Thread(){
            override fun run() {
                try {
                    sleep(3000)
                    finish()
                }catch (e:Exception){
                    e.printStackTrace()
                }finally {
                    val intent=Intent(this@Splash_Screen,MainActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                }
            }
        }.start()
    }
}