package com.example.pennydrop.binding

import android.view.View
import androidx.databinding.BindingAdapter


@BindingAdapter("isHidden")
fun bindIsHidden(view: View, isInvisible: Boolean) {
    view.visibility = if (isInvisible) View.INVISIBLE else View.VISIBLE
}

// These are package-level functions that aren’t associated with a particular class
// but instead just live as part of a package.

// the bindIsHidden function which will create a new app:isHidden attribute on all views
// similar to what was done before in the ternary statement for android:background and
// other properties in the layout_coin_slot.xml file