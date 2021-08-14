package com.kgstrivers.InstaC

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        lsignupbutton.setOnClickListener {

            startActivity(Intent(this,SignUpActivity::class.java))
        }

        loginbutton.setOnClickListener{


            signin()

        }
    }

    private fun signin() {

        val email = signinemail.text.toString()
        val pass = signinpassword.text.toString()

        when{

            TextUtils.isEmpty(email) ->Toast.makeText(this,"Email Missing",Toast.LENGTH_LONG).show()
            TextUtils.isEmpty(pass) ->Toast.makeText(this,"Password Missing",Toast.LENGTH_LONG).show()

            else->
            {
                val progressdialog  = ProgressDialog(this)
                progressdialog.setTitle("Signing in")
                progressdialog.setMessage("Please Wait For a While")
                progressdialog.setCanceledOnTouchOutside(false)
                progressdialog.show()
                val mAuth : FirebaseAuth = FirebaseAuth.getInstance()

                mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {

                    task->
                   if(task.isSuccessful)
                   {
                       Toast.makeText(this,"Sign In Successful",Toast.LENGTH_LONG).show()
                       progressdialog.dismiss()
                       startActivity(Intent(this,MainActivity::class.java))
                   }
                    else
                   {
                       val err =  task.exception.toString()
                       Toast.makeText(this,"Error: $err",Toast.LENGTH_LONG).show()
                       progressdialog.dismiss()
                   }
                }
            }
        }
    }
}