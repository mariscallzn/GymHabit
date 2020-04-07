package com.andymariscal.shared.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

class GymhabitViewModelFactory @Inject constructor(
    private val creators: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    //region ViewModelProvider.Factory
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val found = creators.entries.find { modelClass.isAssignableFrom(it.key) }
        val creator = found?.value
            ?: throw IllegalArgumentException("unknown model class $modelClass")
        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            Timber.e(e)
            throw RuntimeException(e)
        }
    }
    //endregion
}