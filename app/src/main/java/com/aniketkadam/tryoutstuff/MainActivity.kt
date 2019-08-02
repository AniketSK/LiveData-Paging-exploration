package com.aniketkadam.tryoutstuff

import android.os.Bundle
import androidx.lifecycle.Observer
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var mainVM: MainVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainVM.navigate.observe(this, Observer {
            // Start the fragment
        })

    }

}
