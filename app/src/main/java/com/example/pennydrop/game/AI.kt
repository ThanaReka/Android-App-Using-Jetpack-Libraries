package com.example.pennydrop.game

data class AI(val name: String){

    //a toString method that returns the name property
    override fun toString() = name

	//the above is equivalent to:
	// 	override fun toString(): String {
	// 	  return name
	// 	}

    companion object {
         	    @JvmStatic
         	    val basicAI = listOf(
         	      AI("TwoFace"),
         	      AI("No Go Noah"),
         	      AI("Bail Out Beulah"),
         	      AI("Fearful Fred"),
         	      AI("Even Steven"),
         	      AI("Riverboat Ron"),
         	      AI("Sammy Sixes"),
         	      AI("Random Rachael")
         	    )
         	  }
    //eight AI players to be used in the Spinner
	//The companion object syntax denotes an object that is associated with a given class.
    //There’s only one instance of this object per class (meaning it’s a singleton) and
	//we can get basicAI elsewhere by referencing AI.basicAI

	//the @JvmStatic annotation tells the compiler to create an additional static get method for basicAI,
	//which will allow us to reference basicAI in a static way within a player list item,
}
