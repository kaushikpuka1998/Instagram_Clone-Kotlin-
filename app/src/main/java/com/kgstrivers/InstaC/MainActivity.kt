package com.kgstrivers.InstaC

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.kgstrivers.InstaC.Fragments.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val searchFragment = SearchFragment()
    private val reelsFragment  = ReelsFragment()
    private val notificationFragment = NotificationFragment()
    private val profileFragment = ProfileFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(homeFragment)

        bottomnavigation.setOnNavigationItemSelectedListener{

            when(it.itemId)
            {
                R.id.fraghome -> replaceFragment(homeFragment)
                R.id.fragSearch -> replaceFragment(searchFragment)
                R.id.fragreels -> replaceFragment(reelsFragment)
                R.id.fragheart ->replaceFragment(notificationFragment)
                R.id.fragprofile -> replaceFragment(profileFragment)
            }
            true
        }


    }

    private fun replaceFragment(fragment: Fragment)
    {
        if(fragment!=null)
        {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_layout,fragment)
            transaction.commit()
        }
    }
}