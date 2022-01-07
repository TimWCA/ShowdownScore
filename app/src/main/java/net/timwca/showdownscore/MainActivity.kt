package net.timwca.showdownscore

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }

    var servNum : Int = 1 // Номер подачи (1-я или 2-я)

    fun btnServingA(view: View){ // Первая подача A
        val serv : TextView = findViewById(R.id.serving)
        serv.text = "Подача: Игрок A - $servNum"
        servNum++
    }
}