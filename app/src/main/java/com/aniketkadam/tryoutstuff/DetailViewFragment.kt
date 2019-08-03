package com.aniketkadam.tryoutstuff

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.aniketkadam.tryoutstuff.databinding.DetailFragmentBinding
import com.aniketkadam.tryoutstuff.di.FRAGMENT_VM
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import javax.inject.Named

class DetailViewFragment : DaggerFragment() {

    @Inject
    @field:Named(FRAGMENT_VM)
    lateinit var mainVM: MainVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        DataBindingUtil.inflate<DetailFragmentBinding>(inflater, R.layout.detail_fragment, container, false).apply {
            imageData = mainVM.selectedItem.value
        }.root

}