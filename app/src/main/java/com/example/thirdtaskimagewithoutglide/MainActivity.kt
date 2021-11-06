package com.example.thirdtaskimagewithoutglide

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.lang.Exception
import java.net.URL


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        open_website_button.setOnClickListener {
            var image : Bitmap? = null
            val job: Job = GlobalScope.launch(Dispatchers.IO) {
                try {
                    val newurl = URL(website_edittext.text.toString())
                    Log.d("TAG", "URL = " + newurl.toString())
                    image = BitmapFactory.decodeStream(newurl.openConnection().getInputStream())
                    withContext(Dispatchers.Main){
                        imageView.setImageBitmap(image)
                    }
                } catch (e: Exception) {
                    Log.d("TAG", "ERROR MESSAGE = " + e.toString())
                    Toast.makeText(applicationContext, "Something went wrong. Try again, please.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}