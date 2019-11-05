package com.arildojr.appband.core.songs

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.arildojr.appband.R
import com.arildojr.appband.core.base.BaseFragment
import com.arildojr.appband.databinding.SongsFragmentBinding

class SongsFragment : BaseFragment<SongsFragmentBinding>(R.layout.songs_fragment) {

    companion object {
        fun newInstance() = SongsFragment()
    }

    private lateinit var viewModel: SongsViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SongsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    fun scrollToTop() {}

}
