package toroshu.tomato.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.telephony.TelephonyManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.toast
import timber.log.Timber
import toroshu.tomato.R
import toroshu.tomato.fragment.HeroFragment
import toroshu.tomato.fragment.StatusFragment
import toroshu.tomato.interfaces.Contacts
import toroshu.tomato.models.Hero
import toroshu.tomato.utils.BaseActivity

class MainActivity : BaseActivity(), Contacts {

    lateinit var tm: TelephonyManager
    lateinit var heros: List<Hero>
    var heroNumber = ""

    val MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1083
    val CAPTURE_CONTACTS = 200


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        initUI()

    }

    fun initUI() {

        heros = getDb().heroDao().getAll()

        navigation_view.setOnNavigationItemSelectedListener { item ->
            run {
                toolbar?.title = item.title
                val fragment = when (item.itemId) {
                    R.id.action_manage_hero -> HeroFragment.newInstance(heros = heros)
                    R.id.action_home -> StatusFragment()
                    R.id.action_help -> HeroFragment()
                    else -> HeroFragment()
                }
                supportFragmentManager.beginTransaction().replace(R.id.fl_container,
                        fragment).commit()
                true
            }
        }


//        tm = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

//        if (getPrefs().getBoolean(Constant.ProtectionOn))
//            iv_status.setImageResource(R.drawable.dragon)
//        else {
//            iv_status.setImageResource(R.drawable.sad)
//        }
//
//        btn_security.setOnClickListener {
//            hearShakes()
//        }

//        navigation_view.setNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.action_about ->
//
//                    R.id.action_about
//                ->
//            }
//
//            return@setNavigationItemSelectedListener
//
//        }


    }


    override fun pickContact() {

        val askForPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED

        Timber.e("pickContact " + askForPermission)

        if (askForPermission) {

//            // Permission is not granted
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                            Manifest.permission.READ_CONTACTS)) {
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//
//                toast("here")
////            } else {

            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_CONTACTS),
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS)

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
//            }
        } else {
            toast("there")

            openContactsApp()
        }
    }

    fun openContactsApp() = startActivityForResult(Intent().apply {
        action = Intent.ACTION_PICK
        data = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
    }, CAPTURE_CONTACTS)


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data == null || requestCode != CAPTURE_CONTACTS || resultCode != Activity.RESULT_OK) {
            toast(R.string.app_error)
            return
        }

        val contactUri = data.data ?: return

        val cursor = contentResolver.query(contactUri, null, null, null, null) ?: return

        cursor.moveToFirst()
        val column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)

        heroNumber = cursor.getString(column)
        Timber.d("Hero Picked: %s", heroNumber)

        cursor.close()

        supportFragmentManager.beginTransaction().replace(R.id.fl_container,
                HeroFragment.newInstance(heroNumber, heros)).commit()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            MY_PERMISSIONS_REQUEST_READ_CONTACTS ->
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    openContactsApp()
                }
        }
    }

    override fun contactPicked(phone: String) {
        toast(phone)
        getDb().heroDao().insert(Hero(phone = phone))

        heros = getDb().heroDao().getAll()
        heroNumber = ""

        supportFragmentManager.beginTransaction().replace(R.id.fl_container,
                HeroFragment.newInstance(heroNumber, heros)).commit()



    }





//
//    fun changePassword() {
//        val oldPassword = getPrefs().getString(Constant.Password)
//
//        MaterialDialog.Builder(this@MainActivity)
//                .title("Enter a new password")
//                .content("The new password must contain atleast six characters")
//                .input("Password", oldPassword, MaterialDialog.InputCallback { dialog, input ->
//                    val newPassword = input.toString().trim()
//                    if (newPassword.length >= 6) {
//                        getPrefs().setString(Constant.Password, newPassword)
//                        toast("Password has been successfully changed.")
//
//                    } else {
//                        toast("This password can\'t be accepted")
//                    }
//                })
//                .show()
//    }
//
//    fun showPin() {
//        val password = getPrefs().getString(Constant.Password)
//        val start = password.length - 4
//
//        MaterialDialog.Builder(this)
//                .title("PIN Details")
//                .content("Send a custom SMS \"siren " + password.substring(start) +
//                        "\" (last 4 characters of your password) from any number " +
//                        "to start the siren in your phone. Check out the FAQ for more details.")
//                .positiveText("Got it")
//                .show();
//    }
//
//
//    fun info() {
//        MaterialDialog.Builder(this)
//                .title("Important")
//                .content("You should turn off the Tomato protection before doing that. " +
//                        "After putting in the new SIM you can turn on the protection. Please note " +
//                        "that if you are using a dual SIM phone, the position of SIM cards do matter.")
//                .positiveText("Got it")
//                .show()
//    }
//
//    fun showWarning() {
//        MaterialDialog.Builder(this)
//                .title("Warning")
//                .icon(getResources().getDrawable(R.drawable.ic_warning_black_18dp))
//                .content("You are turning off the tomato protection." +
//                        " Do you want to proceed ?")
//                .positiveText("Yes")
//                .negativeText("No")
//                .onPositive { dialog, which ->
//                    getPrefs().setBoolean(Constant.ProtectionOn, false)
//                }
//                .show()
//    }
//
//    @SuppressLint("MissingPermission")
//    fun hearShakes() {
//
//        if (getPrefs().getBoolean(Constant.ProtectionOn)) {
//            showWarning()
//
//        } else {
//            // Read SIM Serial number
//            getPrefs().setString(Constant.SimId, tm.getSimSerialNumber())
//            showFeatures();
//        }
//
//    }
//
//
//    fun showFeatures() {
//
//        MaterialDialog.Builder(this)
//                .title("Info")
//                .icon(getResources().getDrawable(R.drawable.ic_info_black_24dp))
//                .content("Now you can:\n\t-get SIM change alerts\n\t-track your phone with a SMS" +
//                        "\n\t-start siren with another SMS")
//                .positiveText("Got it")
//                .show()
//    }
//


}
