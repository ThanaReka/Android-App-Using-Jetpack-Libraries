package com.example.pennydrop.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pennydrop.types.NewPlayer

class PickPlayersViewModel: ViewModel() {
    //the ViewModel class has a constructor, which requires us to tell the compiler
    // how we want to initialize the parent class when creating our child class,
    // which we do via the constructor invocation syntax

    val players = MutableLiveData<List<NewPlayer>>().apply {
        this.value = (1..6).map {
            NewPlayer(
                canBeRemoved = it > 2,
                canBeToggled = it > 1
            )
        }

    // To get a List of NewPlayer instances as MutableLiveData,
    // we create an IntRange (in this case 1 - 6), then map each item to a new instance of our class
    // (we don’t need to use the current index in map; we just want to have a number
    // of items in a list equal to the length of the IntRange)
    // finally, we just need to add in the conditional logic (canBeRemoved and canBeToggled)

    //.apply takes the original object and returns it with the modifications made.

    }
}