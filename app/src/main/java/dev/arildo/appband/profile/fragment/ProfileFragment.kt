package dev.arildo.appband.profile.fragment

import dev.arildo.appband.R
import dev.arildo.appband.core.base.BaseFragment
import dev.arildo.appband.databinding.ProfileFragmentBinding

class ProfileFragment : BaseFragment<ProfileFragmentBinding>(R.layout.profile_fragment) {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    fun scrollToTop() {}


}
