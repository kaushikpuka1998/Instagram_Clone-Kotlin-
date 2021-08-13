package com.kgstrivers.InstaC

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        ssignupbutton.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

        signinbutton.setOnClickListener {
            startActivity(Intent(this,SignInActivity::class.java))
        }
    }
}