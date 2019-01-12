package toroshu.tomato.utils

import androidx.room.Database
import androidx.room.RoomDatabase
import toroshu.tomato.dao.HeroDao
import toroshu.tomato.models.Hero

@Database(entities = arrayOf(Hero::class), version = 1)
abstract public class AppDatabase : RoomDatabase() {
    abstract fun heroDao(): HeroDao
}