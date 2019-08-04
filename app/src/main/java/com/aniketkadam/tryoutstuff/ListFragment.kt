package com.aniketkadam.tryoutstuff

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.aniketkadam.tryoutstuff.di.FRAGMENT_VM
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.list_fragment.*
import javax.inject.Inject
import javax.inject.Named

class ListFragment : DaggerFragment() {

    @Inject
    @field:Named(FRAGMENT_VM)
    lateinit var mainVM: MainVM
    private lateinit var adapter: PagedAdapter

    val args: ListFragmentArgs by navArgs()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.list_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PagedAdapter { mainVM.setItemToNavigate(PositionOnFragment(ActiveFragment.Selection, it)) }
        recyclerView.adapter = adapter

        mainVM.imageList.observe(this, Observer { adapter.submitList(it) })

        recyclerView.scrollToPosition(args.resumeItemPosition)
    }

}