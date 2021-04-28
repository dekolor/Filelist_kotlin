package ro.dekolor.filelist

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    companion object {
        const val LOGIN_REQUEST_CODE: Int = 11
    }

    private lateinit var mSharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mSharedPreferences = getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)

        Handler().postDelayed({
            checkIfLoginData()
        }, 2500)
    }

    private fun checkIfLoginData() {
        val username = mSharedPreferences.getString(Constants.USERNAME, "")
        val passkey = mSharedPreferences.getString(Constants.PASSKEY, "")

        if(username.isNullOrEmpty() || passkey.isNullOrEmpty()) {
            startActivityForResult(
                Intent(this, LoginActivity::class.java),
                SplashActivity.LOGIN_REQUEST_CODE
            )
        } else {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == SplashActivity.LOGIN_REQUEST_CODE) {
            checkIfLoginData()
        }
    }
}