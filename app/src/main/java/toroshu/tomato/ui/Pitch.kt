package toroshu.tomato.ui

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pitch.*
import toroshu.tomato.R
import toroshu.tomato.adapter.PitchAdapter
import toroshu.tomato.utils.BaseActivity

class Pitch : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pitch)

        initUI()

    }

    fun initUI() {


        vp_pitch.adapter = PitchAdapter(context = this, fm = supportFragmentManager)

        indicator.setViewPager(vp_pitch)

        btn_get_started.setOnClickListener {
            startActivity(Intent(this, CreateAccount::class.java))
        }
    }
}
