package toroshu.tomato.utils

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Typeface
import android.widget.Toast

/**
 * Created by saini on 08-01-2016.
 */
class Phone(internal var mContext: Context) {

    internal var prefs: SharedPreferences
    internal var editor: SharedPreferences.Editor

    val isRegistered: Boolean
        get() = prefs.getBoolean(Constant.ALREADY_REGISTERED, false)

    var username: String?
        get() = prefs.getString(Constant.USERNAME, null)
        set(username) {
            editor.putString(Constant.USERNAME, username)
            editor.commit()
        }

    var password: String?
        get() = prefs.getString(Constant.PASSWORD, null)
        set(password) {
            editor.putString(Constant.PASSWORD, password)
            editor.commit()
        }

    val fsh: String?
        get() = prefs.getString(Constant.FIRST_SUPERHERO, null)

    val isProtectionOn: Boolean
        get() = prefs.getBoolean(Constant.PROTECTION_ON, false)

    var simId: String?
        get() = prefs.getString(Constant.SIM_ID, null)
        set(id) {
            editor.putBoolean(Constant.PROTECTION_ON, true)
            editor.putString(Constant.SIM_ID, id)
            editor.commit()
        }

    val isAlertModeOn: Boolean
        get() = prefs.getBoolean(Constant.ALERT_MODE, false)

    val ssh: String?
        get() = prefs.getString(Constant.SECOND_SUPERHERO, null)

    var trackingStatus: Boolean
        get() = prefs.getBoolean(Constant.TRACK, false)
        set(status) {
            editor.putBoolean(Constant.TRACK, status)
            editor.apply()
        }

    val typeface: Typeface
        get() = Typeface.createFromAsset(mContext.assets, "fonts/latom.ttf")


    init {
        prefs = mContext.getSharedPreferences(Constant.PREFS, Context.MODE_PRIVATE)
        editor = prefs.edit()
    }

    fun registerPhone(firstSuperHero: String, secondSuperHero: String) {

        editor.putString(Constant.FIRST_SUPERHERO, firstSuperHero)

        editor.putString(Constant.SECOND_SUPERHERO, secondSuperHero)
        editor.putBoolean(Constant.ALREADY_REGISTERED, true)
        editor.putString(Constant.PIN, System.currentTimeMillis().toString().substring(6, 10))
        editor.apply()
    }

    fun disableProtection() {
        editor.putBoolean(Constant.PROTECTION_ON, false)
        editor.apply()
    }

    fun startAlertMode() {
        editor.putBoolean(Constant.ALERT_MODE, true)
        editor.apply()
    }

    fun stopAlertMode() {
        editor.putBoolean(Constant.ALERT_MODE, false)
        editor.apply()
    }

    fun setnu(n: Int) {
        editor.putInt(Constant.NUM_UPDATES, n)
        editor.apply()
    }

    fun toast(s: String) {
        Toast.makeText(mContext, s, Toast.LENGTH_LONG).show()
    }


}
