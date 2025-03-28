package app.simple.positional.dialogs.gps

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.appcompat.widget.SwitchCompat
import app.simple.positional.R
import app.simple.positional.extensions.fragment.ScopedDialogFragment
import app.simple.positional.preferences.GPSPreferences

class GPSMenu : ScopedDialogFragment(), SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var compassRotation: Switch
    private lateinit var volumeKeys: Switch
    private lateinit var showTargets: SwitchCompat
    private var listener: SharedPreferences.OnSharedPreferenceChangeListener? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.gps_menu, container, false)
        compassRotation = view.findViewById(R.id.compass_rotation)
        volumeKeys = view.findViewById(R.id.volume_keys)
        showTargets = view.findViewById(R.id.gps_show_saved_targets)

        compassRotation.isChecked = GPSPreferences.isCompassRotation()
        volumeKeys.isChecked = GPSPreferences.isUsingVolumeKeys()
        showTargets.isChecked = GPSPreferences.isShowSavedLocations()

        compassRotation.setOnCheckedChangeListener { _, isChecked ->
            GPSPreferences.setCompassRotation(isChecked)
        }

        volumeKeys.setOnCheckedChangeListener { _, isChecked ->
            GPSPreferences.setUseVolumeKeys(isChecked)
        }
        
        showTargets.setOnCheckedChangeListener{_, isChecked ->
            GPSPreferences.setShowSavedLocations(isChecked)
        }
        return view
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            GPSPreferences.compassRotation -> {
                compassRotation.isChecked = GPSPreferences.isCompassRotation()
            }

            GPSPreferences.useVolumeKeys -> {
                volumeKeys.isChecked = GPSPreferences.isUsingVolumeKeys()
            }
            GPSPreferences.showSavedLocations ->{
                showTargets.isChecked = GPSPreferences.isShowSavedLocations()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        listener = this
        GPSPreferences.registerPreferenceChangeListener(listener)
    }

    override fun onPause() {
        super.onPause()
        GPSPreferences.unregisterPreferenceChangeListener(listener)
    }

    override fun onDestroy() {
        super.onDestroy()
        listener = null
    }
}