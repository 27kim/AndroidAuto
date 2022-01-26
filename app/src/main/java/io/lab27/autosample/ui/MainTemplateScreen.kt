package io.lab27.autosample.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.car.app.CarContext
import androidx.car.app.OnRequestPermissionsListener
import androidx.car.app.Screen
import androidx.car.app.hardware.common.CarValue
import androidx.car.app.hardware.info.EnergyLevel
import androidx.car.app.hardware.info.Model
import androidx.car.app.hardware.info.Speed
import androidx.car.app.model.*
import androidx.core.graphics.drawable.IconCompat
import io.lab27.autosample.*
import java.lang.Float
import java.util.*
import kotlin.coroutines.coroutineContext
import kotlin.math.roundToInt


class MainTemplateScreen(carContext: CarContext) : Screen(carContext) {



    @RequiresApi(Build.VERSION_CODES.N)
    override fun onGetTemplate(): Template {

//        val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION
//        val permission: Int =
//            carContext.checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, 0, 0)
//
//        val listener = OnRequestPermissionsListener { grantedPermissions, rejectedPermissions ->
//            Log.i("result? granted" , "${grantedPermissions.toString()}")
//            Log.i("result? rejected" , "${rejectedPermissions.toString()}")
//        }
////        if (permission != PackageManager.PERMISSION_GRANTED) {
//            carContext.requestPermissions(listOf(locationPermission), listener)
////        }


        val requestLocationUpdates = {
//            val locationManager = carContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, )
        }

//        screenManager.push(RequestPermissionScreen(carContext, requestLocationUpdates))

        //fixme needs permission
        //val hardwareMan = carContext.getCarService(CarContext.HARDWARE_SERVICE) as CarHardwareManager
        var energyLevel = EnergyLevel.Builder().apply {
            setBatteryPercent(CarValue(90f,
                Calendar.getInstance().timeInMillis,
                CarValue.STATUS_SUCCESS))
            setRangeRemainingMeters(CarValue(100000f,
                Calendar.getInstance().timeInMillis,
                CarValue.STATUS_SUCCESS))
        }.build()

        var speed = Speed.Builder().apply {
            setRawSpeedMetersPerSecond(
                CarValue(
                    40f,
                    Calendar.getInstance().timeInMillis,
                    CarValue.STATUS_UNKNOWN)
            )
        }.build()

        val maxSpeed = 1f // m/s, speed gauge will show max if speed is higher
//        val maxSpeed = 160f / 3.6f // m/s, speed gauge will show max if speed is higher

        var gauge = Gauge(
            size = (carContext.resources.displayMetrics.density * 128).roundToInt(),
            ctx = carContext
        )

        return GridTemplate.Builder().apply {
            setTitle("Auto Main")
            setHeaderAction(Action.APP_ICON)
            setSingleList(
                ItemList.Builder().apply {
                    addItem(
                        GridItem.Builder().apply {
                            setTitle("CHARGING")
                            setText(
                                "\uD83D\uDD0C %.0f %% ⛽ %.0f %%".format(
                                    80f,
                                    70f
                                )
                            )
                            setImage(
                                gauge.draw(
                                    80f,
                                    70f
                                ).asCarIcon()
                            ).setOnClickListener {
                                showToast(carContext, "PaneTemplate")
                                screenManager.push(PaneTemplateScreen(carContext))
                            }
                        }.build()
                    )
                    addItem(
                        GridItem.Builder().apply {
                            setTitle("FUEL")
                            if (speed == null) {
                                setLoading(true)
                            } else {
                                val rawSpeed = speed.rawSpeedMetersPerSecond.value
                                val displaySpeed = speed.displaySpeedMetersPerSecond.value
                                if (rawSpeed != null) {
                                    setText(
                                        formatCarUnitSpeed(
                                            rawSpeed,
                                        )
                                    )
                                    setImage(
                                        gauge.draw(Float.min(rawSpeed / maxSpeed * 100, 100f))
                                            .asCarIcon()
                                    )
                                    setOnClickListener {
                                        showToast(carContext, "ListTemplate")
                                        screenManager.push(ListTemplateScreen(carContext))
                                    }
                                } else if (displaySpeed != null) {
                                    setText(
                                        formatCarUnitSpeed(
                                            speed.displaySpeedMetersPerSecond.value,
                                        )
                                    )
                                    setImage(
                                        gauge.draw(Float.min(displaySpeed / maxSpeed * 100, 100f))
                                            .asCarIcon()
                                    )
                                } else {
                                    setText("auto_no_data")
                                    setImage(gauge.draw(0f).asCarIcon())
                                }
                            }
                        }.build()
                    )
                    addItem(
                        GridItem.Builder().apply {
                            setTitle("POI")
                            setImage(
                                CarIcon.Builder(
                                    IconCompat.createWithResource(
                                        carContext,
                                        android.R.drawable.ic_menu_directions
                                    )
                                ).build()
                            ).setOnClickListener {
                                screenManager.push(PoiTemplateScreen(carContext))
                            }

                        }.build()
                    )

                    addItem(
                        GridItem.Builder().apply {
                            setTitle("SEARCH")
                            setImage(
                                CarIcon.Builder(
                                    IconCompat.createWithResource(
                                        carContext,
                                        android.R.drawable.ic_menu_search
                                    )
                                ).build()
                            ).setOnClickListener {
                                screenManager.push(SearchTemplateScreen(carContext))
                            }

                        }.build()
                    )


                }.build()
            )
        }.build()
    }
}
