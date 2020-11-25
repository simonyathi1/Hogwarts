package com.onesimo.nyathi.hogwarts

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.onesimo.nyathi.hogwarts.ui.viewmodel.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: CharactersViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeViewModel()
        viewModel.getCharacters()
    }

    private fun observeViewModel() {
        viewModel.characters.observe(this, Observer { characters ->
            hello_world.text = characters.size.toString()
        })
        viewModel.loading.observe (this, Observer {
            if (it){
                loading.visibility = View.VISIBLE
            }else{
                loading.visibility = View.GONE
            }
        })

        viewModel.error.observe (this, Observer {
            if (!it.isNullOrBlank()){
                error.visibility = View.VISIBLE
                Log.d("===error", it)
            }else{
                error.visibility = View.GONE
                Log.d("----error", it)
            }
        })
    }
}