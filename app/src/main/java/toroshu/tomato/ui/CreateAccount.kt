package toroshu.tomato.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import kotlinx.android.synthetic.main.activity_create_account.*
import toroshu.tomato.R
import toroshu.tomato.utils.BaseActivity
import toroshu.tomato.utils.Constant
import toroshu.tomato.utils.Prefs
import toroshu.tomato.utils.Utils

class CreateAccount : BaseActivity() {

    var heroNumber = ""

    val CAPTURE_CONTACTS = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
    }


    private fun createAccount() {

        val userName = et_name.text.toString().trim()
        val password = et_password.text.toString().trim()

        if (Utils().isEmptyOrNull(userName)) {
            Utils().toast(this, R.string.warn_username_short)
            return
        }

        if (Utils().isEmptyOrNull(password)) {
            Utils().toast(this, R.string.warn_password_short)
            return
        }

        getPrefs().setString(Constant.Username, userName)
        getPrefs().setString(Constant.Password, password)

        startActivity(Intent(this, OnBoard::class.java))

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

        val contactUri = data.data
        val cursor = contentResolver.query(contactUri, null, null, null, null)
        cursor.moveToFirst()
        val column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
        cursor.close()

        heroNumber = cursor.getString(column)


    }
}
