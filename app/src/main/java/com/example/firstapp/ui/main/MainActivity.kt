package com.example.firstapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.R
import com.example.firstapp.ui.favorites.FavoriteFragment
import com.example.firstapp.ui.profile.ProfileFragment
import com.example.firstapp.ui.publication.PublicationFragment
import kotlinx.android.synthetic.main.activity_main.*

//Если мы открываем активити, то используем - Intent (Данные передаем через Intent)
//Если мы открываем фрагмент из активити, то используем - supportFragmentManager (Данные передаем через Bundle)
//Если мы открываем фрагмент из фрагмента, то используем - childFragmentManager (Данные передаем через Bundle)

class MainActivity : AppCompatActivity() {

    lateinit var adapter: MainViewPagerAdapter
    private lateinit var repository: MainRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewPager()
        setupBottomNavigationView()
    }

    private fun setupViewPager() {
        adapter = MainViewPagerAdapter(this)
        adapter.addFragment(PublicationFragment())
        adapter.addFragment(FavoriteFragment())
        adapter.addFragment(ProfileFragment())
        view_pager.adapter = adapter
        view_pager.offscreenPageLimit = 3
        view_pager.isEnabled = false
        view_pager.isUserInputEnabled = false
    }

        private fun setupBottomNavigationView() {
            bottom_navigation.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.image -> changeCurrentFragment(0)
                    R.id.favorite -> changeCurrentFragment(1)
                    R.id.profile -> changeCurrentFragment(2)
                }
                true
            }
        }

        private fun changeCurrentFragment(position: Int) {
            view_pager.setCurrentItem(position, false)
    }
}








