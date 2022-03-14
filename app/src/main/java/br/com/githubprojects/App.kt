package br.com.githubprojects

import android.app.Application
import br.com.githubprojects.di.mainModule
import br.com.githubprojects.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                listOf<Module>(
                    repositoryModule,
                    mainModule
                )
            )
        }
    }
}