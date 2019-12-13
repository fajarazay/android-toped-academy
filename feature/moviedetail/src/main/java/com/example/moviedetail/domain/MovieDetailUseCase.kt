package com.example.moviedetail.domain

import tokopedia.app.abstraction.util.state.ResultState
import tokopedia.app.data.entity.Movie
import tokopedia.app.data.repository.movie.MovieDetailRepository

/**
 * Created by FAJAR SEPTIAN on 2019-12-12.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */

class MovieDetailUseCase(private val repository: MovieDetailRepository) {
    suspend fun get(movieId: String): ResultState<Movie> {
        val popularMovie = repository.getMovieDetail(movieId)
        return if (popularMovie.isSuccessful) {
            ResultState.Success(popularMovie.body()!!)
        } else {
            ResultState.Error(MOVIE_ERROR)
        }
    }

    companion object {
        private const val MOVIE_ERROR = " yah gagal"
    }
}