package com.aslanjavasky.surftech.cocktailbar.presentation.cocktailListFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aslanjavasky.surftech.cocktailbar.R
import com.aslanjavasky.surftech.cocktailbar.databinding.FragmentCocktailsListBinding
import com.aslanjavasky.surftech.cocktailbar.presentation.cocktailListFragment.adapter.CocktailsListAdapter
import com.aslanjavasky.surftech.cocktailbar.presentation.newCocktailFragment.NewCocktailFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CocktailsListFragment : Fragment() {

    @Inject
    lateinit var VMFactory: CocktailsListViewModelFactory

    private val viewModel: CocktailsListViewModel by viewModels{
        VMFactory
    }

    private var _binding: FragmentCocktailsListBinding? = null
    private val binding get() = _binding!!

    private val cocktailsListAdapter = CocktailsListAdapter()

    companion object {

        @JvmStatic
        fun newInstance() = CocktailsListFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCocktailsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecyclerView()
        prepareNewCocktailButton()
        observeState()
        viewModel.getAllCocktails()
    }

    private fun prepareRecyclerView() {
        cocktailsListAdapter.onItemClick = { cocktail ->
            Toast.makeText(
                requireContext(),
                "Клик на коктейль с id ${cocktail.id}",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.recyclerViewCocktails.adapter = cocktailsListAdapter
    }

    private fun prepareNewCocktailButton() {
        binding.fabNewCocktail.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, NewCocktailFragment.newInstance(), null)
                .addToBackStack(NewCocktailFragment.TAG)
                .commit()
        }
    }

    private fun observeState() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CocktailsListState.Initial -> {}
                is CocktailsListState.EmptyList -> {
                    setEmptyListViewsVisibility(View.VISIBLE)
                    with(binding) {
                        textViewTitle.visibility = View.GONE
                    }
                }

                is CocktailsListState.Content -> {
                    setEmptyListViewsVisibility(View.GONE)
                    with(binding) {
                        textViewTitle.visibility = View.VISIBLE
                    }
                    cocktailsListAdapter.submitList(state.data)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setEmptyListViewsVisibility(visibility: Int) {
        with(binding) {
            imageViewNoCocktails.visibility = visibility
            textViewNoCocktails.visibility = visibility
            imageViewArrow.visibility = visibility
            textViewHint.visibility = visibility
        }
    }
}