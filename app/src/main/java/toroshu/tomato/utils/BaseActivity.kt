package toroshu.tomato.utils

import android.app.Activity
import android.os.Bundle

open class BaseActivity : Activity() {

    lateinit var app: App

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        app = (application as App)

    }

    fun getPrefs(): Prefs = app.prefs
}
