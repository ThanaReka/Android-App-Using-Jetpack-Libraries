package com.example.pennydrop.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.pennydrop.R
import com.example.pennydrop.databinding.FragmentPickPlayersBinding
import com.example.pennydrop.viewmodels.GameViewModel
import com.example.pennydrop.viewmodels.PickPlayersViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PickPlayersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PickPlayersFragment : Fragment() {
//     TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private val pickPlayersViewModel
     	  by activityViewModels<PickPlayersViewModel>()

    private val gameViewModel
            by activityViewModels<GameViewModel>()

    //bring in an instance of GameViewModel (just as we did with PickPlayersViewModel)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_pick_players, container, false)
        val binding = FragmentPickPlayersBinding
            .inflate(inflater, container, false)
            .apply { this.vm = pickPlayersViewModel

                 //transfer over the players when someone clicks the Play button by mapping
                // each NewPlayer to a Player via the toPlayer function we created in NewPlayer data class.

                this.buttonPlayGame.setOnClickListener {
                	          gameViewModel.startGame(
                	            pickPlayersViewModel.players.value
                	            ?.filter { newPlayer ->
                	              newPlayer.isIncluded.get()
                	            }?.map { newPlayer ->
                	              newPlayer.toPlayer()
                	            } ?: emptyList()
                	          )

                    //need to use value call since players is LiveData
                    //also need to have a fallback (using an empty listOf) in case the value
                    // returns a null instance of whatever it contains

                	          findNavController().navigate(R.id.gameFragment)
                	        }

            // assigning pickPlayersViewModel to the vm <variable> within fragment_pick_players.
            // allows the PickPlayersViewModel instance to be used in our layout,
            // (including grabbing individual players from the ViewModel and assigning them to player items)
            }

        return binding.root


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PickPlayersFragment.
         */
         // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PickPlayersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}