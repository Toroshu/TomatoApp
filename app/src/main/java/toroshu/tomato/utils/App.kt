package toroshu.tomato.utils

import android.app.Application

class App : Application() {

    lateinit var prefs: Prefs

    override fun onCreate() {
        super.onCreate()

        prefs = Prefs(this)

    }

}