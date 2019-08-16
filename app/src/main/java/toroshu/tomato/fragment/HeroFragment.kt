package toroshu.tomato.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_hero.*
import org.jetbrains.anko.toast
import toroshu.tomato.R
import toroshu.tomato.adapter.HeroAdapter
import toroshu.tomato.interfaces.OnClickListener
import toroshu.tomato.models.Hero
import toroshu.tomato.utils.BaseActivity
import toroshu.tomato.utils.Constant
import java.util.*


class HeroFragment : Fragment(), OnClickListener {


    companion object {
        @JvmStatic
        fun newInstance(phone: String = "", heros: List<Hero>): HeroFragment {

            return HeroFragment().apply {
                arguments = Bundle().apply {
                    putString(Constant.PHONE, phone)
                    putParcelableArrayList(Constant.Heroes, heros as ArrayList<Hero>)
                }
            }

        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hero, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var heros: List<Hero> = arguments?.getParcelableArrayList<Hero>(Constant.Heroes)
                ?: mutableListOf<Hero>().toList()
//                (activity as BaseActivity).getDb().heroDao().getAll()


        rv_hero.adapter = HeroAdapter(context!!, this, heros)
        rv_hero.layoutManager = LinearLayoutManager(context)

        iv_no_content.visibility = if (heros.isNullOrEmpty())
            View.VISIBLE
        else
            View.GONE

        val hero = arguments?.getString(Constant.PHONE) ?: ""
        arguments?.clear()

        fab_add_hero.setOnClickListener {
            context?.toast("Add Hero")
            AddHeroFragment.newInstance(hero, false).show((activity as BaseActivity).supportFragmentManager, "Tag")
        }

        if (hero.isNotEmpty())
            fab_add_hero.performClick()
    }


    override fun onClick(position: Int, any: Any) {
        AddHeroFragment.newInstance((any as Hero).phone, false).show((activity as BaseActivity).supportFragmentManager, "Tag")
    }
}
