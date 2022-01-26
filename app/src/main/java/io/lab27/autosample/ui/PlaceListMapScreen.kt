package io.lab27.autosample.ui

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.car.app.CarContext
import androidx.car.app.OnRequestPermissionsListener
import androidx.car.app.Screen
import androidx.car.app.model.*
import androidx.core.graphics.drawable.IconCompat
import io.lab27.autosample.R
import io.lab27.autosample.showToast
import java.security.Permission

class PlaceListMapScreen(carContext: CarContext, val destination: String) :
    Screen(carContext) {
    override fun onGetTemplate(): Template {
        val destinations = mapOf(
//            "서울 시청" to  Place.Builder(CarLocation.create(37.5663, 126.9779)).build(),
            "American University" to  Place.Builder(CarLocation.create(38.938045, -77.089392)).build(),

        )

        val listBuilder = ItemList.Builder()

        destinations.forEach {
            listBuilder.addItem(Row.Builder()
                .setTitle(it.key)
                .addText("${it.key} + addr")
                .setMetadata(
                    Metadata.Builder()
                        .setPlace(it.value)
                        .build()
                )
                .setBrowsable(true)
                .setOnClickListener {

                }
                .build()
            )
        }


//            placeList.forEach {
//
//                val location = Location(NavigationScreen::class.java.simpleName)
//                location.latitude = it.geometry?.location?.lat!!
//                location.longitude = it.geometry?.location?.lng!!
//
//                listBuilder.addItem(Row.Builder()
//                    .setTitle(it.name)
//                    .addText(it.address)
//                    .setMetadata(
//                        Metadata.Builder()
//                            .setPlace(getPlace(it))
//                            .build()
//                    )
//                    .setBrowsable(true)
//                    .setOnClickListener {
//                        onPlaceClick(it)
//                    }
//                    .build())
//            }

        val builder = PlaceListMapTemplate.Builder()
            .setTitle(destination)
            .setHeaderAction(Action.BACK)
            .setCurrentLocationEnabled(true)

//        if (permission != PackageManager.PERMISSION_GRANTED) {
//            carContext.requestPermissions(listOf(locationPermission), listener)
//        } else {
//            return builder.setLoading(true).build()
//        }
//


//        return if (placeList.isEmpty()) {
//            builder.setLoading(true).build()
//        } else {
            return builder.setItemList(listBuilder.build()).build()
//        }
    }
}