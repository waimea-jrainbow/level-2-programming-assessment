/**
 * =====================================================================
 * Programming Project for NCEA Level 2, Standard 91896
 * ---------------------------------------------------------------------
 * Project Name:   Battle of the century
 * Project Author: Jaxon Rainbow
 * GitHub Repo:    https://github.com/waimea-jrainbow/level-2-programming-assessment
 * ---------------------------------------------------------------------
 * Notes:
 * Battle of the century game is a 2 player turn based fighting game which will have players choose between many actions in order
 * to defeat there opponent in combat.
 * =====================================================================
 */
//utility
const val ERROR = "Please enter a valid input."
const val DIVIDER = "-----------------------------------------------------------------------"
const val DEFAULT_DISTANCE = 4 //how far away to start players when the match starts
val STARTER = ((0..1).random()) //decide who starts first

//class stats (name,description, damage, range, health, speed)
val FIGHTER = listOf(
    "Fighter",
    "The Fighter class is a versatile, weapons-oriented warrior who excels in combat, utilizing skill, strategy, and tactics. The fighter uses a Broadsword that deals up to 8 damage and has a range of 1",
    8,
    2,
    20,
    4
)
val ARCHER = listOf(
    "Archer",
    "The Archer class is a person specializing in ranged combat. The archer uses a bow that deals up to 6 damage and has a range of 6m.",
    6,
    6,
    10,
    4
)
val HOPLITE = listOf(
    "Hoplite",
    "The Hoplite is a heavily armored soldier. The hoplite uses a spear to attack dealing up to 5 damage at a range of 3 and also uses a shield which increases its health.",
    5,
    3,
    25,
    3
)
val BARBARIAN = listOf(
    "Barbarian",
    "a primal warrior class focused on melee combat, using raw strength and fury to excel in battle. The Barbarian wields an axe that can deal up to 10 damage",
    10,
    1,
    30,
    5
)

//class indexes
const val NAME = 0
const val DESCRIPTION = 1
const val DAMAGE = 2
const val RANGE = 3
const val HEALTH = 4
const val SPEED = 5

//Classes
val CLASSES = listOf(FIGHTER,HOPLITE,ARCHER,BARBARIAN)


