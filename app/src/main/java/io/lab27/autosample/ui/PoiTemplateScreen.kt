package io.lab27.autosample.ui

import android.graphics.drawable.Icon
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.car.app.CarContext
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


class PoiTemplateScreen(carContext: CarContext) : Screen(carContext) {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onGetTemplate(): Template {
        return GridTemplate.Builder().apply {
            setTitle("POI")
            setHeaderAction(Action.BACK)
            setSingleList(
                ItemList.Builder().apply {
                    addItem(
                        GridItem.Builder().apply {
                            setTitle("Home")

                            setImage(
                                CarIcon.Builder(
                                    IconCompat.createWithResource(
                                        carContext,
                                        android.R.drawable.ic_dialog_email
                                    )
                                ).build()
                            ).setOnClickListener {
                                screenManager.push(
                                    DetailScreen(
                                        carContext = carContext,
                                        name = "Home",
                                        addr = "서울특별시 중구 세종대로 110")
                                )
                            }
                        }.build()
                    )
                    addItem(
                        GridItem.Builder().apply {
                            setTitle("Work")
                            setText("3km")
                            setImage(
                                CarIcon.Builder(
                                    IconCompat.createWithResource(
                                        carContext,
                                        android.R.drawable.ic_dialog_dialer
                                    )
                                ).build()
                            ).setOnClickListener {
                                screenManager.push(
                                    DetailScreen(
                                        carContext = carContext,
                                        name = "Work",
                                        addr = "서울특별시 중구 세종대로 110")
                                )
                            }
                        }.build()
                    )
                    addItem(
                        GridItem.Builder().apply {
                            setTitle("Gas Station")
                            setText("2.5km")
                            setImage(
                                CarIcon.Builder(
                                    IconCompat.createWithResource(
                                        carContext,
                                        android.R.drawable.ic_dialog_info
                                    )
                                ).build()
                            ).setOnClickListener {
                                screenManager.push(
                                    DetailScreen(
                                        carContext = carContext,
                                        name = "Gas Station",
                                        addr = "서울특별시 중구 세종대로 110")
                                )
                            }
                        }.build()
                    )

                    addItem(
                        GridItem.Builder().apply {
                            setTitle("School")
                            setText("0.9km")
                            setImage(
                                CarIcon.Builder(
                                    IconCompat.createWithResource(
                                        carContext,
                                        android.R.drawable.ic_dialog_map
                                    )
                                ).build()
                            ).setOnClickListener {
                                screenManager.push(
                                    DetailScreen(
                                        carContext = carContext,
                                        name = "School",
                                        addr = "서울특별시 중구 세종대로 110")
                                )
                            }
                        }.build()
                    )
                    addItem(
                        GridItem.Builder().apply {
                            setTitle("Coffee")
                            setText("1.3km")
                            setImage(
                                CarIcon.Builder(
                                    IconCompat.createWithResource(
                                        carContext,
                                        android.R.drawable.ic_menu_directions
                                    )
                                ).build()
                            ).setOnClickListener {
                                screenManager.push(
                                    DetailScreen(
                                        carContext = carContext,
                                        name = "Coffee",
                                        addr = "서울특별시 중구 세종대로 110")
                                )
                            }
                        }.build()
                    )
                    addItem(
                        GridItem.Builder().apply {
                            setTitle("Grocery")
                            setImage(
                                CarIcon.Builder(
                                    IconCompat.createWithResource(
                                        carContext,
                                        android.R.drawable.ic_menu_search
                                    )
                                ).build()
                            ).setOnClickListener {
                                screenManager.push(
                                    DetailScreen(
                                        carContext = carContext,
                                        name = "Grocery",
                                        addr = "서울특별시 중구 세종대로 110")
                                )
                            }
                        }.build()
                    )
                }.build()
            )
        }.build()
    }
}
