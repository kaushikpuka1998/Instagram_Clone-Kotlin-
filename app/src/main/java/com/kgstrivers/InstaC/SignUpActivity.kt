package com.kgstrivers.InstaC

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlin.collections.HashMap as HashMap

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        ssignupbutton.setOnClickListener {

            createuser();

        }

        signinbutton.setOnClickListener {
            startActivity(Intent(this,SignInActivity::class.java))
        }
    }

    private fun createuser() {


        val name  = signupname.text.toString()
        val email =  signupemail.text.toString()
        val userid = signupuserid.text.toString()
        val pass = signuppass.text.toString()
        val cpass =  signupconfirmpass.text.toString()


        when{
            TextUtils.isEmpty(name) -> Toast.makeText(this,"Name Field Empty",Toast.LENGTH_LONG).show()
            TextUtils.isEmpty(email) -> Toast.makeText(this,"Email Field Empty",Toast.LENGTH_LONG).show()
            TextUtils.isEmpty(userid) -> Toast.makeText(this,"UserID Field Empty",Toast.LENGTH_LONG).show()
            TextUtils.isEmpty(pass) -> Toast.makeText(this,"Password Field Empty",Toast.LENGTH_LONG).show()
            TextUtils.isEmpty(cpass) -> Toast.makeText(this,"Confirm Password Field Empty",Toast.LENGTH_LONG).show()


            else ->{

                if(cpass != pass)
                {
                    Toast.makeText(this,"Password Doesn't Match",Toast.LENGTH_LONG).show()
                }
                else
                {
                    val progressdialog  = ProgressDialog(this)
                    progressdialog.setTitle("Sign Up")
                    progressdialog.setMessage("Please Wait For a While")
                    progressdialog.setCanceledOnTouchOutside(false)
                    progressdialog.show()
                    val mAuth :FirebaseAuth = FirebaseAuth.getInstance()

                    mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener{
                            task->

                        if(task.isSuccessful){


                            saveuserinfo(name,email,userid,pass,progressdialog)
                        }
                        else
                        {
                            val excepmsg = task.exception.toString()
                            Toast.makeText(this,excepmsg,Toast.LENGTH_LONG).show()
                            progressdialog.dismiss()
                        }
                    }
                }

            }

        }
    }

    private fun saveuserinfo(name: String, email: String, userid: String, pass: String,progressDialog :ProgressDialog) {


        val currentUserID = FirebaseAuth.getInstance().currentUser!!.uid

        val userref: DatabaseReference = FirebaseDatabase.getInstance().reference.child("Users")

        val usermap = HashMap<String,Any>()

        usermap["uid"] = currentUserID
        usermap["name"] = name
        usermap["email"] =  email
        usermap["usercid"] = userid
        usermap["bio"] = "Here All are Devs"
        usermap["pic"] = "https://www.google.co.in/url?sa=i&url=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Fprofile%2520picture%2F&psig=AOvVaw1LZaSt2RM_urcV9kf-QUms&ust=1629043929318000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCICL5dfzsPICFQAAAAAdAAAAABAD"



        userref.child(currentUserID.toString()).setValue(usermap).addOnCompleteListener { task->

            if(task.isSuccessful)
            {
                progressDialog.dismiss()
                Toast.makeText(this,"Account Created",Toast.LENGTH_LONG).show()
                startActivity(Intent(this,MainActivity::class.java))
            }
        }

    }

}