# Android-App-Using-Jetpack-Libraries

STEPS TO COMPLETE THE UI:

I) Initializing the App:

1) Create an app called Penny Drop.
2) Create 2 Fragment classes: PickPlayersFragment and GameFragment.
3) Create a <menu> called navigation.xml with the 2 Fragments as items.
4) Add 2 images as Vector Assets in the drawable folder to be used as the icons for the 2 fragments in the menu.
5) Create a navigation graph called nav_graph.xml after adding a variable called nav_version in top-level build.gradle and then incorporating the variable in the dependencies under the app's build.gradle file.
6) Include both Fragment classes as destinations in nav_graph.xml.
7) Add a container <FragmentContainerView> and BottomNavigationView to activity_main.xml.
8) Connect the <FragmentContainerView> to the BottomNavigationView in MainActivity by using the NavController.

II) Build the Individual Fragments and Add Data Binding to the App:  

10) Add the FAB button to fragment_pick_players.xml.
11) Create a base layout file called player_list_item featuring <CheckBox> and <SwitchCompat> for the individual rows. 
12) Include Player Rows in the fragment using the <include> tag.
13) Add a Generic layout Tag to fragment_pick_players.xml and Use Data Binding in PickPlayersFragment.
14) Enable Data Binding by adding the Kotlin kapt plugin and the dataBinding flag in the app’s build.gradle file.
15) Add data and checkboxHidden and switchHidden variable Tags Inside player_list_item.xml.
16) Conditionally Display the CheckBox and SwitchCompat for a Player.
17) Assign values to both app:checkboxHidden and app:switchHidden Variables so that only the <SwitchCompat> will be hidden from the mainPlayer and the <CheckBox> will be hidden from the main player and player two while the rest will remain as is.
18) Add a Generic layout Tag to fragment_game.xml and Use Data Binding in PickPlayersFragmentGameFragment.
19) Add relevant TextViews (including textCurrentTurnInfo.movementMethod = ScrollingMovementMethod to enable movement of scrollbars), plus the Roll and Pass Buttons.
20) Create layout_coin_slot.xml including a slotNum <variable> to use with the <TextView>.
21) Add Coin Slots to GameFragment using the <include> tag.

III) Build the Individual Fragments and Add Data Binding to the App:

23) Add the ViewModel and LiveData Dependencies to build.gradle file.
24) Add NewPlayer data class.
25) Create PickPlayersViewModel and add in the NewPlayer Instances.
26) Update PickPlayersFragment to Use PickPlayersViewModel by first adding a <variable> called viewModel to fragment_pick_players of type PickPlayersViewModel.
27) Get an Instance of PickPlayersViewModel via the by activityViewModels() syntax.
28) Bind that instance to the viewModel variable on FragmentPickPlayersBinding.
29)  Bind each NewPlayer using the player variable tags inside  player_list_item.xml.
30) Add app:player attributes to the fragment_pick_players using a collection syntax (example, viewModel.players[0]) to get each individual player.
31) Update the android:visibility attributes in player_list_item.xml to use player.canBeRemoved and player.canBeToggled in the CheckBox and SwitchCompat.
32) Use two-way binding to bind player’s name in edit text and player.isIncluded player.isHuman via android:checked property in the CheckBox and SwitchCompat.
33) Create the AI Class and add AI Spinner in player_list_item.xml.
34) Use the "android:enabled"@{player.isIncluded}" attribute in <EditText>, <Spinner>, and <SwitchCompat> tags to disable views in a player list item
35) Create drawable selector to help change the icon on the <SwitchCompat> based on its state (android:state_checked = true/false) using the <SwitchCompat>: android:thumb"@drawable/ai_toggle_bg" attribute.
36) Similarly, create color selectors to help change the color on the <SwitchCompat> based on its state using the android:thumbTint and android:trackTint attributes.
37) Create the GameViewModel class and the Player class to keep track of a player’s status in a game (this will handle the connection between the Game view and the GameHandler later on).
38) Get a List<Player> by converting NewPlayer into Player using the toPlayer​() function in the NewPlayer data class.
39) Once the players have been added to the GameViewModel, create a new function inside GameViewModel called startGame, which brings in the List<Player> and gets a game set up.
40) Bring in an instance of GameViewModel inside PickPlayersFragment, and transfer over the players when someone clicks the Play button.
41) Also transfer over the players when someone clicks the Play button by mapping each NewPlayer to a Player via the toPlayer function created in NewPlayer data class.
42) Create slot class, then add LiveData to GameViewModel to be bound to components in layout_game.xml.
43) Add a <variable> called viewModel of type GameViewModel to fragment_game.
44) Update GameFragment to use GameViewModel and set a lifecycleOwner, which ensures our LiveData will.
45) Bind the relevant text values to the values from GameViewModel for CurrentPlayer using the player <TextView> tags inside fragment_game.xml.
46) Update Button Bindings by changing android:background and android:enabled attributes to use the values from mvm.canRoll/vm.canPass, and update the android:onClick to incorporate the appropriate methods.
50) Update Slot Bindings by converting to app:slot to "@{vm.slots[]}" within slot <include> tags in fragment_game.
51) Replace the slotNum <variable> with slot in layout_coin_slot.xml, and then 
# Android-App-Using-Jetpack-Libraries

STEPS TO COMPLETE THE UI:

I) Initializing the App:

