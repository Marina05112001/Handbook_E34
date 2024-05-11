package com.example.navigation_drawer_menu

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navigation_drawer_menu.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    lateinit var bindingClass: ActivityMainBinding
//    lateinit var bottom_navigation : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //подключение view binding
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

//        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        setSupportActionBar(bindingClass.toolbar)

        //navView - это Navigation View в activity_main
        bindingClass.navView.setNavigationItemSelectedListener(this)
        bindingClass.navView.setItemIconTintList(null);

        //drawLay - это Drawer layout в activity_main
        //open_drawer и close_drawer прописываются в strings.xml

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

//        if (savedInstanceState == null) {
//            startActivity(Intent(this, MainActivity::class.java))
//
////             Установка выбранного элемента меню в навигационном меню
////            bindingClass.navView.setCheckedItem(R.id.menu_home)
//        }

        bindingClass.cardBody.setOnClickListener{
            val intent = Intent(this, BodyActivity::class.java)
            startActivity(intent)
        }
        bindingClass.cardEngine.setOnClickListener{
            val intent = Intent(this, EngineActivity::class.java)
            startActivity(intent)
        }
        bindingClass.cardBody.setOnClickListener{
            val intent = Intent(this, BodyActivity::class.java)
            startActivity(intent)
        }
        bindingClass.cardDisk.setOnClickListener{
            val intent = Intent(this, DiskActivity::class.java)
            startActivity(intent)
        }
        bindingClass.cardSalon.setOnClickListener{
            val intent = Intent(this, SalonActivity::class.java)
            startActivity(intent)
        }

    }

    //выдвигающееся меню
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_home -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
            R.id.menu_body -> {
                startActivity(Intent(this, BodyActivity::class.java))
            }
            R.id.menu_engine -> {
                startActivity(Intent(this, EngineActivity::class.java))
            }
            R.id.menu_disk -> {
                startActivity(Intent(this, DiskActivity::class.java))
            }
            R.id.menu_like -> {
                startActivity(Intent(this, LikeActivity::class.java))
            }
            R.id.menu_logout -> Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show()

//            R.id.categoty_menu -> bindingClass.drawLay.openDrawer(GravityCompat.START)
//            R.id.home_page -> startActivity(Intent(this, MainActivity::class.java))
//            R.id.like -> startActivity(Intent(this, LikeActivity::class.java))
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
            }

            R.id.nav_top_engine -> {
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


//    //переход на активити?
//    fun onClickBody(view: View){
//        val intent = Intent(this, BodyActivity::class.java)
//        startActivity(intent)
//    }
}

