package com.example.cnccalc.ui.common

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : ComponentActivity() {

    protected lateinit var binding: VB

    override fun setContentView(layoutRes: Int) {
        super.setContentView(layoutRes)
    }

    protected inline fun <reified T : ViewBinding> bind(
        crossinline bindingFactory: (ComponentActivity) -> T
    ) {
        binding = bindingFactory(this)
        setContentView(binding.root)
    }
}