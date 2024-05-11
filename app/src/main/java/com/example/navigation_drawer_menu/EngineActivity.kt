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
import com.example.navigation_drawer_menu.databinding.ActivityEngineBinding
import com.google.android.material.navigation.NavigationView

class EngineActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    var adapter: MainAdapterTwo? = null
    lateinit var bindingClass: ActivityEngineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //подключение view binding
        bindingClass = ActivityEngineBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

//        setContentView(R.layout.activity_engine)
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
            this, bindingClass.drawLay, bindingClass.toolbar, R.string.open_drawer, R.string.close_drawer
        )
        //изменение цвета гамбургера
        toggle.drawerArrowDrawable.setTint(Color.WHITE)

        //изменение цвета пунктов самого меню Navigation View
        //bindingClass.navView.itemTextColor = ColorStateList.valueOf(Color.WHITE)

        bindingClass.drawLay.addDrawerListener(toggle)
        toggle.syncState()

        var list = ArrayList<ListItemTwo>()

        var rcView = findViewById<RecyclerView>(R.id.rcView)

        list.addAll(fillArrays(resources.getStringArray(R.array.bodycar)))
        list.addAll(fillArrays(resources.getStringArray(R.array.engine)))
        list.addAll(fillArrays(resources.getStringArray(R.array.disk)))

        rcView.hasFixedSize()
        rcView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapterTwo(list, this)
        rcView.adapter = adapter
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (bindingClass.drawLay.isDrawerOpen(GravityCompat.START)) {
            bindingClass.drawLay.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_home -> startActivity(Intent(this, MainActivity::class.java))
            R.id.menu_body -> {
                startActivity(Intent(this, BodyActivity::class.java))
//                Toast.makeText(this, "Нажат кузов", Toast.LENGTH_SHORT).show()
//                adapter?.updateAdapter(fillArrays(resources.getStringArray(R.array.bodycar)))
            }
            R.id.menu_engine -> {
                startActivity(Intent(this, EngineActivity::class.java))
//                Toast.makeText(this, "Нажат двигатель", Toast.LENGTH_SHORT).show()
//                adapter?.updateAdapter(fillArrays(resources.getStringArray(R.array.engine)))
            }
            R.id.menu_disk -> {
                startActivity(Intent(this, DiskActivity::class.java))
//                Toast.makeText(this, "Нажат диск", Toast.LENGTH_SHORT).show()
//                adapter?.updateAdapter(fillArrays(resources.getStringArray(R.array.disk)))
            }
            R.id.menu_like -> startActivity(Intent(this, LikeActivity::class.java))
            R.id.menu_logout ->  Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show()
        }
        bindingClass.drawLay.closeDrawer(GravityCompat.START)
        return true
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

    fun fillArrays(titleArray: Array<String>):List<ListItemTwo>{
        var listItemArray = ArrayList<ListItemTwo>()
        for(n in 0..titleArray.size - 1){
            var listItem = ListItemTwo(titleArray[n])
            listItemArray.add(listItem)
        }
        return listItemArray
    }
}