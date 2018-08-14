package com.kostikov.marvelgallery.ui.characters

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.kostikov.marvelgallery.MarvelApplication
import com.kostikov.marvelgallery.R
import com.kostikov.marvelgallery.ui.characters.adapters.CharacterItemAdapter
import com.kostikov.marvelgallery.ui.characters.adapters.MainListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var charactersViewModel: CharactersViewModel

    @Inject
    lateinit var charactersViewModelFactory: CharactersViewModelFactory

    init {
        MarvelApplication.getCharactersComponent().inject(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        charactersViewModel = ViewModelProviders.of(this, charactersViewModelFactory).get(CharactersViewModel::class.java);

        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val charactersAdapters = charactersViewModel.characters.map (::CharacterItemAdapter)
        recyclerView.adapter = MainListAdapter(charactersAdapters)
    }
}
