package io.lab27.autosample.ui

import android.util.Log
import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.*
import io.lab27.autosample.showToast

class ListTemplateScreen(carContext: CarContext) : Screen(carContext) {
    override fun onGetTemplate(): Template {
        val itemList = ItemList.Builder().apply {
            (0..10).forEach {
                addItem(
                    Row.Builder().apply {
                        setTitle("Station No.$it")
                        setImage(CarIcon.ALERT)
                        addText("${it * 2.5f} km away ⭐ $it.3")
                        if(it%2 ==0) addText("⛽ Diesel")
                        setBrowsable(true)
//                        if(it%3 ==0) addText("")
                        setOnClickListener { showToast(carContext, "Hi") }
                    }.build()
                )
            }
        }.build()
        return ListTemplate.Builder().apply {
            setTitle("Gas stations nearby")
            setHeaderAction(Action.BACK)
            setSingleList(itemList)
        }.build()
    }
}