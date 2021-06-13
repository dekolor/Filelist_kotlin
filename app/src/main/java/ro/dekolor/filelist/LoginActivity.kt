package ro.dekolor.filelist

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    private lateinit var mSharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mSharedPreferences = getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)

        btn_save.setOnClickListener {
            saveUser()
        }

        btn_website.setOnClickListener {
            val url : String = "https://filelist.io/my.php"
            val i : Intent = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(url))
            startActivity(i);
        }
    }

    fun saveUser() {
        val username: String = et_username.text.toString()
        val passkey: String = et_passkey.text.toString()

        if(validateForm(username, passkey)) {
            showProgressDialog(resources.getString(R.string.please_wait))
            val editor = mSharedPreferences.edit()
            editor.putString(Constants.USERNAME, username)
            editor.putString(Constants.PASSKEY, passkey)
            editor.apply()
            Toast.makeText(this, "Username and passkey are now saved, you can use the app", Toast.LENGTH_SHORT).show()
            setResult(Activity.RESULT_OK)
            hideProgressDialog()
            finish()
        }
    }

    private fun validateForm(username: String, passkey: String): Boolean {
        return when {
            TextUtils.isEmpty(username) -> {
                showErrorSnackBar("Please enter a username")
                false
            }
            TextUtils.isEmpty(passkey) -> {
                showErrorSnackBar("Please enter a passkey")
                false
            } else -> {
                true
            }
        }
    }
}