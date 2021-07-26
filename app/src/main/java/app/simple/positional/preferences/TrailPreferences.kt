package app.simple.positional.preferences

import app.simple.positional.singleton.SharedPreferences.getSharedPreferences
import org.jetbrains.annotations.NotNull

object TrailPreferences {

    private const val mapZoom = "trail_map_zoom_value"
    private const val mapTilt = "trail_map_tilt_value"
    private const val mapBearing = "trail_map_bearing"
    const val mapAutoCenter = "trail_maps_auto_center"
    const val lastSelectedTrail = "last_selected_trail"
    const val wrapped = "are_polylines_wrapped"
    const val geodesic = "is_trail_geodesic"
    const val trailLabelMode = "trail_map_label_mode"
    const val trailSatellite = "trail_map_satellite_mode"
    const val trailHighContrastMap = "trail_map_high_contrast_map"
    const val trailShowBuilding = "trail_show_buildings_on_map"
    const val toolsMenuGravity = "trail_tools_view_gravity"
    const val compass = "trail_map_compass_or_bearing"

    //--------------------------------------------------------------------------------------------------//

    fun setGeodesic(@NotNull value: Boolean) {
        getSharedPreferences().edit().putBoolean(geodesic, value).apply()
    }

    fun isTrailGeodesic(): Boolean {
        return getSharedPreferences().getBoolean(geodesic, true)
    }

    //--------------------------------------------------------------------------------------------------//

    fun isLabelOn(): Boolean {
        return getSharedPreferences().getBoolean(trailLabelMode, true)
    }

    fun setLabelMode(@NotNull value: Boolean) {
        getSharedPreferences().edit().putBoolean(trailLabelMode, value).apply()
    }

    //--------------------------------------------------------------------------------------------------//

    fun isSatelliteOn(): Boolean {
        return getSharedPreferences().getBoolean(trailSatellite, false)
    }

    fun setSatelliteMode(@NotNull value: Boolean) {
        getSharedPreferences().edit().putBoolean(trailSatellite, value).apply()
    }

    //--------------------------------------------------------------------------------------------------//

    fun setHighContrastMap(boolean: Boolean) {
        getSharedPreferences().edit().putBoolean(trailHighContrastMap, boolean).apply()
    }

    fun getHighContrastMap(): Boolean {
        return getSharedPreferences().getBoolean(trailHighContrastMap, false)
    }

    //--------------------------------------------------------------------------------------------------//

    fun setShowBuildingsOnMap(boolean: Boolean) {
        getSharedPreferences().edit().putBoolean(trailShowBuilding, boolean).apply()
    }

    fun getShowBuildingsOnMap(): Boolean {
        return getSharedPreferences().getBoolean(trailShowBuilding, false)
    }

    //--------------------------------------------------------------------------------------------------//

    fun setMapZoom(@NotNull value: Float) {
        getSharedPreferences().edit().putFloat(mapZoom, value).apply()
    }

    fun getMapZoom(): Float {
        return getSharedPreferences().getFloat(mapZoom, 15F)
    }

    //--------------------------------------------------------------------------------------------------//

    fun setMapTilt(@NotNull value: Float) {
        getSharedPreferences().edit().putFloat(mapTilt, value).apply()
    }

    fun getMapTilt(): Float {
        return getSharedPreferences().getFloat(mapTilt, 0F)
    }

    //--------------------------------------------------------------------------------------------------//

    fun setMapBearing(@NotNull value: Float) {
        getSharedPreferences().edit().putFloat(mapBearing, value).apply()
    }

    fun getMapBearing(): Float {
        return getSharedPreferences().getFloat(mapBearing, 0F)
    }

    //--------------------------------------------------------------------------------------------------//

    fun setWrapStatus(@NotNull value: Boolean) {
        getSharedPreferences().edit().putBoolean(wrapped, value).apply()
    }

    fun arePolylinesWrapped(): Boolean {
        return getSharedPreferences().getBoolean(wrapped, true)
    }

    //--------------------------------------------------------------------------------------------------//

    fun setLastTrailName(value: String) {
        getSharedPreferences().edit().putString(lastSelectedTrail, value).apply()
    }

    fun getLastUsedTrail(): String {
        return getSharedPreferences().getString(lastSelectedTrail, "")!!
    }

    //--------------------------------------------------------------------------------------------------//

    fun setToolsGravityToLeft(value: Boolean) {
        getSharedPreferences().edit().putBoolean(toolsMenuGravity, value).apply()
    }

    fun isToolsGravityToLeft(): Boolean {
        return getSharedPreferences().getBoolean(toolsMenuGravity, false)
    }

    //--------------------------------------------------------------------------------------------------//

    fun setMapAutoCenter(boolean: Boolean) {
        getSharedPreferences().edit().putBoolean(mapAutoCenter, boolean).apply()
    }

    fun getMapAutoCenter(): Boolean {
        return getSharedPreferences().getBoolean(mapAutoCenter, true)
    }

    //--------------------------------------------------------------------------------------------------//

    fun setCompassRotation(value: Boolean) {
        getSharedPreferences().edit().putBoolean(compass, value).apply()
    }

    fun isCompassRotation(): Boolean {
        return getSharedPreferences().getBoolean(compass, false)
    }
}