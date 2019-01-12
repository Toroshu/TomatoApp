package toroshu.tomato.dao

import androidx.room.*
import toroshu.tomato.models.Hero

@Dao
public interface HeroDao {

    @Insert
    fun insert(hero: Hero)

    @Update
    fun update(hero: Hero)

    @Delete
    fun delete(hero: Hero)

    @Query("SELECT * FROM hero")
    fun getAll(): List<Hero>
}