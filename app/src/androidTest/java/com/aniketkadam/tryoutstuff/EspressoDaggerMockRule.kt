package com.aniketkadam.tryoutstuff

import androidx.test.core.app.ApplicationProvider
import com.aniketkadam.tryoutstuff.di.AppComponent
import com.aniketkadam.tryoutstuff.di.DatabaseModule
import com.aniketkadam.tryoutstuff.di.NetworkingModule
import it.cosenonjaviste.daggermock.DaggerMockRule
import it.cosenonjaviste.daggermock.DaggerMockRule.BuilderCustomizer


class EspressoDaggerMockRule :
    DaggerMockRule<AppComponent>(AppComponent::class.java, DatabaseModule(), NetworkingModule()) {

    private val app: TryOutStuffApplication
        get() = ApplicationProvider.getApplicationContext() as TryOutStuffApplication

    init {
        customizeBuilder(BuilderCustomizer<AppComponent.Builder> { builder -> builder.application(app) })
        set { component -> component.inject(app) }
    }
}