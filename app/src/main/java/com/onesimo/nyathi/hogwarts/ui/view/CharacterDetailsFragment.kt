package com.onesimo.nyathi.hogwarts.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.onesimo.nyathi.hogwarts.R
import com.onesimo.nyathi.hogwarts.data.MovieCharacter
import com.onesimo.nyathi.hogwarts.ui.viewmodel.CharactersViewModel
import com.onesimo.nyathi.hogwarts.ui.viewmodel.HogwartsViewModel
import timber.log.Timber

class CharacterDetailsFragment : Fragment() {

    private val viewModel: CharactersViewModel by activityViewModels()
    private val hogwartsViewModel: HogwartsViewModel by activityViewModels()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        getData()
    }

    private fun getData() {
        if (hogwartsViewModel.characters.isNullOrEmpty()) {
            viewModel.getCharacters()
            Timber.d("===Remote")
        } else {
            displayCharacters(hogwartsViewModel.characters!!)
            Timber.d("===Local")
        }
    }

    private fun displayCharacters(characters: List<MovieCharacter>) {

    }

    private fun observeViewModel() {
        viewModel.characters.observe(viewLifecycleOwner, Observer { characters ->
            hogwartsViewModel.characters = characters
            displayCharacters(characters)
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer {

        })

        viewModel.error.observe(viewLifecycleOwner, Observer {

        })
    }
}