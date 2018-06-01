package toroshu.tomato.utils

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {

    var editor: SharedPreferences.Editor
    var sharedPrefs: SharedPreferences


    init {
        sharedPrefs = context.getSharedPreferences(Constant.Tomato, Context.MODE_PRIVATE)
        editor = sharedPrefs.edit()
    }


    fun setBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.commit()
    }

    fun setString(key: String, value: String) {
        editor.putString(key, value)
        editor.commit()
    }

    fun setInt(key: String, value: Int) {
        editor.putInt(key, value)
        editor.commit()
    }

    fun getBoolean(key: String, default: Boolean = false): Boolean {
        return sharedPrefs.getBoolean(key, default)
    }

    fun getString(key: String, default: String = ""): String {
        return sharedPrefs.getString(key, default)
    }

    fun getInt(key: String, default: Int = -1): Int {
        return sharedPrefs.getInt(key, default)
    }


}