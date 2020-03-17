package dev.arildo.appband.login.activity

import android.content.Intent
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import dev.arildo.appband.R
import dev.arildo.appband.core.base.BaseActivity
import dev.arildo.appband.databinding.ActivityLoginBinding
import dev.arildo.appband.login.viewmodel.LoginViewModel
import dev.arildo.appband.main.activity.MainActivity
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val viewModel: LoginViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val email = binding.tieEmail.text.toString()
            val password = binding.tiePassword.text.toString()

            launch {
                viewModel.login(email, password)
            }
//
        }
    }

    override fun subscribeUi() {
        viewModel.viewState.observe(this, Observer { viewState ->
            when {
                viewState.isLogging -> {
                    TransitionManager.beginDelayedTransition(binding.clLoginContainer)
                    binding.pbLoaderLogin.visibility = View.VISIBLE
                    TransitionManager.beginDelayedTransition(binding.clLoginContainer)
                    binding.btnLogin.visibility = View.INVISIBLE

                }
                viewState.hasNetworkError -> {
                    Snackbar.make(binding.root, getString(R.string.login_failure), Snackbar.LENGTH_LONG).show()
                }
                viewState.isCredentialsInvalid -> {
                    Snackbar.make(binding.root, getString(R.string.incorrect_credentials), Snackbar.LENGTH_LONG).show()
                }
                viewState.successLogin -> {
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                }
                !viewState.isLogging -> {
                    TransitionManager.beginDelayedTransition(binding.clLoginContainer)
                    binding.pbLoaderLogin.visibility = View.GONE
                    TransitionManager.beginDelayedTransition(binding.clLoginContainer)
                    binding.btnLogin.visibility = View.VISIBLE

                }
            }
        })
    }
}
