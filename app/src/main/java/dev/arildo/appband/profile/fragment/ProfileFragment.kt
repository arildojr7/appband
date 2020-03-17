package dev.arildo.appband.profile.fragment

import android.content.Intent
import android.os.Bundle
import dev.arildo.appband.R
import dev.arildo.appband.core.base.BaseFragment
import dev.arildo.appband.databinding.ProfileFragmentBinding
import dev.arildo.appband.login.activity.LoginActivity
import dev.arildo.appband.profile.viewmodel.ProfileViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ProfileFragment : BaseFragment<ProfileFragmentBinding>(R.layout.profile_fragment) {

    private val viewModel: ProfileViewModel by inject()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.btnLogout.setOnClickListener {
            launch {
                viewModel.logout()
                startActivity(Intent(context, LoginActivity::class.java))
                activity?.finish()
            }
        }
    }

    companion object {
        fun newInstance() = ProfileFragment()
    }

    fun scrollToTop() {}


}
