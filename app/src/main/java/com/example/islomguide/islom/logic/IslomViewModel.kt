package com.example.islomguide.islom.logic

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.islomguide.core.main.Routes
import com.example.islomguide.core.main.Routes.FeatureRoutes
import com.example.islomguide.islom.screen.Internal.practices.TasbexScreen.TasbexState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.TimeZone

class IslomViewModel : ViewModel() {


    private val _state = MutableStateFlow(IslomState())
    val state : StateFlow<IslomState> = _state

    private fun updateState(newState: IslomState) {
        _state.value = newState
    }
    fun checkContent() {
       val nowBool = _state.value.copy(content = !_state.value.content)
        updateState(nowBool)
    }

    fun CheckRoute(
        navController: NavController
    ): Boolean {
        val currentRoute = navController.currentBackStackEntry?.destination?.route

         val newRoute = when(currentRoute) {
            FeatureRoutes.PR_Fajr.name -> true
            FeatureRoutes.PR_Fajr.name -> true
            FeatureRoutes.PR_Fajr.name -> true
            FeatureRoutes.PR_Fajr.name -> true
            FeatureRoutes.PR_Fajr.name -> true
            else -> {false}
        }
        updateState(_state.value.copy(isCheck = newRoute))
        return  newRoute
    }

}
