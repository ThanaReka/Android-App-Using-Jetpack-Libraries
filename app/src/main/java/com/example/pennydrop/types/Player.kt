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

    companion object {
        const val defaultPennyCount = 10
    }
}

// pennies and isRolling is kept outside of the constructor since neither
// is likely to be set to something other than its default value upon initialization

