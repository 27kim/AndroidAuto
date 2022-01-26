package io.lab27.autosample.ui

import android.util.Log
import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.*
import io.lab27.autosample.showToast

class SearchTemplateScreen(carContext: CarContext) : Screen(carContext) {
    override fun onGetTemplate(): Template {

        val callback =  object : SearchTemplate.SearchCallback {
            override fun onSearchTextChanged(searchText: String) {
                super.onSearchTextChanged(searchText)
            }

            override fun onSearchSubmitted(searchText: String) {
                showToast(carContext, searchText)
            }
        }

        val itemList = ItemList.Builder().apply {
            (0..2).forEach {
                addItem(
                    Row.Builder().apply {
                        setTitle("My Favorite No.${it+1}")
                        setImage(CarIcon.ALERT)
                    }.build()
                )
            }
        }.build()

        return SearchTemplate.Builder(callback).apply {
            setItemList(itemList)
            setHeaderAction(Action.BACK)
            setSearchHint("type your query")
        }.build()
    }
}