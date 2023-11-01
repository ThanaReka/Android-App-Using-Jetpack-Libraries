package com.example.pennydrop.types

import com.example.pennydrop.game.AI

data class Player(
//class for tracking the player’s status in a game
val playerName: String = "",
    val isHuman: Boolean = true,
    val selectedAI: AI? = null
 	) {
    var pennies: Int = defaultPennyCount
    //how many pennies they currently have

    fun addPennies(count: Int = 1) {
        pennies += count
    }

    var isRolling: Boolean = false
    // whether or not they’re currently rolling

    fun penniesLeft(subtractPenny: Boolean = false) =
    (pennies - (if(subtractPenny) 1 else 0)) > 0

//    this function checks if a user’s penny count is greater than zero.
//    subtractPenny tells our method to take one away from a user’s penny count when checking
//    if they have any pennies left. This is done to check the future state for a user,
//    (i.e. how many pennies they’ll have after taking the current roll into consideration)

    // Instead of having to add an extra value like extraPennies or whatever
    // we’d want to call it to remove the extra penny, we can just inline the if...else statement.

    companion object {
        const val defaultPennyCount = 10
    }
}

// pennies and isRolling is kept outside of the constructor since neither
// is likely to be set to something other than its default value upon initialization

