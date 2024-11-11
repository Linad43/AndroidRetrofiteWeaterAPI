package com.example.androidretrofiteweaterapi

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.androidretrofiteweaterapi.data.ItemsPage
import com.example.androidretrofiteweaterapi.fragments.MainFragment
import com.example.androidretrofiteweaterapi.servise.ViewPagerAdapter
import com.example.androidretrofiteweaterapi.utils.RetrofitInstance
import com.squareup.picasso.Picasso
//import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: ViewPagerAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hello)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.containerFragment, MainFragment())
//            .commit()

        adapter = ViewPagerAdapter(this, ItemsPage.listItems)
        viewPager = findViewById(R.id.view_pager)
        viewPager.adapter = adapter

    }

}