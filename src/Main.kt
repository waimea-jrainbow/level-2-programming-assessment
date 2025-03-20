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



fun main() {



    //intro
    println("--Welcome to Battle of the century--")
    println("--This is a game for 2 players--")
    //rules
    rules(getChar("Would you like to read the rules [Y]es or [N]o ","yn"))
    //player 1 setup
    println()
    println("Lets set up player 1")
    setupPlayer()
    //player 2 setup

    // name

    //class choice
        //list classes

    //weapon choice

}

fun setupPlayer(){
    getPlayerName()
    chooseClass()
}

fun getPlayerName(): String{
    println("Please enter your characters name:")
    val name = readln()
    return name
}

fun chooseClass(): String {
    val classes = mutableListOf("Fighter","Archer", "Hoplite", "Barbarian")

    println("Next please choose a class")
    val userinput = getString("enter the first letter of class to read its description. Type the classes' name to select it")
    when (userinput) {
        "Fighter" -> return userinput + 1
        "Archer" -> return userinput + 1
        "Hoplite" -> return userinput + 1
        "Barbarian" -> return userinput + 1
        "F" -> return userinput + 2
        "A" -> return userinput + 2
        "H" -> return userinput + 2
        "B" -> return userinput + 2

    }
    return "Error"
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
 * Function to get an char from the user
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
        else
        println("Please enter a valid  (e.g. )")
    }
}

fun rules(userinput: Char){
    if (userinput == 'y' ) {
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