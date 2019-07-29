package com.aniketkadam.tryoutstuff

import androidx.lifecycle.ViewModel
import com.aniketkadam.tryoutstuff.data.Repository
import javax.inject.Inject

class MainVM @Inject constructor(val respository: Repository) : ViewModel() {

}