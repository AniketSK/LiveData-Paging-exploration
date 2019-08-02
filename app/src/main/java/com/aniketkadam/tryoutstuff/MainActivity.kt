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
    lateinit var adapter: PagedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = PagedAdapter { mainVM.selectedItem.value = it }

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            setVariable(BR.state, mainVM)
            recyclerView.adapter = adapter
        }

        initObservers()
    }

    private fun initObservers() {
        mainVM.imageList.observe(this, Observer { adapter.submitList(it) })

        mainVM.networkState.observe(this, Observer {
            Timber.d("HELLO Network state is: ${it} ")
        })
    }
}
