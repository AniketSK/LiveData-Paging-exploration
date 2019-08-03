package com.aniketkadam.tryoutstuff.di.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aniketkadam.tryoutstuff.MainVM
import com.aniketkadam.tryoutstuff.data.Repository
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainVM::class.java)) {
            return MainVM(repository) as T
        } else {
            throw IllegalArgumentException("No viewmodel found with class ${modelClass}")
        }
    }

}