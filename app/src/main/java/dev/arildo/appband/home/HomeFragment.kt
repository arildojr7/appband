package dev.arildo.appband.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import dev.arildo.appband.R
import dev.arildo.appband.core.base.BaseFragment
import dev.arildo.appband.databinding.HomeFragmentBinding
import dev.arildo.appband.main.MainViewPagerAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeFragmentBinding>(R.layout.home_fragment) {

    private val viewModel: HomeViewModel by viewModel()

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupBannerViewPager()
    }

    private fun setupBannerViewPager() {
        val urls = mutableListOf<String>().apply {
            add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcT9u3AOHEAi15Yf-AbwSolYsxPIb_kpV2FwkLr5nEC6lRrqzbzD")
            add("https://www.dhresource.com/0x0/f2/albu/g6/M00/AF/99/rBVaR1vSe-KAa_8IAAKfo_7x0xE696.jpg")
            add("https://observatoriodatelevisao.bol.uol.com.br/wp-content/uploads/2017/10/dragon-ball-z-e-exibido-pela-rede-brasi-2-696x392.png")
        }
        val homeAdapter = MainViewPagerAdapter(urls)

        binding.vpBannerTop.adapter = homeAdapter
    }

    fun scrollToTop() {

    }

}
