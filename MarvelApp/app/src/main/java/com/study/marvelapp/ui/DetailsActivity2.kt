package com.study.marvelapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.study.marvelapp.R
import com.study.marvelapp.model.CharacterResults
import kotlinx.android.synthetic.main.activity_details2.*

class DetailsActivity2 : AppCompatActivity() {
    private var results: CharacterResults? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details2)

        supportActionBar?.setDisplayShowHomeEnabled(true)

        intent.extras.let {
            results = it?.getParcelable<CharacterResults>(MainActivity.CHARACTER_EXTRA)
        }

        title = results?.name

        Glide.with(this)
            .load(results?.thumbnail?.path + "/portrait_incredible." + results?.thumbnail?.extension)
            .into(detail_thumnail)

        detail_title.text = results?.name

        when(results?.description){
            "" -> {
                detail_description.text = getString(R.string.descripton)
            }
            else ->   detail_description.text = results?.description

        }

    }
}