1) Create an app called Penny Drop.
2) Create 2 Fragment classes: PickPlayersFragment and GameFragment.
3) Create a <menu> called navigation.xml with the 2 Fragments as items.
4) Add 2 images as Vector Assets in the drawable folder to be used as the icons for the 2 fragments in the menu.
5) Create a navigation graph called nav_graph.xml after adding a variable called nav_version in top-level build.gradle and then incorporating the variable in the dependencies under the app's build.gradle file.
6) Include both Fragment classes as destinations in nav_graph.xml.
7) Add a container <FragmentContainerView> and BottomNavigationView to activity_main.xml.
8) Connect the <FragmentContainerView> to the BottomNavigationView in MainActivity by using the NavController.

II) Build the Individual Fragments and Add Data Binding to the App:  

10) Add the FAB button to fragment_pick_players.xml.
11) Create a base layout file called player_list_item featuring <CheckBox> and <SwitchCompat> for the individual rows. 
12) Include Player Rows in the fragment using the <include> tag.
13) Add a Generic layout Tag to fragment_pick_players.xml and Use Data Binding in PickPlayersFragment.
14) Enable Data Binding by adding the Kotlin kapt plugin and the dataBinding flag in the app’s build.gradle file.
15) Add data and checkboxHidden and switchHidden variable Tags Inside player_list_item.xml.
16) Conditionally Display the CheckBox and SwitchCompat for a Player.
17) Assign values to both app:checkboxHidden and app:switchHidden Variables so that only the <SwitchCompat> will be hidden from the mainPlayer and the <CheckBox> will be hidden from the main player and player two while the rest will remain as is.
18) Add a Generic layout Tag to fragment_game.xml and Use Data Binding in PickPlayersFragmentGameFragment.
19) Add relevant TextViews (including textCurrentTurnInfo.movementMethod = ScrollingMovementMethod to enable movement of scrollbars), plus the Roll and Pass Buttons.
20) Create layout_coin_slot.xml including a slotNum <variable> to use with the <TextView>.
21) Add Coin Slots to GameFragment using the <include> tag.

III) Binding Data with ViewModels:    

23) Add the ViewModel and LiveData Dependencies to build.gradle file.
24) Add NewPlayer data class.
25) Create PickPlayersViewModel and add in the NewPlayer Instances.
26) Update PickPlayersFragment to Use PickPlayersViewModel by first adding a <variable> called viewModel to fragment_pick_players of type PickPlayersViewModel.
27) Get an Instance of PickPlayersViewModel via the by activityViewModels() syntax.
28) Bind that instance to the viewModel variable on FragmentPickPlayersBinding.
29) Bind each NewPlayer using the player variable tags inside player_list_item.xml.
30) Add app:player attributes to the fragment_pick_players using a collection syntax (example, viewModel.players[0]) to get each individual player.
31) Update the android:visibility attributes in player_list_item.xml to use player.canBeRemoved and player.canBeToggled in the CheckBox and SwitchCompat.
32) Use two-way binding to bind the player’s name in edit text and player.isIncluded player.isHuman via android:checked property in the CheckBox and SwitchCompat.
33) Create the AI Class and add AI Spinner in player_list_item.xml.
34) Use the "android:enabled"@{player.isIncluded}" attribute in <EditText>, <Spinner>, and <SwitchCompat> tags to disable views in a player list item
35) Create a drawable selector to help change the icon on the <SwitchCompat> based on its state (android:state_checked = true/false) using the <SwitchCompat>: android:thumb"@drawable/ai_toggle_bg" attribute.
36) Similarly, create color selectors to help change the color on the <SwitchCompat> based on its state using the android:thumbTint and android:trackTint attributes.
37) Create the GameViewModel class and the Player class to keep track of a player’s status in a game (this will handle the connection between the Game view and the GameHandler later on).
38) Get a List<Player> by converting NewPlayer into Player using the toPlayer​() function in the NewPlayer data class.
39) Once the players have been added to the GameViewModel, create a new function inside GameViewModel called startGame, which brings in the List<Player> and gets a game set up.
40) Bring in an instance of GameViewModel inside PickPlayersFragment, and transfer over the players when someone clicks the Play button.
41) Also transfer over the players when someone clicks the Play button by mapping each NewPlayer to a Player via the toPlayer function created in NewPlayer data class.
42) Create slot class, then add LiveData to GameViewModel to be bound to components in layout_game.xml.
43) Add a <variable> called viewModel of type GameViewModel to fragment_game.
44) Update GameFragment to use GameViewModel and set a lifecycleOwner, which ensures our LiveData will.
45) Bind the relevant text values to the values from GameViewModel for CurrentPlayer using the player <TextView> tags inside fragment_game.xml.
46) Update Button Bindings by changing android:background and android:enabled attributes to use the values from mvm.canRoll/vm.canPass, and update the android:onClick to incorporate the appropriate methods.
50) Update Slot Bindings by converting to app:slot to "@{vm.slots[]}" within slot <include> tags in fragment_game.
51) Replace the slotNum <variable> with slot.number in layout_coin_slot.xml, and use slot.lastRolled to change textColor accordingly. 
52) Create Binding Adapters along with package-level functions to be used for the app:isHidden property in coinImageCoinSlot <ImageView> of the layout_coin_slot.xml file.

References: https://learning-oreilly-com.ezproxy.torontopubliclibrary.ca/library/view/kotlin-and-android/9781680508673/f_0005.xhtml
