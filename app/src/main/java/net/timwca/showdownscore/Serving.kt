package net.timwca.showdownscore
/**
 * Класс для функций, связанных с подачами игроков
 */
class Serving {

    /**
     * Определяет, какой игрок подаёт.
     * False - игрок А
     * True - Игрок B
     */
    var servingPlayer : Boolean = false

    /** Определяет номер подачи (1-я или 2-я) */
    var servNum : Int = 2

    /**
     * Определяет, какой игрок подаёт первым в игре.
     * False - игрок А
     * True - Игрок B
     */
    var firstServingPlayer : Boolean = false

    /** Определяет, является ли эта подача первой в игре */
    var isFirstServing = true

    /**
     * Определяет подающего игрока и номер подачи.
     * @param playerA Имя Игрока А
     * @param playerB Имя Игрока B
     * @return Строка формата "Подающий игрок - номер подачи". Например: "Игрок А - 1"
     */
    fun servText(playerA : String = "Игрок А", playerB : String = "Игрок B"): String {
        if (isFirstServing){ // Если первая подача в игре
            isFirstServing = false
            servingPlayer = firstServingPlayer
        } else { // Если не первая подача в игре
            servNum++

            if (servNum > 2) {
                servNum = 1
                servingPlayer = !servingPlayer
            }
        }

        return if (!servingPlayer) "Подача: $playerA - $servNum"
        else "Подача: $playerB - $servNum"
    }
}