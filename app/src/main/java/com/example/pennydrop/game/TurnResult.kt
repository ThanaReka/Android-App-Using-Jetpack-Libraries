package com.example.pennydrop.game

import com.example.pennydrop.types.Player

//this class contains everything the GameViewModel needs to know
// after a turn (or for the start of the game).
data class TurnResult(
    val lastRoll: Int? = null,
    val coinChangeCount: Int? = null,
    val previousPlayer: Player? = null,
 	val currentPlayer: Player? = null,
 	val playerChanged: Boolean = false,
 	val turnEnd: TurnEnd? = null,
 	val canRoll: Boolean = false,
 	val canPass: Boolean = false,
 	val clearSlots: Boolean = false,
 	val isGameOver: Boolean = false

)

 	enum class TurnEnd { Pass, Bust, Win }

//TurnEnd is an enum class that contains the three ways a turn can be over:
// a user passes, a user busts (and takes the pennies on the board), and a user wins

// coinChangeCount is the number of coins going to the player, which is determined by how many slots are already filled