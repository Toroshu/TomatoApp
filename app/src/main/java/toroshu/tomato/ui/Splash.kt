package toroshu.tomato.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*
import toroshu.tomato.R

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        iv_mid.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW)
                    .setData(Uri.parse("http://www.madewithlove.org.in")))
        }

        iv_logo.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }
    }
}
