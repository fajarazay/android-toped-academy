package com.example.moviedetail.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviedetail.domain.MovieDetailUseCase
import com.example.moviedetail.ui.DetailMovieViewModel


/**
 * Created by FAJAR SEPTIAN on 2019-12-12.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
class DetailMovieFactory(private val useCase: MovieDetailUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailMovieViewModel(useCase) as T
    }
}