fun main() {

    //intro
    println("--Welcome to Battle of the century--")
    println("--This is a game for 2 players--")

    //rules
    rules(getChar("Would you like to read the rules [Y]es or [N]o? ", "yn"))

    //player 1 setup
    println()
    println("Lets set up player 1")
    //Get name
    val p1Name = getString("Please enter your characters name: ")
    //ask if player wants to read class descriptions if no then pass
    descClass()
    //Get class
    val p1Class = chooseClass()
    println()
    //list all parts of player one's data
    println("Player one -----------")
    println("Name: $p1Name")
    println("Class: ${p1Class[NAME]}")
    var p1Health = p1Class[HEALTH] as Int //set up player health variable

    //player 2 setup
    println()
    println("Lets set up player 2")
    //Get name
    val p2Name = getString("Please enter your characters name: ")
    //ask if player wants to read class descriptions if no then pass
    descClass()
    //Get class
    val p2Class = chooseClass()
    println()
    //list all parts of player one's data
    println("Player two -----------")
    println("Name: $p2Name")
    println("Class: ${p2Class[NAME]}")
    var p2Health = p2Class[HEALTH] as Int

    //final set up
    println("LET THE BATTLE BEGIN:")
    //set default distance between players for the start of the battle
    var distance = DEFAULT_DISTANCE
    //setup variable in order to check whose turn it is
    val playernames = listOf(p1Name,p2Name) //set up list for player names to draw from
    var currentP = 0 // set up variable to tell function which players turn it is

    //figure out who starts first based on a random value
    when (STARTER) {
        0 -> {
            println("$p1Name goes first!")
            currentP = 0
        }
        1 -> {
            println("$p2Name goes first!")
            currentP = 1
        }
    }

    //start battle
    while (p1Health > 0 && p2Health > 0) { //begin game loop
        println(DIVIDER)
        println("The field of battle")
        println()
        battlefield(distance, p1Name, p2Name) //show the players how far away they are from each other
        println("PLayer health:")
        print("$p1Name health: $p1Health  ") //show player 1's health
        print("$p2Name health: $p2Health") //show player 2's health
        println(DIVIDER)
        print("$p1Name health: $p1Health") //show player 1's health
        print("$p2Name health: $p2Health") //show player 2's health
        val userInput = playerAction(currentP, playernames) //get the current players input on what they would like to do this turn
        when (userInput) {
            //if a is selected then check if the player can attack and if they can then do so
            'a' -> { val damage = attack (currentP, p1Health, p2Health, distance, p1Class , p2Class, p1Name, p2Name)
            if (currentP == 0){ //figure out which players turn it is
                p2Health -= damage //update player 2's health

            }
            else p1Health -= damage //update player 1's health
            }
          'm' -> {
              distance = move(
                  currentP,
                  distance,
                  p1Name,
                  p2Name,
                  p1Class,
                  p2Class
              )  //if m is selected then move the player the desired direction
              if (distance <= 0) {
                distance = 0
              }
          }
          'h' ->  if (currentP == 0 ) { // if h is selected then heal the player a random amount between 1 and 6
                p1Health = heal(currentP, p1Health, p2Health, p1Name, p2Name, p1Class,p2Class) //update player 1's health by generated amount
          }
            else {
                p2Health = heal(currentP, p1Health, p2Health, p1Name, p2Name, p1Class, p2Class) //update player 2's health by generated amount
            }
        }

        //change currentP to change whose turn it is
        if (currentP == 0) currentP = 1
        else currentP = 0
    }

    //check if a player has no health remaining and if they don't tell the player that the other player won
    if (p1Health <= 1){
        println()
        println("Congratulations $p2Name you have won the battle!!!!")
    }
    if (p2Health <= 1){
        println()
        println("Congratulations $p1Name you have won the battle!!!!")
    }
    }

/**
 * Function to get user input on whether they want to read the rules
 *
 * parameters:
 * userInput: entered char from the user
 *
 */
fun rules(userInput: Char){
    if (userInput == 'y' ) { //check if the user input is y if it is print the rules
        println()
        println("These are the rules:")
        println("At the start of the battle each player will pick a class.")
        println("These classes decide the weapons, health and abilities that you will have access to in the battle.")
        println("Once classes have been selected the battle will begin and each player will take turns making a move against their opponent")
        println("these moves include: attacking, healing, moving or using a special class ability ")
    }
    else if (userInput== 'n') { //if the input is n then skip the rules
        println("Skipping rules...")
    }
}

/**
 * Function to get user input on whether they want to read the class descriptions
 * and if they do what class they want to read
 *
 */
fun descClass() {
    println("Would you like to read the class descriptions? ")
    var userInput = getChar("[Y]es or [N]o ", "yn") //get the users input to decide whether they want to read the class descriptions
    if (userInput == 'y'){ // if user input is y then list the classes
        println()
        //list classes
        println("The classes are:")
        for (playerClass in CLASSES) {
            println(playerClass[NAME])
        }
        //and get user input on the class they want to learn about and print the relevant description
        while (true){
            userInput = getChar("Please enter the first letter of the class you want to learn about. Enter [X] to exit ", "fahbx")
            when (userInput) {
                'f' -> {println(FIGHTER[DESCRIPTION]); println(); continue} //when f is entered print fighter description
                'a' -> {println(ARCHER[DESCRIPTION]); println(); continue} //when a is entered print archer description
                'b' -> {println(BARBARIAN[DESCRIPTION]); println(); continue} //when b is entered print barbarian description
                'h' -> {println(HOPLITE[DESCRIPTION]); println(); continue} //when h is entered print hoplite description
                'x' -> break //if x is entered then end the loop
            }
        }
    }
}

/**
 * Function for the user to choose a class
 *
 *
 * returns:
 * the class list that corelates with the entered string from the user
 */
