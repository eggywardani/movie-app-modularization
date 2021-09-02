package com.eggy.movieapp.detail

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.eggy.movieapp.BuildConfig
import com.eggy.movieapp.R
import com.eggy.movieapp.core.domain.model.Movie
import com.eggy.movieapp.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {


    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        val detail = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        initView(detail)
    }

    private fun initView(detail: Movie?) {
        supportActionBar?.title = detail?.originalTitle
        binding.content.tvOverview.text = detail?.overview
        binding.content.tvRating.text = detail?.voteAverage.toString()

        if (detail?.backdropPath.isNullOrEmpty()){
            binding.content.ivPoster2.visibility = View.GONE
        }


        loadImage(BuildConfig.BASE_IMAGE_URL + detail?.posterPath, binding.ivDetailImage)
        loadImage(BuildConfig.BASE_IMAGE_URL + detail?.posterPath, binding.content.ivPoster1)
        loadImage(BuildConfig.BASE_IMAGE_URL + detail?.backdropPath, binding.content.ivPoster2)

        var favorite = detail?.isFavorite as Boolean
        setFavoriteMovie(favorite)
        binding.fab.setOnClickListener {
            showMessage(favorite)
            favorite = !favorite
            detailViewModel.setFavoriteMovie(detail, favorite)
            setFavoriteMovie(favorite)
        }

    }

    private fun loadImage(imageUrl: String, imageView: ImageView) {
        Glide.with(this)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)

    }


    private fun showMessage(favorite: Boolean) {
        if (favorite) {
            Snackbar.make(binding.root, "Deleted from favorite", Snackbar.LENGTH_SHORT).show()
        } else {
            Snackbar.make(binding.root, "Added to  favorite", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setFavoriteMovie(favorite: Boolean) {
        if (favorite) {
            binding.fab.setImageResource(R.drawable.ic_favorite)
        } else {
            binding.fab.setImageResource(R.drawable.ic_favorite_border)
        }

    }
    companion object {
        const val EXTRA_DATA = "extra_data"
    }


}