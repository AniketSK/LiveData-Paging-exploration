package com.aniketkadam.tryoutstuff

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.aniketkadam.tryoutstuff.di.FRAGMENT_VM
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.list_fragment.*
import javax.inject.Inject
import javax.inject.Named

class DetailViewFragment : DaggerFragment() {

    @Inject
    @field:Named(FRAGMENT_VM)
    lateinit var mainVM: MainVM
    private lateinit var adapter: PagedAdapter

    val args: DetailViewFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.list_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        LinearSnapHelper().apply {
            attachToRecyclerView(recyclerView)
        }
        adapter = PagedAdapter { mainVM.setItemToNavigate(PositionOnFragment(ActiveFragment.List, it)) }
        recyclerView.adapter = adapter
        recyclerView.scrollToPosition(args.resumeItemPosition)

        mainVM.imageList.observe(this, Observer { adapter.submitList(it) })

        requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                mainVM.setItemToNavigate(
                    PositionOnFragment(
                        ActiveFragment.List,
                        (recyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                    )
                )
            }
        })
    }

}