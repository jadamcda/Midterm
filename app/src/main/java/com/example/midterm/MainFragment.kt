package com.example.midterm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.midterm.databinding.ActivityMainBinding
import com.example.midterm.databinding.FragmentMainBinding

private var firstRun = true

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private var param1: Boolean? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getBoolean("firstRun")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val playButton = view.findViewById<Button>(R.id.playButton)
        val highScoreButton = view.findViewById<Button>(R.id.highScoresButton)
        val welcomeText = view.findViewById<TextView>(R.id.welcomeText)

        if(firstRun == false){
            val playerName = MainFragmentArgs.fromBundle(requireArguments()).playerName
            val score = MainFragmentArgs.fromBundle(requireArguments()).score
            val returnString = playerName + " score: " + score + " Play Another Game?"
            welcomeText.text = returnString
        }

        playButton.setOnClickListener {
            firstRun = false

            view.findNavController()
                .navigate(R.id.action_mainFragment_to_gameFragment)
        }

        highScoreButton.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_mainFragment_to_highScoreFragment)
        }

        //Using binding for the button is bugged for some reason?
        //Actually it's not working for anything and I don't know why :/
//        binding.playButton.setOnClickListener {
//            println("Button pressed")
//            view.findNavController()
//                .navigate(R.id.action_mainFragment_to_gameFragment)
//        }
//
//        binding.highScoresButton.setOnClickListener{
//            val action = MainFragmentDirections.actionMainFragmentToHighScoreFragment()
//            view.findNavController().navigate(action)
//        }

        return view
    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }
}