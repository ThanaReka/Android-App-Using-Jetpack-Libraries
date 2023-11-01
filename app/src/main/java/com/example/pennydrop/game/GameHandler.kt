package com.example.pennydrop.game

import com.example.pennydrop.types.Player
import com.example.pennydrop.types.Slot
import kotlin.random.Random

// GameHandler is an object declaration (rather than a class) that
//  contains with functions returning a TurnResult? object, which contains game statuses/information
// that will be used to send info back to the GameViewModel

object GameHandler {
    fun roll(
        players: List<Player>,
        currentPlayer: Player,
        slots: List<Slot>
     	  ): TurnResult =
		rollDie().let { lastRoll ->
			slots.getOrNull(lastRoll - 1)?.let { slot ->
				// TurnResult is created here
				if (slot.isFilled) {
					// Player busts, play continues to next player
					TurnResult(
						lastRoll,
						coinChangeCount = slots.count { it.isFilled },
						clearSlots = true,
						turnEnd = TurnEnd.Bust,
						previousPlayer = currentPlayer,
						currentPlayer = nextPlayer(players, currentPlayer),
						playerChanged = true,
						canRoll = true,
						canPass = false
					)
				} else {
					if (!currentPlayer.penniesLeft(true)) {
						// Player wins
						TurnResult(
							lastRoll,
							currentPlayer = currentPlayer,
							coinChangeCount = -1,
							isGameOver = true,
							turnEnd = TurnEnd.Win,
							canRoll = false,
							canPass = false
						 	    )
					} else {
						// Game continues
						TurnResult(
							lastRoll,
						    currentPlayer = currentPlayer,
							canRoll = true,
							canPass = true,
							coinChangeCount = -1
							)
					}
				}
			} ?: TurnResult(isGameOver = true)
		}
		// the let function on rollDie is used as a way to have lastRoll be available to us for use
		// in our next few lines of code (rather than using it as a null check)
		// this is the same thing as using  lastRoll = rollDie()

		// the rolled slot is grabbed using the getOrNull function,
		// which either returns the slot at the given index or null

		// use an else-if block since there are only two primary outcomes for a roll:
		// a player rolls the number of an empty slot (including 6) or they roll an already-filled slot.
		// If they roll an empty slot, they put a penny in that slot (unless they roll a six, which goes into the reservoir),
		// and we see if they won the game.

		// penniesLeft is a helper function from the Player class used to see if the current player has won

		// lastly, the selected Slot is used to generate the TurnResult.
		// if slot is null here, we use Elvis operator (?:) to fall back to a default TurnResult object

		// the inside block of the roll function is an if...else block which returns TurnResult objects
		//(without actually having to write out return statements) since it's inside of a lambda function,
		// which (like inside a let), will return the last value in the block including values/variables & any expressions
		// and in Kotlin if..else blocks are actually expressions return a value themselves instead of just causing side effects like in other languages

     	  fun pass(players: List<Player>, currentPlayer: Player) =
	 	  TurnResult(
	 	      previousPlayer = currentPlayer,
	 	      currentPlayer = nextPlayer(players, currentPlayer),
	 	      playerChanged = true,
	 	      turnEnd = TurnEnd.Pass,
	 	      canRoll = true,
	 	      canPass = false
	)
	//this function addresses the scenario where the current player has rolled at least once on their turn,
	// and decides to pass to the next player.
	// still returning a TurnResult, marking the next player as able to roll but not pass
	// (since it’s the first roll of their turn), and we’re grabbing the next player in the game

     	  private fun rollDie(sides: Int = 6) = Random.nextInt(1, sides + 1)

		//rollDie function takes in a number of sides and gives us a random number from 1 until
		// our entered number of sides (plus one,since the function is non-inclusive upper bound)

     	  private fun nextPlayer(
			  players: List<Player>,
			   	  currentPlayer: Player): Player? {
			  val currentIndex = players.indexOf(currentPlayer)
			  val nextIndex = (currentIndex + 1) % players.size

			  return players[nextIndex]
		  }

	fun playAITurn(
		players: List<Player>,
		currentPlayer: Player,
		slots: List<Slot>,
		canPass: Boolean = false
	): TurnResult? =
	 	  currentPlayer.selectedAI?.let { ai ->
		 	    if (!canPass || ai.rollAgain(slots)) {
			 	      roll(players, currentPlayer, slots)
			 	    } else {
			 	      pass(players, currentPlayer)
			 	    }

	}

//	This method is used:
//
//	Get the index of the current player in the list of players.
//	Figure out the next player’s index (which may wrap around, and hence why modulo % is used ).
//	Return the next player.
}

