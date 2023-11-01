package com.example.pennydrop.fragments

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.pennydrop.databinding.FragmentGameBinding
import com.example.pennydrop.viewmodels.GameViewModel

class GameFragment : Fragment() {

    private val gameViewModel
            by activityViewModels<GameViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_game, container, false)

        val binding = FragmentGameBinding
            .inflate(inflater, container, false)
            . apply {
                vm = gameViewModel

                textCurrentTurnInfo.movementMethod =
                    ScrollingMovementMethod()

                lifecycleOwner = viewLifecycleOwner
                // ensure our LiveData will update properly by setting the LiveData inside GameViewModel
                // to follow the same life cycle (creation/disposal/and so on) as the entered
                // life-cycle owner (i.e. the GameFragment)
        }

        return binding.root

    }

}