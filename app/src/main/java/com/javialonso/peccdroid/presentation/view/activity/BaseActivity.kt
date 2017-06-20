package com.javialonso.peccdroid.presentation.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.javialonso.peccdroid.presentation.AndroidApplication
import com.javialonso.peccdroid.presentation.internal.di.components.ApplicationComponent
import com.javialonso.peccdroid.presentation.internal.di.module.ActivityModule


abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.applicationComponent.inject(this)
    }

    protected fun addFragment(containerViewId: Int, fragment: Fragment) {
        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.add(containerViewId, fragment)
        fragmentTransaction.commit()
    }

    protected fun toFragment(containerViewId: Int, fragment: Fragment) {
        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(containerViewId, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    protected fun toActivity(activity: Class<*>) {
        val intent = Intent(this, FeedActivity::class.java)
        startActivity(intent)
    }

    protected val applicationComponent: ApplicationComponent
        get() = (application as AndroidApplication).applicationComponent!!

    protected val activityModule: ActivityModule
        get() = ActivityModule(this)
}