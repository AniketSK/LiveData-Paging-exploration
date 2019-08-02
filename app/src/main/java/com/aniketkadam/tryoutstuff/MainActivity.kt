package com.aniketkadam.tryoutstuff

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.aniketkadam.tryoutstuff.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var mainVM: MainVM
    @Inject
    lateinit var pagedAdapter: PagedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            setVariable(BR.state, mainVM)
            this.recyclerView.adapter = pagedAdapter
        }

        initObservers()
    }

    private fun initObservers() {
        mainVM.imageList.observe(this, Observer { pagedAdapter.submitList(it) })

        mainVM.networkState.observe(this, Observer {
            Timber.d("HELLO Network state is: ${it} ")
        })
    }
}
