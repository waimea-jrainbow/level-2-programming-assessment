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
 *
 *
 * TODO:
 * - class selection
 * - multiple weapons
 * - healing
 * - health bars
 * - weapons minimum: sword, bow, axe, spear
 * - gain a buff on loss in order to "balance" the players skill
 *
 * -- list manipulation
 * -buffs
 *  - collect new items
 *  - unlock new attacks
 *  - gain more health
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
    "The Fighter class is a versatile, weapons-oriented warrior who excels in combat, utilizing skill, strategy, and tactics. The fighter uses a Broadsword that deals up to 8 damage and has a range of 1m \"",
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
    "The Hoplite is a heavily armored soldier. The hoplite uses a spear to attack dealing up to 5 damage at a range of  and also uses a shield which increases its health.",
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
    val p1name = getString("Please enter your characters name: ")
    //ask if player wants to read class descriptions if no then pass
    descClass()
    //Get class
    val p1class = chooseClass()
    println()
    //list all parts of player one's data
    println("Player one -----------")
    println("Name: $p1name")
    println("Class: ${p1class[NAME]}")
    var p1health = p1class[HEALTH] as Int //set up player health variable

    //player 2 setup
    println()
    println("Lets set up player 2")
    //Get name
    val p2name = getString("Please enter your characters name: ")
    //ask if player wants to read class descriptions if no then pass
    descClass()
    //Get class
    val p2class = chooseClass()
    println()
    //list all parts of player one's data
    println("Player two -----------")
    println("Name: $p2name")
    println("Class: ${p2class[NAME]}")
    var p2health = p2class[HEALTH] as Int

    //final set up
    println("LET THE BATTLE BEGIN:")
    //set default distance between players for the start of the battle
    var distance = DEFAULT_DISTANCE
    //setup variable in order to check whose turn it is
    val playernames = listOf(p1name,p2name) //set up list for player names to draw from
    var currentP = 0 // set up variable to tell function which players turn it is

    //figure out who starts first based on a random value
    when (STARTER) {
        0 -> {
            println("$p1name goes first!")
            currentP = 0
        }
        1 -> {
            println("$p2name goes first!")
            currentP = 1
        }
    }

    //start battle
    while (p1health > 0 && p2health > 0) { //begin game loop
        println(DIVIDER)
        println("The field of battle")
        println()
        battlefield(distance, p1name, p2name) //show the players how far away they are from each other
        println(DIVIDER)
        print("$p1name health: $p1health") //show player 1's health
        print("$p2name health: $p2health") //show player 2's health
        val userinput = playerAction(currentP, playernames) //get the current players input on what they would like to do this turn
        when (userinput) {
            //if a is selected then check if the player can attack and if they can then do so
            'a' -> { val damage = attack (currentP, p1health, p2health, distance, p1class , p2class, p1name, p2name)
            if (currentP == 0){ //figure out which players turn it is
                p2health -= damage //update player 2's health

            }
            else p1health -= damage //update player 1's health
            }
          'm' -> distance = move(currentP, distance, p1name, p2name, p1class, p2class) //if m is selected then move the player the desired direction
          'h' ->  if (currentP == 0 ) { // if h is selected then heal the player a random amount between 1 and 6
                p1health = heal(currentP, p1health, p2health, p1name, p2name, p1class,p2class) //update player 1's health by generated amount
          }
            else {
                p2health = heal(currentP, p1health, p2health, p1name, p2name, p1class, p2class) //update player 2's health by generated amount
            }
        }

        //change currentP to change whose turn it is
        if (currentP == 0) currentP = 1
        else currentP = 0
    }

    //check if a player has no health remaining and if they don't tell the player that the other player won
    if (p1health <= 1){
        println()
        println("Congratulations $p2name you have won the battle!!!!")
    }
    if (p2health <= 1){
        println()
        println("Congratulations $p1name you have won the battle!!!!")
    }
    }



