package com.eggy.movieapp.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.eggy.movieapp.R
import com.eggy.movieapp.core.data.Resource
import com.eggy.movieapp.core.ui.MovieAdapter
import com.eggy.movieapp.databinding.FragmentMovieBinding
import com.eggy.movieapp.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel


class MovieFragment : Fragment() {

    private val movieViewModel: MovieViewModel by viewModel()
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val adapter = MovieAdapter()


            adapter.onItemClick = {
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, it)
                startActivity(intent)
            }


            movieViewModel.movies.observe(viewLifecycleOwner) { movies ->
                if (movies != null) {
                    when (movies) {
                        is com.eggy.movieapp.core.data.Resource.Loading<*> -> binding?.progress?.visibility = View.VISIBLE
                        is com.eggy.movieapp.core.data.Resource.Success<*> -> {
                            binding?.progress?.visibility = View.GONE
                            adapter.setData(movies.data)
                        }
                        is com.eggy.movieapp.core.data.Resource.Error<*> -> {
                            binding?.progress?.visibility = View.GONE
                            binding?.viewError?.root?.visibility = View.VISIBLE
                            binding?.viewError?.tvError?.text =
                                movies.message ?: getString(R.string.something_wrong)
                        }
                    }
                }

            }

            with(binding?.recyclerView) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = adapter

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}