package toroshu.tomato.ui

import android.content.Intent
import android.os.Bundle
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.activity_onboard.*
import toroshu.tomato.R
import toroshu.tomato.deprecated.Status
import toroshu.tomato.utils.BaseActivity
import toroshu.tomato.utils.Constant
import toroshu.tomato.utils.Utils

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
            MaterialDialog.Builder(this@OnBoard)
                    .title("Whose phone numbers should I add ?")
                    .content(R.string.tnc_details)
                    .positiveText("Okay")
                    .show()
        }

        btn_help.setOnClickListener {
            MaterialDialog.Builder(this@OnBoard)
                    .title("Whose phone numbers should I add ?")
                    .content("If your phone ever gets stolen, these two numbers will get the alerts " +
                            " and the details of intruder\'s sim via SMS." +
                            " Note: Enter the phone numbers of only those persons, you trust.")
                    .positiveText("Okay")
                    .show()
        }
    }

    fun completeSignUp() {

        val firstHero = et_first_hero.text.toString().trim()
        val secondHero = et_second_hero.text.toString().trim()


        if (firstHero.length != 10 || secondHero.length != 10) {
            Utils().toast(this, R.string.invalid_number)
            return
        }

        if (firstHero.equals(secondHero))
            Utils().toast(this, R.string.warn_same_number)

        getPrefs().setString(Constant.FirstSuperHero, firstHero)
        getPrefs().setString(Constant.SecondSuperHero, secondHero)
        getPrefs().setBoolean(Constant.AlreadyRegistered, true)


        startActivity(Intent(this, MainActivity::class.java))

    }
}
