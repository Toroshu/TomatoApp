package toroshu.tomato.ui

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast
import toroshu.tomato.R
import toroshu.tomato.utils.BaseActivity
import toroshu.tomato.utils.Constant

class Login : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initUI()
    }

    fun initUI() {

        tv_welcome.text = "Welcome back, %s".format(getPrefs().getString(Constant.Username))

        fab_login.setOnClickListener {
            val password = et_password.text.toString()
            if (password == getPrefs().getString(Constant.Password)) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                toast(R.string.wrong_password)
            }
        }

    }


}
