package com.eggy.movieapp.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eggy.movieapp.BuildConfig
import com.eggy.movieapp.core.domain.model.Movie
import com.eggy.movieapp.databinding.ItemListMovieBinding


@SuppressLint("NotifyDataSetChanged")
class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private val listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private val binding: ItemListMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(item: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(BuildConfig.BASE_IMAGE_URL + item.posterPath)
                    .into(ivItemImage)
                tvItemTitle.text = item.originalTitle
                tvRating.text = item.voteAverage.toString()
            }
        }

        init {

            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size
}