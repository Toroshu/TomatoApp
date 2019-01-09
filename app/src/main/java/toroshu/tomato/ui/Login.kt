package toroshu.tomato.ui

import android.os.Bundle
import toroshu.tomato.R
import toroshu.tomato.utils.BaseActivity

class Login : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initUI()
    }

    fun initUI() {

//        val alreadyRegistered = getPrefs().getBoolean(Constant.AlreadyRegistered,
//                false)
//
//        if (alreadyRegistered) {
//            btn_create_account.visibility = View.GONE
//        } else {
//            btn_fyp.visibility = View.GONE
//            btn_sign_in.visibility = View.GONE
//        }
//
//
//        btn_create_account.setOnClickListener {
//            createAccount()
//        }
//
//        btn_fyp.setOnClickListener {
//            forgotPassword()
//        }
    }

    fun forgotPassword() {

    }

}
