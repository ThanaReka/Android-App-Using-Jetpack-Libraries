package com.example.pennydrop.game

import com.example.pennydrop.types.Slot

data class AI(
	val name: String,
	val rollAgain: (slots: List<Slot>) -> Boolean
	){

    //a toString method that returns the name property
    override fun toString() = name

	//the above is equivalent to:
	// 	override fun toString(): String {
	// 	  return name
	// 	}

    companion object {
         	    @JvmStatic
         	    val basicAI = listOf(
					AI("TwoFace") { slots -> slots.fullSlots() < 3 || (slots.fullSlots() == 3 && coinFlipIsHeads()) },
					AI("No Go Noah") { slots -> slots.fullSlots() == 0 },
					AI("Bail Out Beulah") { slots -> slots.fullSlots() <= 1 },
					AI("Fearful Fred") { slots -> slots.fullSlots() <= 2 },
					AI("Even Steven") { slots -> slots.fullSlots() <= 3 },
					AI("Riverboat Ron") { slots -> slots.fullSlots() <= 4 },
					AI("Sammy Sixes") { slots -> slots.fullSlots() <= 5 },
					AI("Random Rachael") { coinFlipIsHeads() }
         	    )
         	  }
    //eight AI players to be used in the Spinner
	//The companion object syntax denotes an object that is associated with a given class.
    //There’s only one instance of this object per class (meaning it’s a singleton) and
	//we can get basicAI elsewhere by referencing AI.basicAI

	//the @JvmStatic annotation tells the compiler to create an additional static get method for basicAI,
	//which will allow us to reference basicAI in a static way within a player list item,
}

	 fun List<Slot>.fullSlots(): Int = this.count {
		 it.canBeFilled && it.isFilled
	 }

	fun coinFlipIsHeads() = (Math.random() * 2).toInt() == 0
