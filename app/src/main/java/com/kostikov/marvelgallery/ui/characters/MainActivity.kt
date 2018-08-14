package com.kostikov.marvelgallery.ui.characters

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.kostikov.marvelgallery.MarvelApplication
import com.kostikov.marvelgallery.R
import com.kostikov.marvelgallery.model.MarvelCharacter
import com.kostikov.marvelgallery.ui.characters.adapters.CharacterItemAdapter
import com.kostikov.marvelgallery.ui.characters.adapters.MainListAdapter
import io.reactivex.rxkotlin.subscribeBy
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

        initViews()

        retrieveCharacters()
    }

    private fun initViews() {
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        refreshLayout.onRefresh{
            (recyclerView.adapter as MainListAdapter).clear()
            retrieveCharacters()
        }
    }

    private fun retrieveCharacters() {
        progressWheel.visibility = View.VISIBLE
        charactersViewModel.getCharactersList().subscribeBy(
                onError = ::charactersListShowError,
                onSuccess = ::charactersListShowSuccess
        )
    }

    private fun charactersListShowError(exception: Throwable){
        progressWheel.visibility = View.GONE
    }

    private fun charactersListShowSuccess(charactersList: List<MarvelCharacter>) {
        val charactersAdapters = charactersList.map (::CharacterItemAdapter)
        recyclerView.adapter = MainListAdapter(charactersAdapters)

        progressWheel.visibility = View.GONE
    }

    private fun SwipeRefreshLayout.onRefresh(doOnRefresh: () -> Unit){
        refreshLayout.setOnRefreshListener {
            if (progressWheel.visibility != View.VISIBLE) {
            refreshLayout.isRefreshing = true
            doOnRefresh()
            refreshLayout.isRefreshing = false
        }  }
    }

}
