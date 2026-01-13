package biz.ei6.eluvplages

import android.app.Application
import biz.ei6.eluvplages.di.appModule
import org.koin.android.ext.koin.androidContext

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        org.koin.core.context.startKoin {
            androidContext(this@MyApp)
            modules(appModule)
        }
    }
}
