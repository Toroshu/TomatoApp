package toroshu.tomato.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Hero(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val phone: String) {

        override fun toString(): String {
                return super.toString()
        }
}