package com.example.androidretrofiteweaterapi

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.androidretrofiteweaterapi.data.ItemsPage
import com.example.androidretrofiteweaterapi.servise.ViewPagerAdapter
import com.example.androidretrofiteweaterapi.utils.RetrofitInstance
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class HelloActivity : AppCompatActivity() {

    private lateinit var tempTV: TextView
    private lateinit var cityET: EditText
    private lateinit var weatherIV: ImageView
    private lateinit var directTV: TextView
    private lateinit var speedTV: TextView
    private lateinit var pressureTV: TextView
    private lateinit var getDataBTN: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()
        getCurrentWeather("Moscow")
        getDataBTN.setOnClickListener {
            getCurrentWeather(cityET.text.toString())
        }
    }

    private fun init() {
        tempTV = findViewById(R.id.tempTV)
        cityET = findViewById(R.id.cityET)
        weatherIV = findViewById(R.id.imageIV)
        directTV = findViewById(R.id.directTV)
        speedTV = findViewById(R.id.speedTV)
        pressureTV = findViewById(R.id.pressureTV)
        getDataBTN = findViewById(R.id.getData)
    }

    @SuppressLint("SetTextI18n")
    private fun getCurrentWeather(city: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {

                RetrofitInstance.api.getCurrentWeather(
//                    "Moscow",
                    city,
                    "metric",
                    applicationContext.getString(R.string.key)
                )

            } catch (e: IOException) {
                Toast.makeText(
                    applicationContext,
                    "app error ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
                return@launch
            } catch (e: HttpException) {
                Toast.makeText(
                    applicationContext,
                    "http error ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    val data = response.body()
                    cityET.setText(data!!.name)
                    tempTV.text = "${data.main.temp} Cels"
                    directTV.text = "${data.wind.deg}"
                    speedTV.text = "${data.wind.speed} m/sec"
                    val icon = data.weather[0].icon
                    val imageUrl = "https://openweathermap.org/img/wn/$icon@4x.png"
                    Picasso.get().load(imageUrl).into(weatherIV)
                    val convertPressure = (data.main.pressure / 1.33).toInt()
                    pressureTV.text = "${convertPressure} mm Hg"
                }
            }
        }
    }

}