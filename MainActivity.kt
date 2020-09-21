package com.study.marvel.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.study.marvel.R
import com.study.marvel.model.CharacterResults
import com.study.marvel.utils.gone
import com.study.marvel.utils.launchActivity
import com.study.marvel.utils.state.Status
import com.study.marvel.utils.visible
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
                    progressBar?.visible()
                    it.data.let {
                        adapter.submitList(it?.data?.results)
                    }
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


        viewModel.errorLiveData.observe(this, Observer { error ->
            error?.let { Toast.makeText(this, error, Toast.LENGTH_SHORT).show() }
        })

        viewModel.progressLiveData.observe(
            this,
            Observer { if (it!!) progressBar.visible() else progressBar.gone() })


    }


    private fun onCharacterClick(results: CharacterResults) {
        launchActivity<DetailActivity> {
            putExtra(CHARACTER_EXTRA, results)
        }
    }


    companion object {
        const val CHARACTER_EXTRA = "character_extra"
    }
}