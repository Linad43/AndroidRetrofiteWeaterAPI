package com.example.androidretrofiteweaterapi.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.androidretrofiteweaterapi.R
import com.example.androidretrofiteweaterapi.utils.RetrofitInstance
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MainFragment : Fragment() {

    private lateinit var tempTV: TextView
    private lateinit var cityTV: TextView
    private lateinit var weatherIV: ImageView
    private lateinit var directTV: TextView
    private lateinit var speedTV: TextView
    private lateinit var pressureTV: TextView
    private lateinit var getDataBTN:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        getCurrentWeather()
    }

    private fun init(view: View) {
        tempTV = view.findViewById(R.id.tempTV)
        cityTV = view.findViewById(R.id.cityTV)
        weatherIV = view.findViewById(R.id.imageIV)
        directTV = view.findViewById(R.id.directTV)
        speedTV = view.findViewById(R.id.speedTV)
        pressureTV = view.findViewById(R.id.pressureTV)
        getDataBTN = view.findViewById(R.id.getData)
    }

    @SuppressLint("SetTextI18n")
    private fun getCurrentWeather() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {
                RetrofitInstance.api.getCurrentWeather(
                    "Moscow",
                    "metric",
                    activity?.applicationContext?.getString(R.string.key)!!
                )
            } catch (e: IOException) {
                Toast.makeText(
                    activity?.applicationContext,
                    "app error ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
                return@launch
            } catch (e: HttpException) {
                Toast.makeText(
                    activity?.applicationContext,
                    "http error ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    val data = response.body()
                    cityTV.text = data!!.name
                    tempTV.text = "${data.main.temp} Cels"
                    directTV.text = "${data.wind.deg}"
                    speedTV.text = "${data.wind.speed} m/sec"
                    val icon = data.weather[0].icon
                    val imageUrl = "https://openweathermap.org/img/wn/$icon@4x.png"
                    Picasso.get().load(imageUrl).into(weatherIV)
                    val convertPressure = (data.main.pressure/1.33).toInt()
                    pressureTV.text = "$convertPressure mm Hg"
                }
            }
        }
    }
}