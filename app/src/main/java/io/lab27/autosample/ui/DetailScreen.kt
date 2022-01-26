package io.lab27.autosample.ui

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.*

class DetailScreen(carContext: CarContext, var name : String, var addr : String) : Screen(carContext) {
    override fun onGetTemplate(): Template {

        val pane = Pane.Builder()
            .addAction(
                Action.Builder()
                    .setTitle("Navigate")
                    .setBackgroundColor(CarColor.BLUE)
                    .setOnClickListener { screenManager.push(PlaceListMapScreen(carContext, name)) }
//                    .setOnClickListener(this::onClickNavigate)
                    .build()
            ).addAction(
                Action.Builder()
                    .setTitle("Back")
                    .setBackgroundColor(CarColor.BLUE)
//                    .setOnClickListener(this::onClickBack)
                    .build()
            )
            .addRow(
                Row.Builder()
                    .setTitle("Address")
                    .addText(addr)
                    .build()
            )
            .build()
        return PaneTemplate.Builder(pane)
            .setTitle(name)
            .setHeaderAction(Action.BACK)
            .build()
    }
}