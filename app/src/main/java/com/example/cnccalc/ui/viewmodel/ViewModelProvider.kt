package com.example.cnccalc.ui.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cnccalc.CNCApplication

@Composable
fun mainViewModel(): MainViewModel {
    val application = LocalContext.current.applicationContext as CNCApplication
    return viewModel(
        factory = MainViewModelFactory(application.machineRepository)
    )
}