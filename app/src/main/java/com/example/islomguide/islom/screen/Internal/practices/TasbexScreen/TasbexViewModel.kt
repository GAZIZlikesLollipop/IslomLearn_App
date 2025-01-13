package com.example.islomguide.islom.screen.Internal.practices.TasbexScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TasbexViewModel : ViewModel() {

    private val _state = MutableStateFlow(TasbexState())
    val state: StateFlow<TasbexState> = _state

    private fun updateState(newState: TasbexState) {
        _state.value = newState
    }
//    private
    fun CheckMax() {
        val fMax  = 33
        val sMax = 99
        val inf = Int.MAX_VALUE
        // Определяем максимальное значение
        val newCountMax = when{
            _state.value.is33 && !_state.value.isInf -> fMax
            !_state.value.is33 && _state.value.isInf -> inf
            else -> sMax
        }

        // Обновляем состояние с новым максимальным значением
        updateState(_state.value.copy(countMax = newCountMax))
    }

    fun ChangeMax() {
        // Меняем состояние флага и проверяем максимальное значение
        val changeBool = when {
            _state.value.is33 && !_state.value.isInf -> {
                _state.value.copy(is33 = false, isInf = true)  // Меняем на is33 = false, isInf = true
            }
            !_state.value.is33 && _state.value.isInf -> {
                _state.value.copy(is33 = false, isInf = false) // Возвращаем в начальное состояние
            }
            else -> {
                _state.value.copy(is33 = true, isInf = false)  // Устанавливаем is33 = true, isInf = false
            }
        }
        updateState(
            changeBool
        )
        CheckMax()
    }

    fun increaseCount() {
        val newTotalCount = _state.value.totalCount + 1
        var newCurrentCount = _state.value.currentCount + 1

        // Если текущий счетчик превышает максимальный, сбрасываем
        if (newCurrentCount >= _state.value.countMax) {
            newCurrentCount = 0
        }

        // Обновляем состояние с новыми значениями
        updateState(
            _state.value.copy(
                totalCount = newTotalCount,
                currentCount = newCurrentCount
            )
        )
    }

    fun RemoveCount() {
        // Сбрасываем счетчики
        updateState(
            _state.value.copy(
                totalCount = 0,
                currentCount = 0
            )
        )
    }
}
