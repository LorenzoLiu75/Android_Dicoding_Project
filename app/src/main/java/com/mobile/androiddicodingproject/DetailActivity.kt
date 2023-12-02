package com.mobile.androiddicodingproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mobile.androiddicodingproject.databinding.ActivityDetailBinding
import java.lang.reflect.Type

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Details"

        val song = intent.getParcelableExtra<Song>("key_song")

        val tvDetailName = findViewById<TextView>(R.id.tv_detail_name)
        val tvDetailDescription = findViewById<TextView>(R.id.tv_detail_description)
        val ivDetailPhoto = findViewById<ImageView>(R.id.iv_detail_photo)
        val shareButton = findViewById<Button>(R.id.action_share)

        binding.apply {
            tvDetailName.text = song?.name
            tvDetailDescription.text = song?.description

            song?.photo?.let {
                Glide.with(this@DetailActivity)
                    .load(it)
                    .into(ivDetailPhoto)
            }

            shareButton.setOnClickListener {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Check out this song: ${song?.name}\nLyric: ${song?.description}")
                    type = "text/plain"
                }
                startActivity(Intent.createChooser(shareIntent, "Share via"))
            }
        }
    }
}