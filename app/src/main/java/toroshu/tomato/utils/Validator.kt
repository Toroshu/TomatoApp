package toroshu.tomato.utils

import android.content.Context
import android.text.TextUtils
import toroshu.tomato.R

class Validator(var isValid: Boolean = false,
                var message: String = "") {

    companion object {

        fun phone(phone: String, context: Context) = Validator().apply {
            when {
                phone.length != 10 -> this.message = context.getString(R.string.phone_too_short)
                !TextUtils.isDigitsOnly(phone) -> this.message = context.getString(R.string.invalid_phone)
                else -> this.isValid = true
            }
        }

        fun name(name: String, context: Context) = Validator().apply {
            when {
                name.length < 3 -> this.message = context.getString(R.string.name_too_short)
                else -> this.isValid = true
            }
        }

        fun newPassword(name: String, context: Context) = Validator().apply {
            when {
                name.length < 3 -> this.message = context.getString(R.string.new_password_too_short)
                else -> this.isValid = true
            }
        }

        fun oldPassword(name: String, context: Context) = Validator().apply {
            when {
                name.length < 3 -> this.message = context.getString(R.string.name_too_short)
                else -> this.isValid = true
            }
        }
    }
}