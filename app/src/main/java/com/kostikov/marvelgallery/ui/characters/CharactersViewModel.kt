package com.kostikov.marvelgallery.ui.characters

import android.arch.lifecycle.ViewModel
import com.kostikov.marvelgallery.data.MarvelRepository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * @author Kostikov Aleksey.
 */
class CharactersViewModel(private val charactersRepository: MarvelRepository): ViewModel() {

    val disposables = CompositeDisposable()

    private val characterListProcessor: BehaviorProcessor<CharactersViewState> = BehaviorProcessor.create()


    fun observeViewState(): Flowable<CharactersViewState> {
        return characterListProcessor
    }

    fun requestCharacterList(){
        charactersRepository.getAllCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    characterListProcessor.onNext(CharacterLoading(true))  }
                .doOnError { characterListProcessor.onNext(CharacterError(it)) }
                .subscribeBy {
                    characterListProcessor.onNext(CharacterLoading(false))
                    characterListProcessor.onNext(CharacterSuccess(it)) }
    }


    fun onDestroyViewModel(){
        disposables.clear()
    }


}