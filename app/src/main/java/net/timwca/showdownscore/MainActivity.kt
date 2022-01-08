package net.timwca.showdownscore

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

lateinit var playerA : EditText // Имя Игрока A
lateinit var playerB : EditText // Имя Игрока B
lateinit var serving : Serving // Экземпляр класса Serving
lateinit var servingText : TextView // Строка для подач

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        // Инициализация общих переменных
        serving = Serving()
        servingText = findViewById(R.id.serving)
        playerA = findViewById(R.id.playerA)
        playerB = findViewById(R.id.playerB)
    }



    fun btnServingA(view: View){ // Обработчик нажатия кнопки "Первая подача A"
        if (serving.isFirstServing){ // Если это первая подача в игре
            serving.lastServingText = servingText.text.toString() // Сохранение предыдущей строки подачи
            serving.firstServingPlayer = false // Игрок A
            servingText.text = serving.servText(playerA.text.toString(), playerB.text.toString()) // Формирование новой строки подачи
        }
    }

    fun btnServingB(view: View){ // Обработчик нажатия кнопки "Первая подача B"
        if (serving.isFirstServing){ // Если это первая подача в игре
            serving.lastServingText = servingText.text.toString()
            serving.firstServingPlayer = true // Игрок B
            servingText.text = serving.servText(playerA.text.toString(), playerB.text.toString())
        }
    }
}