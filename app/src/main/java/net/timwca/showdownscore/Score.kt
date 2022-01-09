package net.timwca.showdownscore
/**
 * Класс для функций, связанных с подсчётом очков игроков
 */
class Score {

    /** Очки Игрока */
    var pointsA : Int = 0

    /** Очки Игрока B */
    var pointsB : Int = 0

    /** Номер сета */
    var set: Int = 1

    /** Счёт по сетам Игрока */
    var scoreA = 0;

    /** Счёт по сетам Игрока B */
    var scoreB = 0;

    /**
     * Функция добавления очков игроку
     * @param player Игрок, которому добавить очки: False - Игрок А, True - Игрок B
     * @param points Количество очков для прибавления
     * @return Количество очков после прибавления
     */
    fun addPoints(player: Boolean, points: Int): Int {
        return if (!player){
            pointsA += points
            pointsA
        } else {
            pointsB += points
            pointsB
        }
    }

    /**
     * Проверяет, продолжается лм сет или закончен
     * Обновляет номер сета и счёт по сетам
     * @return Закончен ли сет
     */
    fun scoreUpdate(): Boolean {
        // Если Игрок А или Игрок Б набрал 11+ очков с разницей в 2 очка
        if ((pointsA >= 11) or (pointsB >= 11)){
            if (((pointsA - pointsB) >= 2) or ((pointsB - pointsA) >= 2)){
                if (set <= 3){
                    if (set < 3) set++ // Прибавляем номер сета
                    if ((scoreA < 2) and (scoreB < 2)) {
                            if (pointsA > pointsB) scoreA++ // Если выиграл Игрок A
                            else  scoreB++
                        }
                }


                /* Обнуляем счёт */
                pointsA = 0
                pointsB = 0
                return true
            }
        }
        return false
    }
}