package com.kostikov.marvelgallery

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.kostikov.marvelgallery.adapters.CharacterItemAdapter
import com.kostikov.marvelgallery.adapters.MainListAdapter
import com.kostikov.marvelgallery.data.MarvelRepository
import com.kostikov.marvelgallery.model.MarvelCharacter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var marvelRepository: MarvelRepository

    private val characters = mutableListOf(
            MarvelCharacter(name = "3-D Man", imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"),
            MarvelCharacter(name = "Abomination (Emil Blonsky)", imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04.jpg")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MarvelApplication.getCharactersComponent().inject(this)

        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val charactersAdapters = characters.map (::CharacterItemAdapter)
        recyclerView.adapter = MainListAdapter(charactersAdapters)

    }

    override fun onResume() {
        super.onResume()
        (recyclerView.adapter as MainListAdapter).clear()

        marvelRepository.getAllCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({list, th ->
                    val charactersAdapters = list.map (::CharacterItemAdapter)
                    recyclerView.adapter = MainListAdapter(charactersAdapters)
                })
    }

    override fun onDestroy() {
        super.onDestroy()

        if (isFinishing) {
            MarvelApplication.releaseCharactersComponent()
        }
    }
}
