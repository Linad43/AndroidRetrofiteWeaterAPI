package com.example.androidretrofiteweaterapi.data

import com.example.androidretrofiteweaterapi.R
import java.io.Serializable

class ItemsPage(
    val name: String,
    val image: Int,
) : Serializable {
    companion object {
        val listItems = arrayListOf(
            ItemsPage("Листай дальше", R.drawable.on_a_holiday),
            ItemsPage("Еще раз", R.drawable.boatmen_on_the_volga),
            ItemsPage("Последний раз", R.drawable.polenov_golden_autumn),
            ItemsPage("Нажмите на экран", R.drawable.morning_in_the_pine_forest)
        )
    }
}