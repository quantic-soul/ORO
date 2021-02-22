package app.simple.positional.dialogs.clock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import app.simple.positional.R
import app.simple.positional.preference.ClockPreferences.getMovementType
import app.simple.positional.preference.ClockPreferences.setMovementType
import app.simple.positional.ui.Clock
import app.simple.positional.views.CustomBottomSheetDialogFragment
import java.lang.ref.WeakReference

class ClockMotionType(private val clock: WeakReference<Clock>) : CustomBottomSheetDialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
        retainInstance = true
    }

    private lateinit var smooth: RadioButton
    private lateinit var tick: RadioButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_clock_motion_type, container, false)

        smooth = view.findViewById(R.id.motion_smooth)
        tick = view.findViewById(R.id.motion_tick)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButton(getMovementType())

        smooth.setOnClickListener {
            setButton(true)
            clock.get()?.setMotionDelay(true)
        }

        tick.setOnClickListener {
            setButton(false)
            clock.get()?.setMotionDelay(false)
        }
    }

    private fun setButton(value: Boolean) {
        smooth.isChecked = value
        tick.isChecked = !value
        setMovementType(value)
    }
}