fun chooseClass(): List<Any> {
    println()
    println("Next please choose a class")
    println("The classes are:")
    for (classInfo in CLASSES) {// list all classes
        println(classInfo[NAME])
    }
    while (true) {
        val userInput = getString("Type the classes' name to select it ") //get userInput on the class that the player wants
        when (userInput.lowercase().first()) {
            'f' -> return FIGHTER //if fighter entered then will return fighter class stats
            'a' -> return ARCHER //if archer entered then will return archer class stats
            'h' -> return HOPLITE //if hoplite entered then will return hoplite class stats
            'b' -> return BARBARIAN //if barbarian entered then will return barbarian class stats
            else -> println(ERROR) //if player doesn't enter a valid input then print error message
        }
    }

}

/**
 * Function to print the names of both player seperated by an amount of underscores equal to the distance variable
 *
 * parameters
 * distance: the distance between both players
 * p1Name: player 1's name
 * p2Name: player 2's name
 */
fun battlefield(distance:Int,p1Name:String, p2Name:String){
    print(p1Name) //print player 1's name
    for (steps in 1..distance)print("_")//print number of underscores equal to distance between players
    println(p2Name) //print player 2's name
}

/**
 * Function for the user to choose what action they take on their turn
 *
 * parameters
 * currentP: the player whos turn it is currently
 * p1Health: player one's remaining hitpoints
 * p2Health: player two's remaining hitpoints
 * returns:
 * the string the user entered if it is a class in the CLASSES list
 */
fun playerAction(currentP:Int,  playernames: List<String>):Char {
    val currentPName = playernames[currentP] //find out what the current players name is
    val userInput = getChar(" $currentPName choose an action: \n$DIVIDER\n ATTACK [A] \n MOVE [M] \n HEAL [H] \n", "amh") //get the player input as to what they want to do this turn
    println()
    return userInput
}

/**
 * Function to decide how much the player heals after they choose to heal
 *
 * parameters
 * currentP: the player whos turn it is currently
 * p1Health: player one's remaining hitpoints
 * p2Health: player two's remaining hitpoints
 * p1Name: name of player 1
 * p2Name: name of player 2
 * p1Class: list of player 1's stats
 * p2Class: list of player 2's stats
 *
 * returns: what the healed players new health value is
 */
fun heal(currentP:Int, p1Health: Int,p2Health: Int, p1Name: String,p2Name: String, p1Class: List<Any>, p2Class: List<Any>): Int {
    when (currentP) { //check which players turn it is
        0 -> { //P1 heals
            val heal = (1..6).random() //generate the number of hitpoints to add

            if (p1Health + heal >= p1Class[HEALTH] as Int) { //check if, after being healed, the current players health will be above the maximum for their class
                println("$p1Name heals $heal and is now at max health which is ${p1Class[HEALTH]}.")
                return p1Class[HEALTH] as Int // if it is above max then return the max so the player can't gain more than the specified max health
            } else {
                println("$p1Name heals $heal and is now at ${p1Health + heal} health.")
                return p1Health + heal //if it won't go above the max then return their current health with the healed amount added on
            }
        }


        1 -> { //P2 heals
                // do the same as above for player instead
            val heal = (1..6).random()

            if (p2Health + heal >= p2Class[HEALTH] as Int) {
                println("$p2Name heals $heal and is now at max health which is ${p2Class[HEALTH]}.")
                return p2Class[HEALTH] as Int
            } else {
                println("$p2Name heals $heal and is now at ${p2Health + heal} health.")
                return p2Health + heal
            }


        }

    }
    return 0 // never reached
}

/**
 * Function to move the player either left or right when move is selected
 *
 * parameters
 * currentP: the player whos turn it is currently
 * distance: the current distance between the players
 * p1Name: name of player 1
 * p2Name: name of player 2
 * p1Class: list of player 1's stats
 * p2Class: list of player 2's stats
 *
 * returns: what the healed players new health value is
 */
