package com.example.navigation_drawer_menu

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navigation_drawer_menu.databinding.ActivityDiskBinding
import com.google.android.material.navigation.NavigationView

class SalonActivity : AppCompatActivity() {

    lateinit var bindingClass: ActivityDiskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //подключение view binding
        bindingClass = ActivityDiskBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

//        setContentView(R.layout.activity_salon)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        setSupportActionBar(bindingClass.toolbar)
    }

    //создание верхнего правого меню
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        menuInflater.inflate(R.menu.nav_top_right, menu)
        return true
    }

    //верхнее правое меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_top_body -> {
                startActivity(Intent(this, BodyActivity::class.java))
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.fragment_container, AboutFragment.newInstance("", ""))
//                    .commit()
            }
            R.id.nav_top_engine -> {
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.fragment_container, SettingsFragment.newInstance("", ""))
//                    .commit()
                startActivity(Intent(this, EngineActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}