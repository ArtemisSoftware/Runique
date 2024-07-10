package com.artemissoftware.runique

import android.app.Application
import com.artemissoftware.auth.data.di.authDataModule
import com.artemissoftware.auth.presentation.di.authViewModelModule
import com.artemissoftware.core.di.coreDataModule
import com.artemissoftware.runique.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class RuniqueApp: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@RuniqueApp)
            modules(
                authDataModule,
                authViewModelModule,
                appModule,
                coreDataModule
            )
        }
    }
}