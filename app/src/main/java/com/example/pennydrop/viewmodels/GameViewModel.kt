package com.example.pennydrop.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pennydrop.game.GameHandler
import com.example.pennydrop.game.TurnEnd
import com.example.pennydrop.game.TurnResult
import com.example.pennydrop.types.Player
import com.example.pennydrop.types.Slot
import com.example.pennydrop.types.clear
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
		  var clearText	= false

         	    fun startGame(playersForNewGame: List<Player>) {
        	        this.players = playersForNewGame
					// More logic will be added here later.
					this.currentPlayer.value = this.players.firstOrNull().apply {
						this?.isRolling = true
					}

					canRoll.value = true
					canPass.value = false

					// since slots is a MutableLiveData object, we need to call .value to get the actual List<Slot>.
					// also, every value that comes out of a LiveData object will be nullable,
					// which is why the next clear call (a function declared inside of Slot.kt) uses the null-safe syntax

					slots.value?.clear()
					slots.notifyChange()
					currentTurnText.value = "The game has begun!\n"
					currentStandingsText.value = generateCurrentStandings(this.players)
        	    }

//	slots: the six Slot objects with their current status.
//	currentPlayer: the player that’s currently rolling.
//	canRoll: whether or not the current player can roll.
//	canPass: whether or not the current player can pass (remember, players must roll at least once).
//	currentTurnText: the info text on the bottom of the screen.
//	currentStandingsText: the text of scores in the current game.

	fun roll() {
		 	  // Implementing later
		slots.value?.let { currentSlots ->
			 	    // Comparing against true saves us a null check
			 	    val currentPlayer = players.firstOrNull { it.isRolling }
			 	    if (currentPlayer != null && canRoll.value == true) {
			 	      updateFromGameHandler(
			 	          GameHandler.roll(players, currentPlayer, currentSlots)
			 	      )
			 	    }
			 	  }
		 	}

	 	fun pass() {
		 	  // Implementing later
			val currentPlayer = players.firstOrNull { it.isRolling }
			 	  if (currentPlayer != null && canPass.value == true) {
				 	    updateFromGameHandler(GameHandler.pass(players, currentPlayer))
				 	  }
		 	}

	// the roll and pass functions get the current player (the first with isRolling as true),
	// make sure the resulting Player? object is not null, check if the requested move is valid,
	// call the GameHandler function which will then be sent into the updateFromGameHandler function.

	//the roll function will also get the game’s current slots and send those along to the GameHandler

	// since canRoll.value and canPass.value are both Boolean? objects (nullable Boolean objects)
	// we compare them against true (instead of doing a null check)
	// if you get a value of false or null then we skip going to the GameHandler

	private fun <T> MutableLiveData<List<T>>.notifyChange() {
		 	  this.value = this.value
		 	}

	// since nothing happens when trying to update something inside of .value,
	//(although changing .value of a LiveData object, does usually automatically send an event to all listeners with the update)
	// the .notifyChange extension function is used to force that update to occur

	private fun generateCurrentStandings(
	 	  players: List<Player>,
	 	  headerText: String = "Current Standings:"
	 	) =
	 	  players.sortedBy { it.pennies }.joinToString(
	 	    separator = "\n",
	 	    prefix = "$headerText\n"
	 	  ) {
		 	    "\t${it.playerName} - ${it.pennies} pennies"
		 	  }

	//	the generateCurrentStandings function brings in a List<Player>,
	//	sorts it by the player’s current penny count,
	//	then uses joinToString to bring everything together using the newline
	// 	character (\n)  to separate each item and a header prefix value.

	private fun updateFromGameHandler(result: TurnResult) {
		 	  if (result.currentPlayer != null) {
			 	    currentPlayer.value?.addPennies(result.coinChangeCount ?: 0)
			 	    currentPlayer.value = result.currentPlayer
			 	    this.players.forEach { player ->
			 	        player.isRolling = result.currentPlayer == player
			 	    }
			 	  }
		 	  if (result.lastRoll != null) {
			 	    slots.value?.let { currentSlots ->
			 	        updateSlots(result, currentSlots, result.lastRoll)
			 	    }
			 	  }

		 	  currentTurnText.value = generateTurnText(result)
		 	  currentStandingsText.value = generateCurrentStandings(this.players)

		 	  canRoll.value = result.canRoll
		 	  canPass.value = result.canPass

		 	  if (!result.isGameOver && result.currentPlayer?.isHuman == false) {
			 	    canRoll.value = false
			 	    canPass.value = false
				  	playAITurn()
			 	  }
		 	}

	// the updateFromGameHandler method will be called in the roll() and pass() functions
	// to set the LiveData values accordingly

	// this function will add/remove the current player’s pennies, mark the proper player as rolling,
	// update the slots, generate turn and standings text, change canRoll/canPass flags,
	// and finally handle game-over scenarios if applicable

	// the MutableLiveData<Player> value from the TurnResult just gets updated
	// from both the player’s name and their current coin count from the currentPlayer

	private fun playAITurn() {
		viewModelScope.launch {
			delay(1000)
			slots.value?.let { currentSlots ->
				val currentPlayer = players.firstOrNull { it.isRolling }

				if(currentPlayer != null && !currentPlayer.isHuman) {
					GameHandler.playAITurn(
						players,
						currentPlayer,
						currentSlots,
						canPass.value == true
					)?.let { result ->
						updateFromGameHandler(result)
					}
				}
			}
		}
	}

	// this Kotlin coroutines function is used to handle the player
	// being run by the app if we’re sure it’s not human

	// delay for a second (1000 milliseconds) between AI moves
	// to make it feel more like a real person is playing the game

	// the call to GameHandler.playAITurn is similar to roll and pass
	// but we're using a null-safe let call to handle scenarios
	// where we get back null (i.e. where we try to play an AI turn with a player
	// that’s missing a valid selectedAI property) rather than a TurnResult instance

	private fun updateSlots(
	 	    result: TurnResult,
	 	    currentSlots: List<Slot>,
	 	    lastRoll: Int
	 	) {
		 	  if (result.clearSlots) {
			 	    currentSlots.clear()
			 	  }

		 	  currentSlots.firstOrNull { it.lastRolled }?.apply { lastRolled = false }

		 	  currentSlots.getOrNull(lastRoll - 1)?.also { slot ->
			 	      if (!result.clearSlots && slot.canBeFilled) slot.isFilled = true

			 	      slot.lastRolled = true
			 	  }

		 	  slots.notifyChange()
		 	}

	// the updateSlots function brings in the TurnResult plus the current state of the Slots
	// and the last roll to update everything we need from a UI perspective

	// if either firstOrNull or getOrNull fails, we get back null
	// and the attached blocks are skipped (due to the ?.apply and ?.also calls)

	//.also is similar to .let but, .also { ... } returns this, which is the object .also { ... } was called on

	private fun generateTurnText(result: TurnResult): String {

		if (clearText) currentTurnText.value = ""
		clearText = result.turnEnd != null

		 	  val currentText = currentTurnText.value ?: ""
		 	  val currentPlayerName = result.currentPlayer?.playerName ?: "???"
		 	  return when {
				  result.isGameOver -> """
 				  |Game Over!
				  |$currentPlayerName is the winner!
				  |${generateCurrentStandings(this.players, "Final Scores:\n")}
				  }}
 	  				""".trimMargin()//Game's over, let's get a summary
				  result.turnEnd == TurnEnd.Bust -> "${result.previousPlayer?.playerName} rolled a ${result.lastRoll}.  They collected ${result.coinChangeCount} pennies for a total of ${result.previousPlayer?.pennies}.\n$currentText"//Player busted, got some pennies
				  result.turnEnd == TurnEnd.Pass -> "${result.previousPlayer?.playerName} passed.  They currently have ${result.previousPlayer?.pennies} pennies.\n$currentText"//Player passed.
				  result.lastRoll != null -> "$currentText\n$currentPlayerName rolled a ${result.lastRoll}." //Roll text
				  else -> ""
			 	  }
		 	}

	// Three primary pieces of the function:
	// clear out the existing text if the previous event was the end of a turn,
	// flag if the next set of text should be on a cleared view,
	// and display the next set of text.

}