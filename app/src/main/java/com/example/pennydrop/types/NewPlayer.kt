package com.example.pennydrop.types

import androidx.databinding.ObservableBoolean
import com.example.pennydrop.game.AI

data class NewPlayer(
//	The NewPlayer class is intended to figure out who’s in the game and who they are
    var playerName: String = "",
    val isHuman: ObservableBoolean = ObservableBoolean(true),
    val canBeRemoved: Boolean = true,
    val canBeToggled: Boolean = true,
    var isIncluded: ObservableBoolean = ObservableBoolean(!canBeRemoved),

//	using an ObservableBoolean class to handle any changes by enabling two-way binding,

//            defaulting isIncluded to true if a user can’t be removed from the game,
//            otherwise they default to false.

    var selectedAIPosition: Int = -1
//    var selectedAIPosition will track the index of the selected AI
//    (retrieved from AI.basicAI or AI.getBasicAI)

)

{

    fun selectedAI() = if (!isHuman.get()) {
    	    AI.basicAI.getOrNull(selectedAIPosition)
    	  } else {
    	    null
    	  }

    //this function converts NewPlayer into a Player to create our List<Player>,
    // by getting the player’s name (or name of the AI, if applicable),
    //and then mapping a few more values from NewPlayer
    fun toPlayer() = Player(
     	  if (this.isHuman.get()) {
     	    this.playerName
     	  } else {
     	     (this.selectedAI()?.name ?: "AI")
     	  },
    	  this.isHuman.get(),
     	  this.selectedAI()
     	)
}
