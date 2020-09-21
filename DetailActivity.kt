package com.study.marvel.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.study.marvel.R
import com.study.marvel.model.CharacterResults
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private var result: CharacterResults? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayShowHomeEnabled(true)

        intent.extras.let {
            result = it?.getParcelable(MainActivity.CHARACTER_EXTRA)
        }

        title = result?.name

        Glide.with(this)
            .load(result?.thumbnail?.path + "/portrait_incredible." + result?.thumbnail?.extension)
            .into(detail_thumnail)

        detail_title.text = result?.name ?: getString(R.string.title)

        when (result?.description) {
            "" -> {
                detail_description.text = getString(R.string.description)
            }
            else -> {
                detail_description.text = result?.description
            }
        }

    }
}