package toroshu.tomato.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import toroshu.tomato.R
import toroshu.tomato.utils.BaseActivity
import toroshu.tomato.utils.Constant
import toroshu.tomato.utils.Utils

class Login : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initUI()
    }

    fun initUI() {

        val alreadyRegistered = getPrefs().getBoolean(Constant.AlreadyRegistered,
                false)

        if (alreadyRegistered) {
            btn_create_account.visibility = View.GONE
        }


        btn_create_account.setOnClickListener {
            createAccount()
        }

        btn_fyp.setOnClickListener {
            forgotPassword()
        }
    }

    fun forgotPassword() {

    }

    private fun createAccount() {

        var userName = et_user.text.toString().trim()
        var password = et_password.text.toString().trim()

        if (Utils().isEmptyOrNull(userName)) {
            Utils().toast(this, R.string.warn_username_short)
            return
        }

        if (Utils().isEmptyOrNull(password)) {
            Utils().toast(this, R.string.warn_password_short)
            return
        }

        getPrefs().setString(Constant.Username, userName)
        getPrefs().setString(Constant.Password, password)

        startActivity(Intent(this, Onboard::class.java))

    }
}
