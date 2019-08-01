package com.aniketkadam.tryoutstuff

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.github.tmurakami.dexopener.DexOpener

/**
 * Used when final classes are required to be mocked in androidTest.
 * mockito can't mock them inline and the dex opener needs to be used for it.
 * Basically whenever you want to use the EspressoDaggerMockRule, this is the runner that should be used.
 */
class DexOpeningInstrumentationRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        DexOpener.install(this)
        return super.newApplication(cl, className, context)
    }
}