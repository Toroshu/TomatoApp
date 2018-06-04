package toroshu.tomato.utils

import android.content.Context
import android.widget.Toast

class Utils {
    fun isEmptyOrNull(str: String?): Boolean = str == null || str.trim().isEmpty()

    fun toast(context: Context, str: String) = Toast.makeText(context, str, Toast.LENGTH_SHORT).show()

    fun toast(context: Context, str: Int) = Toast.makeText(context, str, Toast.LENGTH_SHORT).show()

}