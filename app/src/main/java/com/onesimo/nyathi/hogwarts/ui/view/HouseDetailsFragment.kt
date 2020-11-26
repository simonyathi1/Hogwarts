package com.onesimo.nyathi.hogwarts.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.onesimo.nyathi.hogwarts.R
import com.onesimo.nyathi.hogwarts.data.House
import com.onesimo.nyathi.hogwarts.data.MovieCharacter
import com.onesimo.nyathi.hogwarts.ui.view.adapter.CharactersAdapter
import com.onesimo.nyathi.hogwarts.ui.viewmodel.CharactersViewModel
import com.onesimo.nyathi.hogwarts.ui.viewmodel.HogwartsViewModel
import com.onesimo.nyathi.hogwarts.util.setImageFromUrl
import kotlinx.android.synthetic.main.error_screen.*
import kotlinx.android.synthetic.main.fragment_house_details.*
import kotlinx.android.synthetic.main.fragment_houses.error_screen
import kotlinx.android.synthetic.main.fragment_houses.loading_indicator


class HouseDetailsFragment : Fragment() {

    private val viewModel: CharactersViewModel by activityViewModels()
    private val hogwartsViewModel: HogwartsViewModel by activityViewModels()

    private lateinit var house: House
    private lateinit var adapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_house_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        getData()
    }

    private fun getData() {
        hogwartsViewModel.selectedHouse?.let { house = it }
        if (hogwartsViewModel.characters.isNullOrEmpty()) {
            viewModel.getCharacters()
        } else {
            setViewData(hogwartsViewModel.characters!!)
        }
    }

    private fun setViewData(characters: List<MovieCharacter>) {
        val houseCharacters = characters.filter { it.house == house.name }
        setupButton()
        setAdapter(houseCharacters)
        house_name.text = house.name
        house_details.text = house.history
        context?.let {
            setImageFromUrl(house.emblem, house_image, it)
            house_banner.background = AppCompatResources.getDrawable(
                it,
                R.drawable.background
            )
        }
    }

    private fun setAdapter(characters: List<MovieCharacter>) {
        adapter = CharactersAdapter(characters) {
            hogwartsViewModel.selectedCharacter = it
            val action =
                HouseDetailsFragmentDirections.actionHouseDetailsFragmentToCharacterDetailsFragment()
            findNavController().navigate(action)
        }
        characters_list.adapter = adapter
    }

    private fun setupButton() {
        toggleButtonText()
        view_toggle_button.setOnClickListener {
            if (hogwartsViewModel.isDisplayingHouseCharacters) {
                hogwartsViewModel.isDisplayingHouseCharacters = false
                toggleButtonText()
            } else {
                hogwartsViewModel.isDisplayingHouseCharacters = true
                toggleButtonText()
            }
        }
    }

    private fun toggleButtonText() {
        if (hogwartsViewModel.isDisplayingHouseCharacters) {
            view_toggle_button.text = getString(R.string.view_details)
            characters_list.visibility = View.VISIBLE
            house_details_scrollview.visibility = View.GONE
        } else {
            view_toggle_button.text = getString(R.string.view_characters)
            characters_list.visibility = View.GONE
            house_details_scrollview.visibility = View.VISIBLE
        }
    }

    private fun observeViewModel() {
        viewModel.characters.observe(viewLifecycleOwner, Observer { characters ->
            hogwartsViewModel.characters = characters
            setViewData(characters)
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                loading_indicator.visibility = View.VISIBLE
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