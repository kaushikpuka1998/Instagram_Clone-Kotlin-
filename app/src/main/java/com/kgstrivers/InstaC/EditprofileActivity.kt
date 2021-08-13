package com.kgstrivers.InstaC

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kgstrivers.InstaC.Fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_editprofile.*

class EditprofileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editprofile)


        save.setOnClickListener {


            Toast.makeText(this,"Saved",Toast.LENGTH_LONG).show()
            onBackPressed()
        }

        cancel.setOnClickListener {

            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}