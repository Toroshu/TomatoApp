package toroshu.tomato.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import toroshu.tomato.R
import toroshu.tomato.fragment.PitchFragment

class PitchAdapter(fm: FragmentManager, context: Context) : FragmentStatePagerAdapter(fm) {

    val imageRes = intArrayOf(R.drawable.logo,
            R.drawable.logo,
            R.drawable.logo,
            R.drawable.logo,
            R.drawable.logo)


    override fun getItem(position: Int): Fragment {
        return PitchFragment.newInstance(imageRes[position])
    }

    override fun getCount(): Int {
        return imageRes.size
    }


}
