package toroshu.tomato.utils

import androidx.room.Database
import androidx.room.RoomDatabase
import toroshu.tomato.dao.HeroDao
import toroshu.tomato.models.Hero

@Database(entities = [Hero::class], version = 2)
public abstract class AppDatabase : RoomDatabase() {
    abstract fun heroDao(): HeroDao
}