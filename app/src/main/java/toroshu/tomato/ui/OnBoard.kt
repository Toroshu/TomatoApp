package toroshu.tomato.ui

import android.content.Intent
import android.os.Bundle
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.activity_onboard.*
import org.jetbrains.anko.toast
import toroshu.tomato.R
import toroshu.tomato.utils.BaseActivity
import toroshu.tomato.utils.Constant

class OnBoard : BaseActivity() {

    var tncAgreed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboard)

        initUI()
    }

    fun initUI() {

        btn_proceed.isClickable = false

        btn_proceed.setOnClickListener {
            completeSignUp()
        }

        checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            tncAgreed = isChecked

            btn_proceed.isClickable = tncAgreed

        }

        tv_tnc.setOnClickListener {
            MaterialDialog(this@OnBoard)
                    .title(text = "Whose phone numbers should I add ?")
                    .message(R.string.tnc_details)
                    .positiveButton(R.string.okay)
                    .show()
        }

        btn_help.setOnClickListener {
            MaterialDialog(this@OnBoard)
                    .title(text = "Whose phone numbers should I add ?")
                    .message(text = "If your phone ever gets stolen, these two numbers will get the alerts " +
                            " and the details of intruder\'s sim via SMS." +
                            " Note: Enter the phone numbers of only those persons, you trust.")
                    .positiveButton(R.string.okay)
                    .show()
        }
    }

    fun completeSignUp() {

        val firstHero = et_first_hero.text.toString().trim()
        val secondHero = et_second_hero.text.toString().trim()


        if (firstHero.length != 10 || secondHero.length != 10) {
            toast(R.string.invalid_number)
            return
        }

        if (firstHero.equals(secondHero))
            toast(R.string.warn_same_number)

        getPrefs().setString(Constant.FirstSuperHero, firstHero)
        getPrefs().setString(Constant.SecondSuperHero, secondHero)
        getPrefs().setBoolean(Constant.AlreadyRegistered, true)


        startActivity(Intent(this, MainActivity::class.java))

    }
}