/**
 * Function for the user to choose what action they take on their turn
 *
 * parameters
 * currentP: the player whos turn it is currently
 * p1health: player one's remaining hitpoints
 * p2health: player two's remaining hitpoints
 * returns:
 * the string the user entered if it is a class in the CLASSES list
 */
fun playerAction(currentP:Int,  playernames: List<String>):Char {
    val currentPName = playernames[currentP] //find out what the current players name is
    val userinput = getChar(" $currentPName choose an action: \n$DIVIDER\n ATTACK [A] \n MOVE [M] \n HEAL [H] \n", "amh") //get the player input as to what they want to do this turn
    println()
    return userinput
}

/**
 * Function to decide how much the player heals after they choose to heal
 *
 * parameters
 * currentP: the player whos turn it is currently
 * p1health: player one's remaining hitpoints
 * p2health: player two's remaining hitpoints
 * p1name: name of player 1
 * p2name: name of player 2
 * p1class: list of player 1's stats
 * p2class: list of player 2's stats
 *
 * returns: what the healed players new health value is
 */
fun heal(currentP:Int, p1health: Int,p2health: Int, p1name: String,p2name: String, p1class: List<Any>, p2class: List<Any>): Int {
    when (currentP) { //check which players turn it is
        0 -> { //P1 heals
            val heal = (1..6).random() //generate the number of hitpoints to add

            if (p1health + heal >= p1class[HEALTH] as Int) { //check if, after being healed, the current players health will be above the maximum for their class
                println("$p1name heals $heal and is now at max health which is ${p1class[HEALTH]}.")
                return p1class[HEALTH] as Int // if it is above max then return the max so the player can't gain more than the specified max health
            } else {
                println("$p1name heals $heal and is now at ${p1health + heal} health.")
                return p1health + heal //if it won't go above the max then return their current health with the healed amount added on
            }
        }


        1 -> { //P2 heals
                // do the same as above for player instead
            val heal = (1..6).random()

            if (p2health + heal >= p2class[HEALTH] as Int) {
                println("$p2name heals $heal and is now at max health which is ${p2class[HEALTH]}.")
                return p2class[HEALTH] as Int
            } else {
                println("$p2name heals $heal and is now at ${p2health + heal} health.")
                return p2health + heal
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
 * p1name: name of player 1
 * p2name: name of player 2
 * p1class: list of player 1's stats
 * p2class: list of player 2's stats
 *
 * returns: what the healed players new health value is
 */
fun move(currentP: Int, distance: Int, p1name: String, p2name: String, p1class: List<Any>, p2class: List<Any>):Int {
    val direction = getChar("Do you want to move left or right", "lr") //ask the player which direction they want to move

    when (currentP) { //check which players turn it is
        0 -> { //P1 moves
            val speed = p1class[SPEED] as Int //get player 1's speed from their class stats
            //if the player chose l then move them left by their speed value
            if (direction == 'l') {
                val movement = distance + speed //movement is the new value for distance by adding the speed to the current distance
                println("$p1name moves $speed steps")
                return movement //return movement aka new value for distance
            }
            //if the player chose r then move them right by their speed value
            else if (direction == 'r'){
                val movement = distance - speed
                println("$p1name moves $speed steps")
                return movement
            }
        }

        1 -> { //P2 moves
            val speed = p2class[SPEED] as Int
            if (direction == 'l') {
                val movement = distance - speed
                println("$p2name moves $speed steps")
                return movement

            }
            else if (direction == 'r') {
                val movement = distance + speed
                println("$p2name moves $speed steps")
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
 * p1name: name of player 1
 * p2name: name of player 2
 * p1class: list of player 1's stats
 * p2class: list of player 2's stats
 * p1health: player 1's health
 * p2health: player 2's health
 *
 * returns: how much damage is dealt to the other player
 */
fun attack(currentP:Int, p1health:Int, p2health:Int, distance:Int, p1class: List<Any>, p2class: List<Any>, p1name: String, p2name: String): Int {
    when (currentP) { //check which players turn it is
        0 -> { //P1 attacks
            val attackRange = p1class[RANGE] as Int //get player 1's range from their class list
            if (attackRange >= distance) { //check whether the players range is greater than the distance between the players to decide whether they hit
                val damage =(1.. p1class[DAMAGE] as Int).random() //if they hit decide how much damage they do between 1 and the max damage they can do as defined by their class list
                println("$p1name takes a swing at $p2name")
                println("The hit lands dealing $damage damage. $p2name is now at ${p2health - damage} health.")
                return damage //return the damage value
            } else println("Sorry $p1name, $p2name is too far away your range is ${p1class[RANGE]}.") //if the player is out of range tell the current player that this is the case and what their range is
        }

        1 -> { //P2 attacks
            val attackRange = p2class[RANGE] as Int
            if (attackRange >= distance) {
                val damage =(1.. p2class[DAMAGE] as Int).random()
                println("$p2name takes a swing at $p1name")
                println("The hit lands dealing $damage damage. $p1name is now at ${p1health - damage} health.")
                return damage
            } else println("Sorry $p2name, $p1name is too far away your range is ${p2class[RANGE]}.")
        }


    }
    return 0 //never reached
}


/**
 * Function to get user input on whether they want to read the class descriptions
 * and if they do what class they want to read
 *
 */
fun descClass() {
    println("Would you like to read the class descriptions? ")
    var userinput = getChar("[Y]es or [N]o ", "yn") //get the users input to decide whether they want to read the class descriptions
    if (userinput == 'y'){ // if user input is y then list the classes
        println()
        //list classes
        println("The classes are:")
        for (playerClass in CLASSES) {
            println(playerClass[NAME])
        }
        //and get user input on the class they want to learn about and print the relevant description
        while (true){
        userinput = getChar("Please enter the first letter of the class you want to learn about. Enter [X] to exit ", "fahbx")
            when (userinput) {
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
 * parameters
 * returns:
 * the string the user entered if it is a class in the CLASSES list
 */
fun chooseClass(): List<Any> {
    println()
    println("Next please choose a class")
    println("The classes are:")
    for (classInfo in CLASSES) {
        println(classInfo[NAME])
    }
    while (true) {
        val userinput = getString("Type the classes' name to select it ")
        when (userinput.lowercase()) {
            "fighter" -> return FIGHTER
            "archer" -> return ARCHER
            "hoplite" -> return HOPLITE
            "barbarian" -> return BARBARIAN
            else -> println(ERROR)
        }
    }

}

fun battlefield(distance:Int,p1name:String, p2Name:String){
    print(p1name)
    for (steps in 1..distance)print("_")
    println(p2Name)
}


/**
 * Function to get a string from the user
 *
 * parameters:
 * prompt:
 * -text to show user
 * returns:
 * -string that the user has entered
 */

fun getString(prompt: String): String {
    var userinput: String

    while (true) {
        print(prompt)
        userinput = readln()
        if (userinput.isNotBlank()) break

    }
    return userinput
}

/**
 * Function to get an integer from the user
 *
 * parameters:
 * prompt:
 * -text to show user
 * returns:
 * -integer that the user has entered
 */
fun getInt(prompt: String): Int {
    var intValue:Int?

    while (true) {
        val userinput = getString(prompt)
        intValue = userinput.toIntOrNull()
        if (intValue != null) break

    }

    return intValue!!

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
        val userinput = getString(prompt).lowercase().first()
        if (check.contains(userinput)) return userinput
        else println(ERROR)

    }
}

fun rules(userinput: Char){
    if (userinput == 'y' ) {
        println()
        println("These are the rules:")
        println("At the start of the battle each player will pick a class.")
        println("These classes decide the weapons, health and abilities that you will have access to in the battle.")
        println("Once classes have been selected the battle will begin and each player will take turns making a move against their opponent")
        println("these moves include: attacking, healing, moving or using a special class ability ")
    }
    else if (userinput== 'n') {
        println("Skipping rules...")
    }
}