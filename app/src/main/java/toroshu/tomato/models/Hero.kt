package toroshu.tomato.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Hero(
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,
        val phone: String) : Parcelable {

        override fun toString(): String {
                return super.toString()
        }
}