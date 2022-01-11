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

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("net.timwca.showdownscore", appContext.packageName)
    }
}