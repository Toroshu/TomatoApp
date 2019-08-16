package toroshu.tomato.ui

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.toast
import toroshu.tomato.R
import toroshu.tomato.utils.BaseActivity
import toroshu.tomato.utils.Constant
import toroshu.tomato.utils.Validator

class CreateAccount : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        initUI()
    }

    fun initUI() {

        toolbar.title = "Let's get started"
        setSupportActionBar(toolbar)

        btn_create_account.setOnClickListener {
            createAccount()
        }

    }


    private fun createAccount() {

        val userName = et_name.text.toString().trim()
        val password = et_password.text.toString().trim()
        val nameValidator = Validator.name(userName, this)
        val passwordValidator = Validator.newPassword(userName, this)

        if (!nameValidator.isValid) {
            toast(nameValidator.message)
            return
        }

        if (!passwordValidator.isValid) {
            toast(nameValidator.message)
            return
        }

        getPrefs().setString(Constant.Username, userName)
        getPrefs().setString(Constant.Password, password)

        startActivity(Intent(this, MainActivity::class.java))

    }

}
