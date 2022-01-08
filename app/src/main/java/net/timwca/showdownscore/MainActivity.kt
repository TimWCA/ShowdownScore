package net.timwca.showdownscore

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


/* Общие переменные */
lateinit var playerA : EditText // Имя Игрока A
lateinit var playerB : EditText // Имя Игрока B
lateinit var serving : Serving // Экземпляр класса Serving
lateinit var servingText : TextView // Строка для подач
lateinit var score : Score // Экземпляр класса Score
lateinit var pointsA : TextView // Очки Игрока A
lateinit var pointsB : TextView // Очки Игрока B
lateinit var scoreText : TextView // Счёт по сетам
lateinit var set : TextView // Номер сета

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        /* Инициализация общих переменных */
        serving = Serving()
        servingText = findViewById(R.id.serving)
        playerA = findViewById(R.id.playerA)
        playerB = findViewById(R.id.playerB)
        score = Score()
        pointsA = findViewById(R.id.textScoreA)
        pointsB = findViewById(R.id.textScoreB)
        scoreText = findViewById(R.id.textScore)
        set = findViewById(R.id.textSet)
    }


    fun btnServingA(view: View) { // Обработчик нажатия кнопки "Первая подача A"
        if (serving.isFirstServing) { // Если это первая подача в игре
            serving.servNum = 1
            serving.firstServingPlayer = false // Игрок A
            servingText.text =
                serving.servText(playerA.text.toString(), playerB.text.toString()) // Формирование новой строки подачи
        }
    }

    fun btnServingB(view: View) { // Обработчик нажатия кнопки "Первая подача B"
        if (serving.isFirstServing) { // Если это первая подача в игре
            serving.servNum = 1
            serving.firstServingPlayer = true // Игрок B
            servingText.text = serving.servText(playerA.text.toString(), playerB.text.toString())
        }
    }

    fun add2PlayerA(view: View) {
        score.addPoints(false, 2) // Добавить 2 очка Игроку А
        var isSetEnd = score.scoreUpdate() // Обновить счёт по сетам
        if (isSetEnd) {
            /* Меняем первую подачу */
            serving.servingPlayer = serving.firstServingPlayer // Игрок B
            serving.servNum = 1
            servingText.text = serving.servText(playerA.text.toString(), playerB.text.toString())
        }
        pointsA.text = score.pointsA.toString() // Обновить очки Игрока A
        pointsB.text = score.pointsB.toString() // Обновить очки Игрока B
        scoreText.text = "Счёт: ${score.scoreA} : ${score.scoreB}"
        set.text = "Сет: ${score.set}" // Обновить сет

        servingText.text = serving.servText(playerA.text.toString(), playerB.text.toString()) // Обновить строку подачи
    }

    fun add1PlayerA(view: View) {
        score.addPoints(false, 1) // Добавить 2 очка Игроку А
        var isSetEnd = score.scoreUpdate() // Обновить счёт по сетам
        if (isSetEnd) {
            /* Меняем первую подачу */
            serving.servingPlayer = serving.firstServingPlayer // Игрок B
            serving.servNum = 1
            servingText.text = serving.servText(playerA.text.toString(), playerB.text.toString())
        }
        pointsA.text = score.pointsA.toString() // Обновить очки Игрока A
        pointsB.text = score.pointsB.toString() // Обновить очки Игрока B
        scoreText.text = "Счёт: ${score.scoreA} : ${score.scoreB}"
        set.text = "Сет: ${score.set}" // Обновить сет

        servingText.text = serving.servText(playerA.text.toString(), playerB.text.toString()) // Обновить строку подачи
    }

    fun add2PlayerB(view: View) {
        score.addPoints(true, 2) // Добавить 2 очка Игроку А
        var isSetEnd = score.scoreUpdate() // Обновить счёт по сетам
        if (isSetEnd) {
            /* Меняем первую подачу */
            serving.servingPlayer = serving.firstServingPlayer // Игрок B
            serving.servNum = 1
            servingText.text = serving.servText(playerA.text.toString(), playerB.text.toString())
        }
        pointsA.text = score.pointsA.toString() // Обновить очки Игрока A
        pointsB.text = score.pointsB.toString() // Обновить очки Игрока B
        scoreText.text = "Счёт: ${score.scoreA} : ${score.scoreB}"
        set.text = "Сет: ${score.set}" // Обновить сет

        servingText.text = serving.servText(playerA.text.toString(), playerB.text.toString()) // Обновить строку подачи
    }

    fun add1PlayerB(view: View) {
        score.addPoints(true, 1) // Добавить 2 очка Игроку А
        var isSetEnd = score.scoreUpdate() // Обновить счёт по сетам
        if (isSetEnd) {
            /* Меняем первую подачу */
            serving.servingPlayer = serving.firstServingPlayer // Игрок B
            serving.servNum = 1
            servingText.text = serving.servText(playerA.text.toString(), playerB.text.toString())
        }
        pointsA.text = score.pointsA.toString() // Обновить очки Игрока A
        pointsB.text = score.pointsB.toString() // Обновить очки Игрока B
        scoreText.text = "Счёт: ${score.scoreA} : ${score.scoreB}"
        set.text = "Сет: ${score.set}" // Обновить сет

        servingText.text = serving.servText(playerA.text.toString(), playerB.text.toString()) // Обновить строку подачи
    }

    fun restartApp(view: View) {
        this.recreate()
    }
}