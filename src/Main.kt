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
    println("These are the rules:")
    println("")
    //player 1 setup

        // name

        //class choice
            //list classes

        //weapon choice

    //player 2 setup

    // name

    //class choice
        //list classes

    //weapon choice

}

fun setupPlayer{

}

fun getplayerName{

}

fun chooseClass{

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

fun getChar(prompt: String): Char {
    while (true) {
        val userinput = getString(prompt).first()
        if ("".contains(userinput)) return userinput
        println("Please enter a valid  (e.g. )")
    }
}