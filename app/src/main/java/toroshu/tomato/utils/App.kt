package toroshu.tomato.utils

import android.app.Application
import timber.log.Timber

class App : Application() {

    lateinit var prefs: Prefs

    override fun onCreate() {
        super.onCreate()

        prefs = Prefs(this)

        Timber.plant(Timber.DebugTree())

    }

}