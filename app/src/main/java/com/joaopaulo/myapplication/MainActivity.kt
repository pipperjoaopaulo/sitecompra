package com.joaopaulo.myapplication

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationView
import com.joaopaulo.myapplication.databinding.ActivityMainBinding
import kotlin.text.replace
import androidx.activity.addCallback

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        WindowCompat.setDecorFitsSystemWindows(window,false)
        WindowInsetsControllerCompat(window, window.decorView).hide(WindowInsetsCompat.Type.statusBars())

        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(this,binding.drawerLayout, binding.toolbar, R.string.nav_open,R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationDrawer.setNavigationItemSelectedListener(this)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.bottom_home -> openFragment(HomeFragment())
                R.id.botao_cart -> openFragment(CartFragment())
                R.id.botao_profile -> openFragment(ProfileFragment())
                R.id.botao_menu -> openFragment(MenuFragment())
            }
            true
        }
        fragmentManager = supportFragmentManager
        openFragment(HomeFragment())

        binding.fab.setOnClickListener {
            Toast.makeText(this, "Categorias" , Toast.LENGTH_SHORT).show()
            }



            onBackPressedDispatcher.addCallback(this) {
               if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
               }else{
                    finish()
               }
            }




        }


            override fun onNavigationItemSelected(p0: MenuItem): Boolean {
                when(p0.itemId){
                    R.id.nav_pet -> openFragment(PetsFragment())
                    R.id.nav_furniture -> openFragment(MobiliaFragment())
                    R.id.nav_eletronics -> openFragment(EletrodomesticsFragment())
                    R.id.nav_mobile -> Toast.makeText(this, "Telefones" , Toast.LENGTH_SHORT).show()
                    R.id.nav_games ->  Toast.makeText(this, "Jogos" , Toast.LENGTH_SHORT).show()
                    R.id.nav_cars -> Toast.makeText(this, "Carros" , Toast.LENGTH_SHORT).show()
                }
                binding.drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }



            private fun openFragment(fragment: Fragment){
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragment_container,fragment)
                fragmentTransaction.commit()
            }
}



