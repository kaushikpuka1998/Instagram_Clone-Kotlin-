package com.kgstrivers.InstaC

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.kgstrivers.InstaC.Fragments.DashboardFragment
import com.kgstrivers.InstaC.Fragments.HomeFragment
import com.kgstrivers.InstaC.Fragments.NotificationFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val dashboardFragment = DashboardFragment()
    private val notificationFragment = NotificationFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(homeFragment)

        bottomnavigation.setOnNavigationItemSelectedListener{

            when(it.itemId)
            {
                R.id.fraghome -> replaceFragment(homeFragment)
                R.id.fragDashboard -> replaceFragment(dashboardFragment)
                R.id.fragnotification ->replaceFragment(notificationFragment)
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