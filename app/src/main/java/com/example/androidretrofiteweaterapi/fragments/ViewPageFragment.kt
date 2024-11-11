package com.example.androidretrofiteweaterapi.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.androidretrofiteweaterapi.HelloActivity
import com.example.androidretrofiteweaterapi.MainActivity
import com.example.androidretrofiteweaterapi.R
import com.example.androidretrofiteweaterapi.data.ItemsPage

class ViewPageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_page, container, false)
    }

    @SuppressLint("CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nameTV: TextView = view.findViewById(R.id.nameTV)
        val imageIV: ImageView = view.findViewById(R.id.imageIV)
        val viewPagerItems = this.arguments?.getSerializable("vp") as ItemsPage
        nameTV.text = viewPagerItems.name
        imageIV.setImageResource(viewPagerItems.image)
        val main = view.findViewById<FrameLayout>(R.id.main)
        main.setOnClickListener {
            if (viewPagerItems.name == ItemsPage.listItems.last().name) {
                startActivity(Intent(activity, HelloActivity::class.java))
            }
        }
    }
}