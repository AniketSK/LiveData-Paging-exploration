package com.aniketkadam.tryoutstuff

import android.app.Application
import android.content.Context
import io.appflate.restmock.android.RESTMockTestRunner

class MockTestRunner : RESTMockTestRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, MockApplication::class.java.name, context)
    }
}