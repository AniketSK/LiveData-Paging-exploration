package com.aniketkadam.tryoutstuff

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.aniketkadam.tryoutstuff.di.FRAGMENT_VM
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.list_fragment.*
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class ListFragment : DaggerFragment() {

    @Inject
    @field:Named(FRAGMENT_VM)
    lateinit var mainVM: MainVM
    lateinit var adapter: PagedAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.list_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PagedAdapter { this.mainVM.selectedItem.value = it }
        recyclerView.adapter = adapter

        mainVM.imageList.observe(this, Observer { adapter.submitList(it) })

        mainVM.networkState.observe(this, Observer {
            Timber.d("HELLO Network state is: ${it} ")
        })

    }

}