package dev.arildo.appband.splash.activity

import android.content.Intent
import android.os.Bundle
import dev.arildo.appband.R
import dev.arildo.appband.core.base.BaseActivity
import dev.arildo.appband.databinding.ActivitySplashBinding
import dev.arildo.appband.login.activity.LoginActivity
import dev.arildo.appband.main.activity.MainActivity
import dev.arildo.appband.splash.viewmodel.SplashViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    private val viewModel: SplashViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launch {
            if (viewModel.isUserLogged()) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            } else {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            }
            finish()

        }
    }

}
