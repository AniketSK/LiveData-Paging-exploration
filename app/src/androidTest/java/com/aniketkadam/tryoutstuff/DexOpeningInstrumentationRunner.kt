package com.aniketkadam.tryoutstuff

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.github.tmurakami.dexopener.DexOpener

class DexOpeningInstrumentationRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        DexOpener.install(this)
        return super.newApplication(cl, className, context)
    }
}