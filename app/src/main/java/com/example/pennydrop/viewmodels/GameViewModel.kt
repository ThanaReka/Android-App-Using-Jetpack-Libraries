package com.example.pennydrop.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pennydrop.types.Player
import com.example.pennydrop.types.Slot

class GameViewModel : ViewModel() {

    private var players: List<Player> = emptyList()
	//This function gives us an empty instance of whatever type of List we’re expecting.
	//We could have just used listOf here, but emptyList is slightly more efficient and makes it clearer
	//that we wanted an empty list and didn’t just forget to populate a listOf call.

		  val slots =
		      MutableLiveData(
		          (1..6).map { slotNum -> Slot(slotNum, slotNum != 6) }
		      )

		  val currentPlayer = MutableLiveData<Player?>()

		  val canRoll = MutableLiveData(false)
		  val canPass = MutableLiveData(false)

		  val currentTurnText = MutableLiveData("")
		  val currentStandingsText = MutableLiveData("")

         	    fun startGame(playersForNewGame: List<Player>) {
        	        this.players = playersForNewGame
					// More logic will be added here later.
        	    }

//	slots: the six Slot objects with their current status.
//	currentPlayer: the player that’s currently rolling.
//	canRoll: whether or not the current player can roll.
//	canPass: whether or not the current player can pass (remember, players must roll at least once).
//	currentTurnText: the info text on the bottom of the screen.
//	currentStandingsText: the text of scores in the current game.

	fun roll() {
		 	  // Implementing later
		 	}

	 	fun pass() {
		 	  // Implementing later
		 	}
}