package com.mbariah.wallpapers.ui

import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mbariah.wallpapers.ViewModelFactory
import com.mbariah.wallpapers.databinding.HomeFragmentBinding
import com.mbariah.wallpapers.models.Photo
import com.mbariah.wallpapers.utils.InfiniteScrollListener
import com.mbariah.wallpapers.utils.Logger
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    // You want Dagger to provide an instance of ViewModelFactory with all it's subDependencies
    // from the graph
    @Inject lateinit var viewModelFactory: ViewModelFactory<HomeViewModel>
    private val columns = 2

    lateinit var viewModel: HomeViewModel
    lateinit var binding: HomeFragmentBinding
    lateinit var mBundleRecyclerViewState: Bundle


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //Inject first
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        binding = HomeFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle("Wallpapers X")

        //2 - VERTICAL COLUMNS
        val recyclerlayoutManager = StaggeredGridLayoutManager(columns, StaggeredGridLayoutManager.VERTICAL)
        recyclerlayoutManager.gapStrategy =
            StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS


        binding.recycler.apply {
            layoutManager = recyclerlayoutManager
            addOnScrollListener(InfiniteScrollListener({ viewModel.searchEmptyList(limit = "10") }, recyclerlayoutManager))
        }

        //Listener of recycler view click
        binding.recycler.adapter = ImagesAdapter(ImagesAdapter.ClickListener {
            //notice lambda
            moveToNext(it)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean> {
            binding.isLoading = it
        })

        viewModel.hasNetworkError.observe(viewLifecycleOwner, Observer<Boolean> {
            binding.hasError = it
        })

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        searchInit()

    }

    private fun searchInit() {

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Logger.dt("$query submitted")
                if (query != null) {
                    if (query.isNotEmpty()) {
                        viewModel.searchList("1", "10", query)
                    } else if (query == "") {
                        viewModel.searchEmptyList(limit = "10")
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.equals("")) {
                    this.onQueryTextSubmit("")
                }
                return true
            }
        })

    }

    override fun onResume() {
        super.onResume()
        //Unhide toolbar when coming back
        hideToolbar(false)
        restoreScrollPosition()
    }

    private fun restoreScrollPosition() {

        if (this::mBundleRecyclerViewState.isInitialized) {
            Logger.dt("Restored")

            Handler().postDelayed({
                val mListState =
                    mBundleRecyclerViewState.getParcelable<Parcelable>("KEY_RECYCLER_STATE")
                binding.recycler.layoutManager?.onRestoreInstanceState(mListState)
            }, 100)
        }
    }


    override fun onPause() {
        super.onPause()
        Logger.dt("Paused")
        mBundleRecyclerViewState = Bundle()
        val mListState = binding.recycler.layoutManager?.onSaveInstanceState()
        mBundleRecyclerViewState.putParcelable("KEY_RECYCLER_STATE", mListState)
    }

    private fun moveToNext(photo: Photo) {
        val action = HomeFragmentDirections.nextAction(photo)
        findNavController().navigate(action)
    }


}