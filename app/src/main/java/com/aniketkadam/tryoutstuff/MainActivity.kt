package com.aniketkadam.tryoutstuff

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.aniketkadam.tryoutstuff.di.ACTIVITY_VM
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import javax.inject.Named

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    @field:Named(ACTIVITY_VM)
    lateinit var mainVM: MainVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainVM.navigate.observe(this, Observer { navigate(it) })
    }

    private fun navigate(it: ActiveFragment?) {
        when (it) {
            is ActiveFragment.Selection -> {
                navTo(ListFragmentDirections.actionListFragmentToDetailViewFragment())
            }
        }
    }

    fun navTo(d: NavDirections) {
        findNavController(R.id.nav_host_fragment).navigate(d)
    }
}
