/*
 © Вихрянов Т. Д., 2022

 Этот файл — часть Showdown Score.

 Showdown Score — свободная программа: вы можете перераспространять ее и/или изменять ее на условиях Стандартной
 общественной лицензии GNU в том виде, в каком она была опубликована Фондом свободного программного обеспечения;
 либо версии 3 лицензии, либо (по вашему выбору) любой более поздней версии.

 Showdown Score распространяется в надежде, что она будет полезной, но БЕЗО ВСЯКИХ ГАРАНТИЙ; даже без неявной гарантии
 ТОВАРНОГО ВИДА или ПРИГОДНОСТИ ДЛЯ ОПРЕДЕЛЕННЫХ ЦЕЛЕЙ. Подробнее см. в Стандартной общественной лицензии GNU.

 Вы должны были получить копию Стандартной общественной лицензии GNU вместе с этой программой. Если это не так,
 см. <https://www.gnu.org/licenses/>.Вы должны были получить копию Стандартной общественной лицензии GNU вместе с этой
 программой. Если это не так, см. <https://www.gnu.org/licenses/>.
 */

package ru.timwca.showdownscore

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import ru.timwca.showdownscore.R


/* Общие переменные */
lateinit var playerA: EditText // Имя Игрока A
lateinit var playerB: EditText // Имя Игрока B
lateinit var serving: Serving // Экземпляр класса Serving
lateinit var servingText: TextView // Строка для подач
lateinit var score: Score // Экземпляр класса Score
lateinit var pointsA: TextView // Очки Игрока A
lateinit var pointsB: TextView // Очки Игрока B
lateinit var scoreText: TextView // Счёт по сетам
lateinit var set: TextView // Номер сета
lateinit var coinText: TextView // Состояние монетки
var isChange: Boolean = false // Был ли переход

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
        coinText = findViewById(R.id.textCoin)
    }

    /* Массив для сохранения последнего действия
     * pointsA - очки Игрока А
     * pointsB - очки Игрока B
     * servingPlayer - подающий игрок
     * servingNum - номер подачи */
    var save: Map<String, Any> =
        mutableMapOf("pointsA" to 0, "pointsB" to 0, "servingPlayer" to 0, "servingNum" to 1)

    var isStarted: Boolean = true // Программа только запустилась?

    /* Сохранение последних результатов */
    fun saveFun() {
        save += "pointsA" to score.pointsA // Сохранение последних очков Игрока A
        save += "pointsB" to score.pointsB // Сохранение последних очков Игрока A
        save += "servingPlayer" to serving.servingPlayer // Сохранение подающего игрока
        save += "servingNum" to serving.servNum // Сохранение номера подачи

        isStarted = false
    }

    /* Добавляет очки игроку и обновляет счёт и подачу */
    fun addPoints(player: Boolean, points: Int) {
        if (isStarted) {
            save += "servingPlayer" to serving.servingPlayer
            save += "servingNum" to 1
            isStarted = false
        } else saveFun()
        score.addPoints(player, points) // Добавить 2 очка Игроку А
        var isSetEnd = score.scoreUpdate() // Обновить счёт по сетам
        if (isSetEnd) {
            /* Меняем первую подачу */
            serving.servingPlayer = serving.firstServingPlayer
            serving.firstServingPlayer = !serving.firstServingPlayer
            serving.servNum = 1
            servingText.text = serving.servText(playerA.text.toString(), playerB.text.toString())
            val toast: Toast = Toast.makeText(
                this,
                "Победитель: ${if (!score.winner) playerA.text else playerB.text} ",
                Toast.LENGTH_LONG
            )
            toast.show()
        }

        /* Уведомление о переходе в 3 сете */
        if (!isChange) {
            if ((score.set == 3) and ((score.pointsA >= 6) or (score.pointsB >= 6))) {
                val toast: Toast = Toast.makeText(this, "Переход", Toast.LENGTH_SHORT)
                toast.show()
                isChange = true
            }
        }
        pointsA.text = score.pointsA.toString() // Обновить очки Игрока A
        pointsB.text = score.pointsB.toString() // Обновить очки Игрока B
        scoreText.text = "Счёт: ${score.scoreA} : ${score.scoreB}"
        set.text = "Сет: ${score.set}" // Обновить сет

        servingText.text = serving.servText(
            playerA.text.toString(),
            playerB.text.toString()
        ) // Обновить строку подачи
    }

    /* Выставляет первую подачу и выводит строку подачи */
    fun serv(player: Boolean) {
        if (serving.isFirstServing) { // Если это первая подача в игре
            serving.servNum = 1
            serving.firstServingPlayer = player // Игрок A
            servingText.text =
                serving.servText(
                    playerA.text.toString(),
                    playerB.text.toString()
                ) // Формирование новой строки подачи
        }
    }

    /* Обработчики нажатия кнопок выбора первой подачи */
    fun btnServingA(view: View) {
        serv(false)
    }

    fun btnServingB(view: View) {
        serv(true)
    }

    /* Обработчики нажатия кнопок добавления очков */
    fun add2PlayerA(view: View) {
        addPoints(false, 2)
    }

    fun add1PlayerA(view: View) {
        addPoints(false, 1)
    }

    fun add2PlayerB(view: View) {
        addPoints(true, 2)
    }

    fun add1PlayerB(view: View) {
        addPoints(true, 1)
    }

    /* Обработчик нажатия кнопки сброса */
    fun restartApp(view: View) {
        playerA.setText("Игрок A")
        playerB.setText("Игрок B")

        this.recreate()
    }

    fun undo(view: View) { // Обработчик нажатия кнопки Undo
        if (!isStarted) {
            /* При вызове функции сразу после запуска программа крашится.
            * Для этого проверяем, были ли какие-то действия после запуска. */
            score.pointsA = save["pointsA"] as Int
            score.pointsB = save["pointsB"] as Int
            serving.servingPlayer = save["servingPlayer"] as Boolean
            serving.servNum = save["servingNum"] as Int

            servingText.text =
                serving.servTextWithoutUpdate(playerA.text.toString(), playerB.text.toString())
            score.scoreUpdate()
            pointsA.text = score.pointsA.toString()
            pointsB.text = score.pointsB.toString()
        }
    }

    /* Обработчик нажатия кнопки "Монетка" */
    fun coin(view: View) {
        if ((0..1).random() == 0) {
            coinText.setText("Красный")
            coinText.setTextColor(Color.parseColor("#FF0000"))
        } else {
            coinText.setText("Зелёный")
            coinText.setTextColor(Color.parseColor("#00FF00"))
        }
    }
}