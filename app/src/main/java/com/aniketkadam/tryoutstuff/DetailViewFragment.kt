package com.aniketkadam.tryoutstuff

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.aniketkadam.tryoutstuff.databinding.DetailFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DetailViewFragment : DaggerFragment() {
    @Inject
    lateinit var mainVM: MainVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        DataBindingUtil.inflate<DetailFragmentBinding>(inflater, R.layout.detail_fragment, container, false)
            .apply {
                setVariable(BR.imageData, mainVM.selectedItem)
            }.root
}