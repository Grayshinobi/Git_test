package com.example.chataddict

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.chataddict.databinding.ActivityMainBinding
import com.example.chataddict.ui.contacts.ContactsFragment
import com.example.chataddict.ui.conversation.ConversationFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private val listOfClasses = listOf(ConversationFragment(),ContactsFragment())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        val navView: BottomNavigationView = binding.bottomNav

        supportActionBar?.hide()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
         navController = navHostFragment.navController



        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener{ _, nd: NavDestination, _->
           when(nd.id){
               R.id.LogIn -> navView.visibility = View.GONE
               R.id.register -> navView.visibility = View.GONE
           }

            navView.setOnItemSelectedListener {fragment->
                when(fragment.itemId){
                    R.id.contacts -> {swapFragment(listOfClasses[0])

                    true
                    }
                    R.id.conversation -> {swapFragment(listOfClasses[1])
                        true
                    }
                }
                true
            }



}
    }

    private fun swapFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.container,fragment)
        }
    }
}