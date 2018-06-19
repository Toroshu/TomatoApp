package toroshu.tomato.ui

import android.content.Intent
import android.os.Bundle
import toroshu.tomato.utils.BaseActivity
import toroshu.tomato.utils.Constant

class Splash : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (getPrefs().getBoolean(Constant.AlreadyRegistered))
            startActivity(Intent(this, Login::class.java))
        else
            startActivity(Intent(this, OnBoard::class.java))

    }
}
