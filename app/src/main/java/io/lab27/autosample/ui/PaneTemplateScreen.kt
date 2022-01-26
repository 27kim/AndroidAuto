package io.lab27.autosample.ui

import android.util.Log
import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.*
import androidx.core.graphics.drawable.IconCompat
import io.lab27.autosample.R
import io.lab27.autosample.showToast

class PaneTemplateScreen(carContext: CarContext) : Screen(carContext) {
    override fun onGetTemplate(): Template {
        Log.i("AutoSampleService", "onGetTemplate")

        val actionStrip = ActionStrip.Builder().addAction(Action.BACK).build()
        val pane = Pane.Builder().apply {
            addAction(
                Action.Builder()
                    .setIcon(
                        CarIcon.Builder(
                            IconCompat.createWithResource(
                                carContext,
                                R.drawable.ic_launcher_foreground
                            )
                        ).build()
                    )
                    .setTitle("Radid")
                    .setOnClickListener {
                        showToast(carContext, "GridTemplate")
                        screenManager.push(MainTemplateScreen(carContext))
                    }
                    .build()
            )
            addAction(
                Action.Builder()
                    .setIcon(
                        CarIcon.Builder(
                            IconCompat.createWithResource(
                                carContext,
                                R.drawable.ic_launcher_foreground
                            )
                        ).build()
                    )
                    .setTitle("Normal")
                    .setOnClickListener {
                        showToast(carContext, "ListTemplate")
                        screenManager.push(ListTemplateScreen(carContext))
                    }.build()
            )

            (0..3).forEach {
                addRow(
                    Row.Builder()
                        .setTitle("Charging stations No.$it")
                        .addText("${it * 1.5f} km away")
                        .build()
                )
            }.apply {
                addRow(
                    Row.Builder()
                        .setTitle("number of items limited")
                        .addText(" actions up to 2, row up to 4")
                        .build()
                )
            }

        }.build()

        return PaneTemplate.Builder(pane)
            .setHeaderAction(Action.APP_ICON)
            .setTitle("Charging stations nearby")
            .setActionStrip(actionStrip)
            .build()
    }
}