package com.mobile.androiddicodingproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvSongs: RecyclerView
    private val list = ArrayList<Song>()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvSongs = findViewById(R.id.rv_songs)
        rvSongs.setHasFixedSize(true)

        list.addAll(getlistSongs())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_about, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getlistSongs(): ArrayList<Song> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listSong = ArrayList<Song>()
        for (i in dataName.indices) {
            val song = Song(dataName[i], dataDescription[i], dataPhoto[i])
            listSong.add(song)
        }
        return listSong
    }

    private fun showRecyclerList() {
        rvSongs.layoutManager = LinearLayoutManager(this)
        val listSongAdapter = ListSongAdapter(list)
        rvSongs.adapter = listSongAdapter

        listSongAdapter.setOnItemClickCallback(object : ListSongAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Song) {
                showSelectedHero(data)
            }
        })
    }

    private fun showSelectedHero(song : Song) {
        Toast.makeText(this, "Kamu memilih " + song.name, Toast.LENGTH_SHORT).show()
    }
}