package toroshu.tomato.utils

import android.app.Application
import androidx.room.Room
import timber.log.Timber

class App : Application() {

    lateinit var prefs: Prefs
    lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()

        prefs = Prefs(this)

        db = Room.databaseBuilder(
                this,
                AppDatabase::class.java,
                "tomato").build()


        Timber.plant(Timber.DebugTree())


    }


}