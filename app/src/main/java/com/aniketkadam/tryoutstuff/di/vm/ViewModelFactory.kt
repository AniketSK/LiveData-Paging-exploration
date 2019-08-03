package com.aniketkadam.tryoutstuff.di.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aniketkadam.tryoutstuff.MainVM
import com.aniketkadam.tryoutstuff.data.Repository
import javax.inject.Inject


class ViewModelFactory @Inject constructor(private val repository: Repository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = with(modelClass) {
        when {
            isAssignableFrom(MainVM::class.java) -> MainVM(repository)
            else -> throw IllegalArgumentException("Unknown viewmodel class ${modelClass.name}")
        }
    } as T
}