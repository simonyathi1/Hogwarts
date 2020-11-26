package com.onesimo.nyathi.hogwarts.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.onesimo.nyathi.hogwarts.R
import com.onesimo.nyathi.hogwarts.data.MovieCharacter
import com.onesimo.nyathi.hogwarts.ui.view.adapter.CharactersAdapter
import com.onesimo.nyathi.hogwarts.ui.viewmodel.CharactersViewModel
import com.onesimo.nyathi.hogwarts.ui.viewmodel.HogwartsViewModel
import kotlinx.android.synthetic.main.error_screen.*
import kotlinx.android.synthetic.main.fragment_characters.*

class CharactersFragment : Fragment() {

    private val viewModel: CharactersViewModel by activityViewModels()
    private val hogwartsViewModel: HogwartsViewModel by activityViewModels()
    private lateinit var adapter: CharactersAdapter

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
        setupListeners()
        getData()
    }

    private fun getData() {
        if (hogwartsViewModel.characters.isNullOrEmpty()) {
            viewModel.getCharacters()
        } else {
            displayCharacters(hogwartsViewModel.characters!!)
        }
    }

    private fun displayCharacters(characters: List<MovieCharacter>) {
        setAdapter(characters)
    }

    private fun setAdapter(characters: List<MovieCharacter>) {
        adapter = CharactersAdapter(characters) {
            hogwartsViewModel.selectedCharacter = it
            val action =
                CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailsFragment()
            findNavController().navigate(action)
        }
        characters_list.adapter = adapter
    }

    private fun setupListeners() {
        error_retry_button.setOnClickListener {
            getData()
        }
    }

    private fun observeViewModel() {
        viewModel.characters.observe(viewLifecycleOwner, Observer { characters ->
            hogwartsViewModel.characters = characters
            displayCharacters(characters)
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                loading_indicator.visibility = View.VISIBLE
                error_screen.visibility = View.GONE
            } else {
                loading_indicator.visibility = View.GONE
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                error_screen.visibility = View.VISIBLE
                error_text.text = it
            } else {
                error_screen.visibility = View.GONE
            }
        })
    }
}