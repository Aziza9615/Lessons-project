package com.example.firstapp.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstapp.R
import com.example.firstapp.model.Publication
import com.example.firstapp.ui.main.MainRepository
import com.example.firstapp.ui.publication.PublicationAdapter
import com.example.firstapp.ui.publication.RequestResult
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoriteFragment : Fragment(), PublicationAdapter.ClickListener, RequestResult{

    lateinit var adapter: PublicationAdapter
    private lateinit var repository: MainRepository
    private var publicationArray = mutableListOf<Publication>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    private fun setupRecyclerView() {
        adapter = PublicationAdapter(this, requireActivity())
        rv_fav.layoutManager = LinearLayoutManager(requireContext())
        rv_fav.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        repository = MainRepository(this)
        repository.fetchFavoritePublications()
    }

    override fun onFavoriteClick(item: Publication, position: Int) {
        for (it in publicationArray) {
            if (it == item) {
                it.isFavorite = !it.isFavorite
                if (it.isFavorite) it.countOfFavorite += 1
                else it.countOfFavorite -= 1
                repository.updateChangeFavoriteState(it)
                adapter.removeItem(position)
            }
        }
    }

    override fun onCommentClick(item: Publication) {
        TODO("Not yet implemented")
    }

    override fun onDirectClick(item: Publication) {
        TODO("Not yet implemented")
    }

    override fun onFailure(t: Throwable) {
        TODO("Not yet implemented")
    }

    override fun <T> onSuccess(result: T) {
        publicationArray = result as MutableList<Publication>
        adapter.addItems(publicationArray)
    }
}

