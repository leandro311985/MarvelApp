package com.study.marvel.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.study.marvel.R
import com.study.marvel.model.CharacterResults
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private var results: CharacterResults? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayShowHomeEnabled(true)

        intent.extras.let {
            results = it?.getParcelable<CharacterResults>(MainActivity.CHARACTER_EXTRA)
        }

        title = results?.name

        Glide.with(this)
            .load(results?.thumbnail?.path + "/portrait_incredible." + results?.thumbnail?.extension)
            .into(detail_thumnail)

        detail_title.text = results?.name ?: getString(R.string.title)

        when (results?.description) {
            "" -> {
                detail_description.text = getString(R.string.description)
            }
            else -> {
                detail_description.text = results?.description
            }
        }

    }
}