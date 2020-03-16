package com.andymariscal.gymhabit.ui.assistant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andymariscal.gymhabit.ui.assistant.domain.CreateWorkoutUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AssistantViewModel(private val useCase: CreateWorkoutUseCase) : ViewModel() {

    //region LiveData
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _showText = MutableLiveData<String>()
    val showText: LiveData<String>
        get() = _showText
    //endregion

    //region Actions
    fun tryCourutine() {
        _loading.postValue(true)
        viewModelScope.launch {
            _showText.postValue("${useCase.letsSee().state} from dummy")
        }
    }
    //endregion
}