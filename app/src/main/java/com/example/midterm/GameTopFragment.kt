package com.example.midterm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.media.MediaPlayer
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.midterm.databinding.FragmentGameTopBinding
import com.example.midterm.databinding.FragmentMainBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class GameTopFragment : Fragment() {
    private var _binding: FragmentGameTopBinding? = null
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
        _binding = FragmentGameTopBinding.inflate(inflater, container, false)
        val view =  inflater.inflate(R.layout.fragment_game_top, container, false)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        var  mediaPlayer: MediaPlayer? = null

        val okayButton = view.findViewById<Button>(R.id.okayButton)
        val guessEditText = view.findViewById<EditText>(R.id.guessEditText)
        val nameEditText = view.findViewById<EditText>(R.id.nameEditText)
        val enterGuessText = view.findViewById<TextView>(R.id.enterGuessText)
        val plusButton = view.findViewById<ImageButton>(R.id.plusButton)
        val minusButton = view.findViewById<ImageButton>(R.id.minusButton)
        val numOfTriesDisplay = view.findViewById<TextView>(R.id.numOfTriesDisplay)

        viewModel.generateSecretNum()
        println("Secret num: " + viewModel.secretNum)

        plusButton.setOnClickListener {
            if(guessEditText.getText().toString() != ""){
                var newNum = Integer.parseInt(guessEditText.getText().toString())
                newNum++
                guessEditText.setText(newNum.toString())
            }
        }

        minusButton.setOnClickListener {
            if(guessEditText.getText().toString() != "") {
                var newNum = Integer.parseInt(guessEditText.getText().toString())
                newNum--
                guessEditText.setText(newNum.toString())
            }
        }

        okayButton.setOnClickListener {
            //val guess = Integer.parseInt(binding.guessEditText.getText().toString())

            val guess = Integer.parseInt(guessEditText.getText().toString())
            var guessResult = viewModel.makeGuess(guess)

            //Putting the number of tries display in this fragment because I can't get it to update in the second fragment
            numOfTriesDisplay.text = viewModel.numOfTries.toString()

            if(guessResult == "<"){
                //Don't have time to mess with sound files
                //mediaPlayer = MediaPlayer.create(this, )


                enterGuessText.text = "Your guess is too low"

                //Toast is bugged and I have no idea why
//                val text = "Your guess is too low"
//                val duration = Toast.LENGTH_SHORT
//
//                val toast = Toast.makeText(this, text, duration)
//                toast.show()
            }
            else if(guessResult == ">"){
                enterGuessText.text = "Your guess is too high"

//                val text = "Your guess is too high"
//                val duration = Toast.LENGTH_SHORT
//
//                val toast = Toast.makeText(this, text, duration)
//                toast.show()
            }
            else{
                val playerName = nameEditText.getText().toString()
                val returnString = playerName + " score: " + viewModel.numOfTries.toString() + "  Play Another Game?"
                val action = GameFragmentDirections.actionGameFragmentToMainFragment(playerName, viewModel.numOfTries.toString())
                view.findNavController().navigate(action)
            }
        }

//        binding.okayButton.setOnClickListener {
//            println("Button pressed")
//            var guessResult = viewModel.makeGuess(binding.guessEditText.text.toString().toInt())
//            println(guessResult)
//        }

        return view
    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }
}