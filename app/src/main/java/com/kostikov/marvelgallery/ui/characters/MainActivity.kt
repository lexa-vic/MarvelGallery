package com.kostikov.marvelgallery.ui.characters

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.kostikov.marvelgallery.MarvelApplication
import com.kostikov.marvelgallery.R
import com.kostikov.marvelgallery.extensions.isVisible
import com.kostikov.marvelgallery.model.MarvelCharacter
import com.kostikov.marvelgallery.ui.characters.adapters.CharacterItemAdapter
import com.kostikov.marvelgallery.ui.characters.adapters.MainListAdapter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var charactersViewModel: CharactersViewModel

    @Inject
    lateinit var charactersViewModelFactory: CharactersViewModelFactory

    val disposables = CompositeDisposable()

    init {
        MarvelApplication.getCharactersComponent().inject(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        charactersViewModel = ViewModelProviders.of(this, charactersViewModelFactory).get(CharactersViewModel::class.java);

        initViews()
        progressWheel.isVisible = false

        disposables.add(charactersViewModel.observeViewState().subscribe(::render))

        if (recyclerView.adapter == null) {
            charactersViewModel.requestCharacterList()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if (isFinishing) {
            charactersViewModel.onDestroyViewModel()
        }
        disposables.clear()
    }

    private fun render(viewState: CharactersViewState) {
        when (viewState) {
            is CharacterError ->  progressWheel.isVisible = false
            is CharacterSuccess -> charactersListShowSuccess(viewState.list)
            is CharacterLoading -> {
                if (refreshLayout.isRefreshing) {
                    refreshLayout.isRefreshing = viewState.state
                } else {
                    progressWheel.isVisible = viewState.state
                }
            }
        }
    }

    private fun initViews() {
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        refreshLayout.onRefresh {
            (recyclerView.adapter as MainListAdapter).clear()
            charactersViewModel.requestCharacterList()
        }
    }

    private fun charactersListShowSuccess(charactersList: List<MarvelCharacter>) {
        val charactersAdapters = charactersList.map(::CharacterItemAdapter)
        recyclerView.adapter = MainListAdapter(charactersAdapters)
    }

    private fun SwipeRefreshLayout.onRefresh(doOnRefresh: () -> Unit) {
        refreshLayout.setOnRefreshListener {
            if (!progressWheel.isVisible) {
                doOnRefresh()
            }
        }
    }
}
