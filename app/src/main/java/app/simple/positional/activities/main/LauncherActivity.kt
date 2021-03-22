package app.simple.positional.activities.main

import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import androidx.appcompat.app.AppCompatDelegate
import app.simple.positional.BuildConfig
import app.simple.positional.R
import app.simple.positional.callbacks.LicenceStatusCallback
import app.simple.positional.preference.ClockPreferences
import app.simple.positional.preference.FragmentPreferences
import app.simple.positional.preference.MainPreferences
import app.simple.positional.preference.MainPreferences.getLicenceStatus
import app.simple.positional.preference.MainPreferences.isDayNightOn
import app.simple.positional.singleton.SharedPreferences
import app.simple.positional.ui.License
import app.simple.positional.ui.SplashScreen
import app.simple.positional.util.ThemeSetter

class LauncherActivity : BaseActivity(), LicenceStatusCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SharedPreferences.init(context = applicationContext)

        if (isDayNightOn()) {
            ThemeSetter.setAppTheme(4)
        } else {
            val value = MainPreferences.getTheme()

            if (value != AppCompatDelegate.getDefaultNightMode()) {
                AppCompatDelegate.setDefaultNightMode(value)
            }
        }

        if (MainPreferences.getLaunchCount() == 0) {
            if (DateFormat.is24HourFormat(this)) {
                ClockPreferences.setDefaultClockTime(false)
            }
        }

        setShortcutScreen()

        setContentView(R.layout.activity_launcher)

        if (getLicenceStatus() || BuildConfig.FLAVOR == "lite" || BuildConfig.DEBUG) {
            onLicenseCheckCompletion()
        } else {
            supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.dialog_in, R.anim.dialog_out)
                    .replace(R.id.launcher_act, License().newInstance(), "license")
                    .commit()
        }
    }

    private fun setShortcutScreen() {
        if (intent.action == null) return
        when (intent.action) {
            "open_clock" -> {
                setScreenValue(0)
            }
            "open_compass" -> {
                setScreenValue(1)
            }
            "open_gps" -> {
                setScreenValue(2)
            }
            "open_level" -> {
                setScreenValue(3)
            }
        }
    }

    private fun setScreenValue(value: Int) {
        FragmentPreferences.setCurrentPage(value)
    }

    override fun onLicenseCheckCompletion() {
        if (MainPreferences.getSkipSplashScreen()) {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        } else {
            supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.dialog_in, R.anim.dialog_out)
                    .replace(R.id.launcher_act, SplashScreen().newInstance(), "launcher")
                    .commit()
        }
    }
}
