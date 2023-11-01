package com.example.pennydrop.types

data class Slot(
    val number: Int,
    val canBeFilled: Boolean = true,
    var isFilled: Boolean = false,
    var lastRolled: Boolean = false

//     var isFilled: Boolean = number % 2 == 0,
//     var lastRolled: Boolean = number % 3 == 2
    // this defaults isFilled to true for even numbers, and lastRolled will be true for slots 2 and 5.
)

fun List<Slot>.clear() = this.forEach { slot ->
     	  slot.isFilled = false
     	  slot.lastRolled = false
     	}

// the clear function is actually an extension function added to the List type and is defined outside of the Slot class itself
// so the code can be put anywhere that makes sense
