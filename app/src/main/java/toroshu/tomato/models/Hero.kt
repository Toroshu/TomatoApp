package toroshu.tomato.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data public class Hero(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val phone: String) {
}