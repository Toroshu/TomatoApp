package toroshu.tomato.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.telephony.TelephonyManager
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import timber.log.Timber
import toroshu.tomato.R
import toroshu.tomato.utils.BaseActivity
import toroshu.tomato.utils.Constant
import toroshu.tomato.utils.Utils

class MainActivity : BaseActivity() {

    lateinit var tm: TelephonyManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        initUI()

    }

    fun initUI() {

        navigation_view.setOnNavigationItemSelectedListener { item ->
            run {
                toolbar.title = item.title
//                when (item.itemId) {
//                    R.id.action_help -> toolbar.title = item.title
//                }
                return@setOnNavigationItemSelectedListener true
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


    fun openContacts() {
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
        startActivityForResult(intent, CAPTURE_CONTACTS)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data == null || requestCode != CAPTURE_CONTACTS || resultCode != Activity.RESULT_OK) {
            Utils().toast(this, R.string.app_error)
            return
        }

        val contactUri = data.data ?: return

        val cursor = contentResolver.query(contactUri, null, null, null, null)
        cursor.moveToFirst()
        val column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)

        heroNumber = cursor.getString(column)
        Timber.e(heroNumber)

//        et_hero.setText(heroNumber)

        cursor.close()

    }

    var heroNumber = ""

    val CAPTURE_CONTACTS = 200


    fun changePassword() {
        val oldPassword = getPrefs().getString(Constant.Password)

        MaterialDialog.Builder(this@MainActivity)
                .title("Enter a new password")
                .content("The new password must contain atleast six characters")
                .input("Password", oldPassword, MaterialDialog.InputCallback { dialog, input ->
                    val newPassword = input.toString().trim()
                    if (newPassword.length >= 6) {
                        getPrefs().setString(Constant.Password, newPassword)
                        Utils().toast(this@MainActivity,
                                "Password has been successfully changed.")

                    } else {
                        Utils().toast(this@MainActivity,
                                "This password can\'t be accepted")
                    }
                })
                .show()
    }

    fun showPin() {
        val password = getPrefs().getString(Constant.Password)
        val start = password.length - 4

        MaterialDialog.Builder(this)
                .title("PIN Details")
                .content("Send a custom SMS \"siren " + password.substring(start) +
                        "\" (last 4 characters of your password) from any number " +
                        "to start the siren in your phone. Check out the FAQ for more details.")
                .positiveText("Got it")
                .show();
    }


    fun info() {
        MaterialDialog.Builder(this)
                .title("Important")
                .content("You should turn off the Tomato protection before doing that. " +
                        "After putting in the new SIM you can turn on the protection. Please note " +
                        "that if you are using a dual SIM phone, the position of SIM cards do matter.")
                .positiveText("Got it")
                .show()
    }

    fun showWarning() {
        MaterialDialog.Builder(this)
                .title("Warning")
                .icon(getResources().getDrawable(R.drawable.ic_warning_black_18dp))
                .content("You are turning off the tomato protection." +
                        " Do you want to proceed ?")
                .positiveText("Yes")
                .negativeText("No")
                .onPositive { dialog, which ->
                    getPrefs().setBoolean(Constant.ProtectionOn, false)
                }
                .show()
    }

    @SuppressLint("MissingPermission")
    fun hearShakes() {

        if (getPrefs().getBoolean(Constant.ProtectionOn)) {
            showWarning()

        } else {
            // Read SIM Serial number
            getPrefs().setString(Constant.SimId, tm.getSimSerialNumber())
            showFeatures();
        }

    }


    fun showFeatures() {

        MaterialDialog.Builder(this)
                .title("Info")
                .icon(getResources().getDrawable(R.drawable.ic_info_black_24dp))
                .content("Now you can:\n\t-get SIM change alerts\n\t-track your phone with a SMS" +
                        "\n\t-start siren with another SMS")
                .positiveText("Got it")
                .show()
    }



}
