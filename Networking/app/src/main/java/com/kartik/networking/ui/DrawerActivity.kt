/*
 * Created by Kartik Kumar Gujarati on 2/12/19 10:21 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 2/12/19 10:21 PM
 */

package com.kartik.networking.ui

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.kartik.networking.R
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.app_bar_drawer.*

class DrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val ghFragment = GitHubRepoListFragment.newInstance()
    private val mockListFragment = MockDataListFragment.newInstance()
    private var currentFragment: Fragment? = null
    private val fm = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        currentFragment = ghFragment
        fm.beginTransaction().add(R.id.content_frame, mockListFragment, MockDataListFragment.TAG).hide(mockListFragment).commit()
        fm.beginTransaction().add(R.id.content_frame, ghFragment, GitHubRepoListFragment.TAG).commit()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_ghList -> {
                currentFragment?.let { fm.beginTransaction().hide(it).show(ghFragment).commit() }
                currentFragment = ghFragment
            }
            R.id.nav_mockList -> {
                currentFragment?.let { fm.beginTransaction().hide(it).show(mockListFragment).commit() }
                currentFragment = mockListFragment
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
