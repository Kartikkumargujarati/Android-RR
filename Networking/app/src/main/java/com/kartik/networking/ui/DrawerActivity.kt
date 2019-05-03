/*
 * Created by Kartik Kumar Gujarati on 2/12/19 10:21 PM
 * Copyright (c) 2019 . All rights reserved.
 *
 * Last modified 2/12/19 10:21 PM
 */

package com.kartik.networking.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import com.kartik.networking.R
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.app_bar_drawer.*

class DrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val repositoryListFragment = RepositoryListFragment.newInstance()
    private val gistListFragment = GistListFragment.newInstance()
    private var currentFragment: Fragment? = null
    private val fm = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)
        setSupportActionBar(toolbar)
        fab.hide()
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        if (!isConnectedToInternet()) {
            Toast.makeText(this, "No network connection", Toast.LENGTH_LONG).show()
        } else {
            currentFragment = repositoryListFragment
            fm.beginTransaction().add(R.id.content_frame, gistListFragment, GistListFragment.TAG).hide(gistListFragment).commit()
            fm.beginTransaction().add(R.id.content_frame, repositoryListFragment, RepositoryListFragment.TAG).commit()
        }
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
            R.id.nav_repositoryList -> {
                fab.hide()
                currentFragment?.let { fm.beginTransaction().hide(it).show(repositoryListFragment).commit() }
                currentFragment = repositoryListFragment
            }
            R.id.nav_gistList -> {
                fab.show()
                currentFragment?.let { fm.beginTransaction().hide(it).show(gistListFragment).commit() }
                currentFragment = gistListFragment
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun isConnectedToInternet(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }
}
