/*
 © Вихрянов Т. Д., 2022

 Этот файл — часть Showdown Score.

 Showdown Score — свободная программа: вы можете перераспространять ее и/или изменять ее на условиях Стандартной
 общественной лицензии GNU в том виде, в каком она была опубликована Фондом свободного программного обеспечения;
 либо версии 3 лицензии, либо (по вашему выбору) любой более поздней версии.

 Showdown Score распространяется в надежде, что она будет полезной, но БЕЗО ВСЯКИХ ГАРАНТИЙ; даже без неявной гарантии
 ТОВАРНОГО ВИДА или ПРИГОДНОСТИ ДЛЯ ОПРЕДЕЛЕННЫХ ЦЕЛЕЙ. Подробнее см. в Стандартной общественной лицензии GNU.

 Вы должны были получить копию Стандартной общественной лицензии GNU вместе с этой программой. Если это не так,
 см. <https://www.gnu.org/licenses/>.
 */

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