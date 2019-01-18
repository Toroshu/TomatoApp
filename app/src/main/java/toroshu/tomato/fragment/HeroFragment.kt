package toroshu.tomato.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_hero.*
import org.jetbrains.anko.toast
import timber.log.Timber
import toroshu.tomato.R
import toroshu.tomato.models.Hero
import toroshu.tomato.utils.BaseActivity


class HeroFragment : Fragment() {

    lateinit var heros: List<Hero>



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hero, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        heros = (activity as BaseActivity).getDb().heroDao().getAll()

        Timber.d(heros.toString())

        iv_no_content.visibility = if (heros.isEmpty())
            View.VISIBLE
        else
            View.GONE

        fab_add_hero.setOnClickListener {
            context?.toast("Add Hero")
        }

    }
}
