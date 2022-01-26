package io.lab27.autosample

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.util.Log
import androidx.car.app.CarAppService
import androidx.car.app.Screen
import androidx.car.app.Session
import androidx.car.app.validation.HostValidator
import io.lab27.autosample.ui.MainTemplateScreen

class AutoSampleService : CarAppService() {
    override fun createHostValidator(): HostValidator {
        Log.i("AutoSampleService", "createHostValidator")

        return if (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0) {
            Log.i("AutoSampleService", "createHostValidator - true")

            HostValidator.ALLOW_ALL_HOSTS_VALIDATOR
        } else {
            Log.i("AutoSampleService", "createHostValidator - false")

            HostValidator.Builder(applicationContext)
                .addAllowedHosts(R.array.hosts_allowlist_sample)
                .build()
        }
    }

    override fun onCreateSession(): Session = AutoSampleSession()
}

class AutoSampleSession : Session() {
    override fun onCreateScreen(intent: Intent): Screen {
        Log.i("AutoSampleService", "onCreateScreen")
        return MainTemplateScreen(carContext)
    }
}
