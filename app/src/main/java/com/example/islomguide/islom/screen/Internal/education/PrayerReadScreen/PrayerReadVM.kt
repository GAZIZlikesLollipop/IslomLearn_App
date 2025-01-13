package com.example.islomguide.islom.screen.Internal.education.PrayerReadScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PrayerReadVM : ViewModel() {
    private val _state = MutableStateFlow(PrayerReadState())
    val state: StateFlow<PrayerReadState> = _state

    private fun updateState(newState: PrayerReadState) {
        _state.value = newState
    }

    fun ChangeMen(){
        val gender = if(!_state.value.isMan){
            _state.value.copy(isMan = true, isWoman = false)
        }else{
            null
        }
        if (gender != null) {
            updateState(gender)
        }
    }
    fun ChangeWomen(){
        val gender = if(!_state.value.isWoman){
            _state.value.copy(isWoman = true, isMan = false)
        }else{
            null
        }
        if (gender != null) {
            updateState(gender)
        }
    }
    fun ChangeNull(){
        val gender = if(_state.value.isMan && !_state.value.isWoman || !_state.value.isMan && _state.value.isWoman){
            _state.value.copy(isMan = false, isWoman = false)
        }else{
            null
        }
        if (gender != null) {
            updateState(gender)
        }
    }
}
