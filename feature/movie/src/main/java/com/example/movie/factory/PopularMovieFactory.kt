package com.example.movie.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movie.domain.PopularMovieUseCase
import com.example.movie.ui.PopularMovieViewModel

/**
 * Created by FAJAR SEPTIAN on 2019-12-12.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
class PopularMovieFactory(private val useCase: PopularMovieUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PopularMovieViewModel(useCase) as T
    }
}