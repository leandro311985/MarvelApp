package com.study.marvelapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.study.marvelapp.R
import com.study.marvelapp.model.CharacterResults
import com.study.marvelapp.utils.gone
import com.study.marvelapp.utils.launchActivity
import com.study.marvelapp.utils.state.Status
import com.study.marvelapp.utils.visible
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    val viewModel: CharacterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = CharacterAdapter(this::onCharacterClick)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter

        viewModel.getCharacters()
        viewModel.characterListLiveData.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar?.gone()
                    adapter.submitList(it?.data?.data?.results)
                }
                Status.LOADING -> {
                    progressBar?.visible()

                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    progressBar?.gone()

                }
            }

        })

        viewModel.progressLiveData.observe(
            this,
            Observer { if (it!!) progressBar.visible() else progressBar.gone() })

        viewModel.errorLiveData.observe(this, Observer { error ->
            error?.let { Toast.makeText(this, error, Toast.LENGTH_SHORT).show() }
        })
    }

    private fun onCharacterClick(results: CharacterResults) {
        launchActivity<DetailsActivity> {
            putExtra(CHARACTER_EXTRA, results)
        }
    }

    companion object {
        const val CHARACTER_EXTRA = "character_extra"
    }
}