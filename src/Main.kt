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
//weapon stats (damage, range, health)
val FIGHTERSTATS = mutableListOf<Int>(8,2,20)
val ARCHERSTATS = mutableListOf<Int>(6,6,10)
val HOPLITESTATS = mutableListOf<Int>(5,3,25)
val BARBARIANSTATS = mutableListOf<Int>(10,1,30)
//class descriptions
const val FIGHTERDESC = "The Fighter class is a versatile, weapons-oriented warrior who excels in combat, utilizing skill, strategy, and tactics. The fighter uses a Broadsword that deals 8 damage and has a range of 1m "
const val ARCHERDESC = "The Archer class is a person specializing in ranged combat, typically using bows and arrows, known for their agility, precision, and ability to deal damage from a distance."
const val HOPLITEDESC = "The Hoplite is a heavily armored soldier, often wielding a spear and shield, known for their strength and discipline in the phalanx formation."
const val BARBARIANDESC = "a primal warrior class focused on melee combat, using raw strength and fury to excel in battle. They often wield weapons such as axes and Greatswords"


fun main() {

    //intro
    println("--Welcome to Battle of the century--")
    println("--This is a game for 2 players--")

    //rules
    rules(getChar("Would you like to read the rules [Y]es or [N]o ","yn"))

    //player 1 setup
        println()
        println("Lets set up player 1")
        //Get name
        val p1name = getPlayerName()
        //ask if player wants to read class descriptions if no then pass
        descClass()
        //Get class
        val p1class = chooseClass()
        //list all parts of player one's data
        println("Player one -----------")
        println("Name: $p1name")
        println("Class: $p1class")

    //player 2 setup
        println()
        println("Lets set up player 2")
        //Get name
        val p2name = getPlayerName()
        //ask if player wants to read class descriptions if no then pass
        descClass()
        //Get class
        val p2class = chooseClass()
        //list all parts of player one's data
        println("Player two -----------")
        println("Name: $p2name")
        println("Class: $p2class")

    //begin the battle


}







fun getPlayerName(): String{
    println("Please enter your characters name:")
    val name = readln()
    return name
}

fun descClass() {
    println("Would you like to read the class descriptions? ")
    var userinput = getChar("[Y]es or [N]o ", "yn")
    if (userinput == 'y'){
        while (true){
        userinput = getChar("Please enter the first letter of the class you want to learn [F]ighter, [A]rcher, [B]arbarian, [H]oplite enter [X] to exit ", "fahbx")
            when (userinput) {
                'f' -> {println(FIGHTERDESC); println(); continue}
                'a' -> {println(ARCHERDESC); println(); continue}
                'b' -> {println(BARBARIANDESC); println(); continue}
                'h' -> {println(HOPLITEDESC); println(); continue}
                'x' -> break
            }
        }
    }
}

fun chooseClass(): String {
    val classes = mutableListOf("Fighter","Archer", "Hoplite", "Barbarian")

    println("Next please choose a class")
    val userinput = getString("Type the classes' name to select it ")
    when (userinput.lowercase()) {
        "fighter" -> return userinput
        "archer" -> return userinput
        "hoplite" -> return userinput
        "barbarian" -> return userinput
        else -> println(ERROR)
    }
    return "error"
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
        println("$ERROR (e.g.$check )")
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