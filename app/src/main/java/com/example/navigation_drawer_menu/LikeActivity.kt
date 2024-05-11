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
import com.example.navigation_drawer_menu.databinding.ActivityLikeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class LikeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    lateinit var bindingClass: ActivityLikeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //подключение view binding
        bindingClass = ActivityLikeBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

//        setContentView(R.layout.activity_like)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        setSupportActionBar(bindingClass.toolbar)

        //navView - это Navigation View в activity_main
        bindingClass.navView.setNavigationItemSelectedListener(this)
        bindingClass.navView.setItemIconTintList(null);

        val toggle = ActionBarDrawerToggle(
            this,
            bindingClass.drawLay,
            bindingClass.toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        //изменение цвета гамбургера
//        toggle.drawerArrowDrawable?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
        toggle.drawerArrowDrawable.setTint(Color.WHITE)

        //изменение цвета пунктов самого меню Navigation View
        //bindingClass.navView.itemTextColor = ColorStateList.valueOf(Color.WHITE)

        bindingClass.drawLay.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_home -> startActivity(Intent(this, MainActivity::class.java))
            R.id.menu_body -> startActivity(Intent(this, BodyActivity::class.java))
            R.id.menu_engine -> startActivity(Intent(this, EngineActivity::class.java))
            R.id.menu_disk -> startActivity(Intent(this, DiskActivity::class.java))
            R.id.menu_like -> startActivity(Intent(this, LikeActivity::class.java))
            R.id.menu_logout -> Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show()

            R.id.categoty_menu -> bindingClass.drawLay.openDrawer(GravityCompat.START)
            R.id.home_page -> startActivity(Intent(this, MainActivity::class.java))
            R.id.like -> startActivity(Intent(this, LikeActivity::class.java))
        }
        bindingClass.drawLay.closeDrawer(GravityCompat.START)
        return true
    }
    override fun onBackPressed() {
        super.onBackPressed()
        if (bindingClass.drawLay.isDrawerOpen(GravityCompat.START)) {
            bindingClass.drawLay.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
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