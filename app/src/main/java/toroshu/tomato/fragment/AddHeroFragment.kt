package toroshu.tomato.fragment


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_add_hero.*
import toroshu.tomato.R
import toroshu.tomato.interfaces.Contacts
import toroshu.tomato.utils.Constant

/**
 * A simple [Fragment] subclass.
 * Use the [AddHeroFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class AddHeroFragment : BottomSheetDialogFragment() {

    lateinit var contacts: Contacts

    companion object {

        @JvmStatic
        fun newInstance(phone: String?, removeAllowed: Boolean) =
                AddHeroFragment().apply {
                    arguments = Bundle().apply {
                        putString(Constant.PHONE, phone)
                        putBoolean(Constant.RemoveAllowed, removeAllowed)
                    }
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        contacts = activity as Contacts
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_hero, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hero = arguments?.getString(Constant.PHONE) ?: ""
        arguments?.clear()

        et_phone.setText(hero)

        iv_contact.setOnClickListener {
            contacts.pickContact()
        }

        btn_save.setOnClickListener {
            contacts.contactPicked(et_phone.text.toString())
            dismiss()
        }

        iv_dismiss.setOnClickListener { dismiss() }


    }


}
