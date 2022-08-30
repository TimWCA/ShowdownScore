package net.timwca.showdownscore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TeamModeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_mode)
        supportActionBar?.hide()
    }
}