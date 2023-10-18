package com.example.midterm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.midterm.databinding.FragmentHighScoreBinding
import com.example.midterm.databinding.FragmentMainBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HighScoreFragment : Fragment() {
    private var _binding: FragmentHighScoreBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentHighScoreBinding.inflate(inflater, container, false)
        val view = inflater.inflate(R.layout.fragment_high_score, container, false)

        val backButton = view.findViewById<Button>(R.id.backButton)

        backButton.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_highScoreFragment_to_mainFragment)
        }

        return view
    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }
}