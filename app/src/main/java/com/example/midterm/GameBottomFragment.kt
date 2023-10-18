package com.example.midterm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.midterm.databinding.FragmentGameBottomBinding
import com.example.midterm.databinding.FragmentMainBinding
import kotlin.properties.Delegates

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class GameBottomFragment : Fragment() {
    private var _binding: FragmentGameBottomBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: GameViewModel

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBottomBinding.inflate(inflater, container, false)
        val view =  inflater.inflate(R.layout.fragment_game_bottom, container, false)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)


        //Pretty sure I need to be using an observer structure in here but can't figure out how to do that for the life of me

        return view
    }



    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }
}