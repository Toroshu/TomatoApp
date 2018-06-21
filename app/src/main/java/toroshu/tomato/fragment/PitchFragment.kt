package toroshu.tomato.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_image.*
import toroshu.tomato.R
import toroshu.tomato.utils.Constant

class PitchFragment() : Fragment() {

    companion object {
        fun newInstance(res: Int): Fragment {
            val fragment = PitchFragment()

            val bundle = Bundle()
            bundle.putInt(Constant.Res, res)

            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        image.setImageResource(arguments!!.getInt(Constant.Res))

    }


}
