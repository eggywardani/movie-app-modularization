package com.eggy.movieapp.favorite.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.eggy.movieapp.core.ui.MovieAdapter
import com.eggy.movieapp.detail.DetailActivity
import com.eggy.movieapp.favorite.databinding.FragmentFavoriteBinding
import com.eggy.movieapp.favorite.di.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {


    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (activity != null) {
            loadKoinModules(favoriteModule)
            val adapter = MovieAdapter()


            adapter.onItemClick = {
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, it)
                startActivity(intent)
            }

            favoriteViewModel.movies.observe(viewLifecycleOwner) { movie ->
                adapter.setData(movie)
                binding?.viewEmpty?.visibility =
                    if (movie.isNotEmpty()) View.GONE else View.VISIBLE

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