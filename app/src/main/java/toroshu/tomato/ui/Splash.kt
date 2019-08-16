package toroshu.tomato.ui

import android.content.Intent
import android.os.Bundle
import toroshu.tomato.utils.BaseActivity

class Splash : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (true) {
            startActivity(Intent(this, MainActivity::class.java))
            return
        }


        if (!getPrefs().doesUserExist())
            startActivity(Intent(this, Pitch::class.java))
        else
            startActivity(Intent(this, Login::class.java))

    }
}
