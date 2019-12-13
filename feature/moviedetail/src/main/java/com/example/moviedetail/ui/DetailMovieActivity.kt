package com.example.moviedetail.ui

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.moviedetail.R
import com.example.moviedetail.domain.MovieDetailUseCase
import com.example.moviedetail.factory.DetailMovieFactory
import kotlinx.android.synthetic.main.detail_movie.*
import tokopedia.app.abstraction.base.BaseActivity
import tokopedia.app.abstraction.util.ext.load
import tokopedia.app.data.entity.Movie
import tokopedia.app.data.repository.movie.MovieDetailRepository
import tokopedia.app.data.repository.moviedetail.MovieDetailRepositoryImpl
import tokopedia.app.data.routes.NetworkServices
import tokopedia.app.network.Network

/**
 * Created by FAJAR SEPTIAN on 2019-12-12.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
class DetailMovieActivity : BaseActivity() {
    override fun contentView(): Int = R.layout.detail_movie

    private lateinit var repository: MovieDetailRepository
    private lateinit var useCase: MovieDetailUseCase
    private lateinit var viewModel: DetailMovieViewModel

    override fun initView() {
        //get movie id
        intent?.data?.lastPathSegment?.let {
            viewModel.setMovieId(it)
        }

        viewModel.error.observe(this, onShowError())

        viewModel.movie.observe(this, Observer {
            showToast(it.title)
            showMovieDetail(it)
        })
    }

    private fun showMovieDetail(movie: Movie) {
        imgBanner.load(movie.bannerUrl())
        imgPoster.load(movie.posterUrl())
        txtMovieName.text = movie.title
        txtYear.text = movie.releaseDate
        txtContent.text = movie.overview
        txtRating.text = movie.voteAverage.toString()
        txtVote.text = movie.voteCount.toString()
    }

    override fun initObservable() {
        val networkBuilder = Network.builder().create(NetworkServices::class.java)

        //init repository
        repository = MovieDetailRepositoryImpl(networkBuilder)

        //init usecase
        useCase = MovieDetailUseCase(repository);

        //init viewmodel
        viewModel = ViewModelProviders.of(this, DetailMovieFactory(useCase))
            .get(DetailMovieViewModel::class.java)
    }

    private fun onShowError(): Observer<String> {
        return Observer {
            showToast(it)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}