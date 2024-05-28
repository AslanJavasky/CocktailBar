package com.aslanjavasky.surftech.cocktailbar.presentation.newCocktailFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aslanjavasky.surftech.cocktailbar.R
import com.aslanjavasky.surftech.cocktailbar.databinding.FragmentNewCocktailBinding
import com.aslanjavasky.surftech.cocktailbar.presentation.newCocktailFragment.adapter.IngredientsListAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewCocktailFragment : Fragment() {


    private var _binding: FragmentNewCocktailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NewCocktailViewModel by viewModels()

    private val ingredientsListAdapter = IngredientsListAdapter()

     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewCocktailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareIngredientsRecyclerView()
        prepareButtons()
        observeState()
    }

    private fun prepareIngredientsRecyclerView() {
        ingredientsListAdapter.onIngredientClick = { item ->
            val newList = ingredientsListAdapter.currentList.toList().filter { it != item }
            ingredientsListAdapter.submitList(newList)
        }
        ingredientsListAdapter.onAddButtonClick = {
            showIngredientDialog()
        }
        with(binding.recyclerViewIngredients) {
            this.adapter = ingredientsListAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

    }

    private fun prepareButtons() {
        with(binding) {
            buttonCancel.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            buttonSave.setOnClickListener {
                val name = editTextCocktailName.text.toString().trim()
                val description = editTextDescription.text.toString().trim()
                val ingredients = ingredientsListAdapter.currentList.map { it.description }
                val recipe = editTextRecipe.text.toString().trim()

                viewModel.addNewCocktail(
                    name = name,
                    description = description,
                    ingredients = ingredients,
                    recipe = recipe
                )
            }
        }
    }

    private fun observeState() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                NewCocktailScreenState.Initial -> {}
                is NewCocktailScreenState.InputError -> {
                    binding.editTextCocktailName.error =
                        if (state.missingName) getString(R.string.add_cocktail_name) else null

                    if (state.missingIngredients) {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.add_ingredients), Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                is NewCocktailScreenState.AddedSuccessfully -> {
                    parentFragmentManager.popBackStack()
                }
            }
        }
    }

    private fun showIngredientDialog() {
        val dialogFragment = IngredientDialogFragment()
        dialogFragment.show(parentFragmentManager, null)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() = NewCocktailFragment()

        const val TAG = "new_cocktail_fragment"
    }
}