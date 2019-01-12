package toroshu.tomato.ui

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.toolbar.*
import toroshu.tomato.R
import toroshu.tomato.utils.BaseActivity
import toroshu.tomato.utils.Constant
import toroshu.tomato.utils.Utils

class CreateAccount : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        initUI()
    }

    fun initUI() {

        toolbar.title = "Let's get started"
        setSupportActionBar(toolbar)

        fab_create_account.setOnClickListener {
            createAccount()
        }

    }


    private fun createAccount() {

        val userName = et_name.text.toString().trim()
        val password = et_password.text.toString().trim()

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

        startActivity(Intent(this, MainActivity::class.java))

    }

}
