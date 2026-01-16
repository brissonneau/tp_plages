package biz.ei6.eluvplages

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import biz.ei6.eluvplages.di.appModule
import biz.ei6.eluvplages.framework.InternalBroadcasts
import biz.ei6.eluvplages.framework.Notif
import biz.ei6.eluvplages.framework.openAppPendingIntent
import org.koin.android.ext.koin.androidContext

class MyApp : Application() {

    val TAG = "Application Plages"
    val notifBroadcast = object : BroadcastReceiver() {
        override fun onReceive( cxt: Context, intent : Intent) {

            val hasUpdate = true // placeholder

            Log.d(TAG, "Broadcast received: $hasUpdate")

            if (hasUpdate) {
                Notif.ensureChannel(applicationContext)
                val pi = openAppPendingIntent(applicationContext)

                Notif.show(
                    applicationContext,
                    title = "Nouvelle info disponible",
                    text = "Cliquer pour ouvrir l’application",
                    pendingIntent = pi
                )
            }
        }

    }

    override fun onCreate() {
        super.onCreate()

        org.koin.core.context.startKoin {
            androidContext(this@MyApp)
            modules(appModule)
        }

        registerReceiver(notifBroadcast,
            IntentFilter(InternalBroadcasts.ACTION_SERVER_HAS_UPDATE),
            RECEIVER_NOT_EXPORTED)

        Log.d(TAG, "Application started")
    }

    override fun onTerminate() {

        unregisterReceiver(notifBroadcast)

        Log.d(TAG, "Application stopped")
        super.onTerminate()
    }
}
