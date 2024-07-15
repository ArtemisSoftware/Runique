package com.artemissoftware.runique

import android.app.Application
import com.artemissoftware.auth.data.di.authDataModule
import com.artemissoftware.auth.presentation.di.authViewModelModule
import com.artemissoftware.core.data.di.coreDataModule
import com.artemissoftware.run.di.locationModule
import com.artemissoftware.run.presentation.di.runPresentationModule
import com.artemissoftware.run.presentation.di.runViewModelModule
import com.artemissoftware.runique.di.appModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class RuniqueApp: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

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
                coreDataModule,
                runViewModelModule,
                runPresentationModule,
                locationModule
            )
        }
    }
}