fun move(currentP: Int, distance: Int, p1Name: String, p2Name: String, p1Class: List<Any>, p2Class: List<Any>):Int {
    val direction = getChar("Do you want to move left or right", "lr") //ask the player which direction they want to move

    when (currentP) { //check which players turn it is
        0 -> { //P1 moves
            val speed = p1Class[SPEED] as Int //get player 1's speed from their class stats
            //if the player chose l then move them left by their speed value
            if (direction == 'l') {
                val movement = distance + speed //movement is the new value for distance by adding the speed to the current distance
                println("$p1Name moves $speed steps")
                return movement //return movement aka new value for distance
            }
            //if the player chose r then move them right by their speed value
            else if (direction == 'r'){
                val movement = distance - speed
                println("$p1Name moves $speed steps")
                return movement
            }
        }

        1 -> { //P2 moves
            val speed = p2Class[SPEED] as Int
            if (direction == 'l') {
                val movement = distance - speed
                println("$p2Name moves $speed steps")
                return movement

            }
            else if (direction == 'r') {
                val movement = distance + speed
                println("$p2Name moves $speed steps")
                return movement
            }
        }


    }
    return 0 //never reached
}


/**
 * Function decide whether the player hits and how much damage they do when attack is selected
 *
 * parameters
 * currentP: the player whos turn it is currently
 * distance: the current distance between the players
 * p1Name: name of player 1
 * p2Name: name of player 2
 * p1Class: list of player 1's stats
 * p2Class: list of player 2's stats
 * p1Health: player 1's health
 * p2Health: player 2's health
 *
 * returns: how much damage is dealt to the other player
 */
fun attack(currentP:Int, p1Health:Int, p2Health:Int, distance:Int, p1Class: List<Any>, p2Class: List<Any>, p1Name: String, p2Name: String): Int {
    when (currentP) { //check which players turn it is
        0 -> { //P1 attacks
            val attackRange = p1Class[RANGE] as Int //get player 1's range from their class list
            if (attackRange >= distance) { //check whether the players range is greater than the distance between the players to decide whether they hit
                val damage =(1.. p1Class[DAMAGE] as Int).random() //if they hit decide how much damage they do between 1 and the max damage they can do as defined by their class list
                println("$p1Name takes a swing at $p2Name")
                println("The hit lands dealing $damage damage. $p2Name is now at ${p2Health - damage} health.")
                return damage //return the damage value
            } else println("Sorry $p1Name, $p2Name is too far away your range is ${p1Class[RANGE]}.") //if the player is out of range tell the current player that this is the case and what their range is
        }

        1 -> { //P2 attacks
            val attackRange = p2Class[RANGE] as Int
            if (attackRange >= distance) {
                val damage =(1.. p2Class[DAMAGE] as Int).random()
                println("$p2Name takes a swing at $p1Name")
                println("The hit lands dealing $damage damage. $p1Name is now at ${p1Health - damage} health.")
                return damage
            } else println("Sorry $p2Name, $p1Name is too far away your range is ${p2Class[RANGE]}.")
        }


    }
    return 0 //never reached
}

/**
 * Function to get a string from the user
 *
 * parameters:
 * prompt:
 * -text to show user
 * returns:
 * -string that the user has entered
 * 
 */
fun getString(prompt: String): String {
    var userInput: String
    while (true) {
        print(prompt)
        userInput = readln() //get user input
        if (userInput.isNotBlank()) break //check if the user input isn't blank if it isn't return it if it ask again

    }
    return userInput
}


/**
 * Function to get a char from the user
 *
 * parameters:
 * prompt:
 * -text to show user
 * check:
 * -char/s that is being check for
 * returns:
 * -char that the user has entered
 */
fun getChar(prompt: String, check: String): Char {
    while (true) {
        val userInput = getString(prompt).lowercase().first() //get string from user and take first character
        if (check.contains(userInput)) return userInput //check the users input against the letters that are being checked for
        else println(ERROR) //if it doesn't ask again

    }
}
