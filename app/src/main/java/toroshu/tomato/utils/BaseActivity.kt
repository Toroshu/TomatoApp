package toroshu.tomato.utils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    lateinit var app: App

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        app = (application as App)

    }

    fun getPrefs(): Prefs = app.prefs

    fun getDb(): AppDatabase = app.db
}
