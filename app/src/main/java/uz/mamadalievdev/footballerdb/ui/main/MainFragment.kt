package uz.mamadalievdev.footballerdb.ui.main

import android.view.View
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import uz.mamadalievdev.footballerdb.R
import uz.mamadalievdev.footballerdb.databinding.FragmentMainBinding
import uz.mamadalievdev.footballerdb.ui.BaseFragment
import uz.mamadalievdev.footballerdb.ui.adapters.FootballerAdapter

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    val viewModel: MainViewModel by viewModels()
    val adapter by lazy {
        FootballerAdapter()
    }

    override fun onViewCreate() {
        binding.searchedList.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false)
        binding.searchedList.adapter = adapter

        adapter.setItemClickListener { player, biography, image ->
            val bundle = bundleOf("PLAYER" to player,
                "BIOGRAPHY" to biography,
                "IMAGE" to image)
            navController.navigate(R.id.action_mainFragment_to_infoFragment, bundle)
        }

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.getSearchedFootballers(query = it) }
                viewModel.searchedArticlesLiveData.observe(viewLifecycleOwner) {
                    if (it.isNullOrEmpty()) {
                        binding.notFoundLayout.visibility = View.VISIBLE
                        binding.searchedList.visibility = View.GONE
                    } else {
                        binding.notFoundLayout.visibility = View.GONE
                        binding.searchedList.visibility = View.VISIBLE
                        adapter.setArticles(it)
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}