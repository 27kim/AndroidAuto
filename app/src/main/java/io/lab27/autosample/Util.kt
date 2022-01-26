package io.lab27.autosample

import android.graphics.Bitmap
import androidx.car.app.CarContext
import androidx.car.app.CarToast
import androidx.car.app.hardware.common.CarUnit
import androidx.car.app.model.CarIcon
import androidx.core.graphics.drawable.IconCompat
import java.util.*

private const val kmPerMile = 1.609344

fun showToast(carContext : CarContext, content: String) {
    CarToast.makeText(carContext, content, CarToast.LENGTH_SHORT).show()
}

fun Bitmap.asCarIcon(): CarIcon = CarIcon.Builder(IconCompat.createWithBitmap(this)).build()

fun formatCarUnitSpeed(value: Float?, unit: Int = CarUnit.KILOMETERS_PER_HOUR): String {
    if (value == null) return ""
    return when (unit ?: getDefaultSpeedUnit()) {
        // speed units: base unit is meters per second
        CarUnit.METERS_PER_SEC -> "%.0f m/s".format(value)
        CarUnit.KILOMETERS_PER_HOUR -> "%.0f km/h".format(value * 3.6)
        CarUnit.MILES_PER_HOUR -> "%.0f mph".format(value * 3.6 / kmPerMile)
        else -> ""
    }
}

fun getDefaultSpeedUnit(): Int {
    return when (Locale.getDefault().country) {
        "US", "GB", "MM", "LR" -> CarUnit.MILES_PER_HOUR
        else -> CarUnit.KILOMETERS_PER_HOUR
    }
}

fun formatCarUnitDistance(value: Float?, unit: Int = CarUnit.KILOMETER): String {
    if (value == null) return ""
    return when (unit ?: getDefaultDistanceUnit()) {
        // distance units: base unit is meters
        CarUnit.METER -> "%.0f m".format(value)
        CarUnit.KILOMETER -> "%.1f km".format(value / 1000)
        CarUnit.MILLIMETER -> "%.0f mm".format(value * 1000) // whoever uses that...
        CarUnit.MILE -> "%.1f mi".format(value / 1000 / kmPerMile)
        else -> ""
    }
}

fun getDefaultDistanceUnit(): Int {
    return when (Locale.getDefault().country) {
        "US", "GB", "MM", "LR" -> CarUnit.MILE
        else -> CarUnit.KILOMETER
    }
}