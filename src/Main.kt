import jdk.jfr.Description
import jdk.jfr.Name
import java.util.*


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
const val DEFAULT_DISTANCE = 4
val STARTER = ((1..2).random())
//class stats (name,description, damage, range, health)
val FIGHTER = listOf(
    "Fighter",
    "The Fighter class is a versatile, weapons-oriented warrior who excels in combat, utilizing skill, strategy, and tactics. The fighter uses a Broadsword that deals 8 damage and has a range of 1m \"",
    8,
    2,
    20
)
val ARCHER = listOf(
    "Archer",
    "The Archer class is a person specializing in ranged combat. The archer uses a bow that deals 6 damage and has a range of 6m.",
    6,
    6,
    10)
val HOPLITE = listOf(
    "Hoplite",
    "The Hoplite is a heavily armored soldier. The hoplite uses a spear to attack dealing 5 damage at a range of  and also uses a shield which increases its health.",
    5,
    3,
    25)
val BARBARIAN = listOf(
    "Barbarian",
    "a primal warrior class focused on melee combat, using raw strength and fury to excel in battle. The Barbarian wields an axe",
    10,
    1,
    30)

//class indexes
const val NAME = 0
const val DESCRIPTION = 1
const val DAMAGE = 2
const val RANGE = 3
const val HEALTH = 4

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
    var p1health = p1class[HEALTH]
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
    var p2health = p2class[HEALTH]
    //begin the battle
    println("LET THE BATTLE BEGIN:")
    //set default distance between players for the start of the battle
    var distance = DEFAULT_DISTANCE
    //setup variable in order to check whose turn it is
    var currentP = 0
    when (STARTER) {
        1 -> {
            println("$p1name goes first!")
            currentP = 1
        }
        2 -> {
            println("$p2name goes first!")
            currentP = 2
        }
    }
    println("The field of battle")
    println()
    battlefield(distance, p1name, p2name)
    println(DIVIDER)
    playerAction(currentP,p1health as Int, p2health as Int)
    when (currentP){
        1 -> currentP = 2
        2 -> currentP = 1
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
fun playerAction(currentP:Int, p1health:Int, p2health:Int) {
    while (p1health > 0 || p2health > 0) {
        val userinput = getChar("Player $currentP please choose an action: ", "amh")
        println(DIVIDER)
        println("ATTACK [A]")
        println("MOVE [M]")
        println("HEAL [H]")
        when (userinput) {
            'a' -> attack(currentP, p1health, p2health)
            'm' -> move()
            'h' -> heal()
        }

    }
}

fun attack(currentP:Int, p1health:Int, p2health:Int ){
    
}







/**
 * Function to get user input on whether they want to read the class descriptions
 * and if they do what class they want to read
 *
 */
fun descClass() {
    println("Would you like to read the class descriptions? ")
    var userinput = getChar("[Y]es or [N]o ", "yn")
    if (userinput == 'y'){
        println()
        //list classes
        println("The classes are:")
        for (playerClass in CLASSES) {
            println(playerClass[NAME])
        }
        //get user input on the class they want to learn about and print its description
        while (true){
        userinput = getChar("Please enter the first letter of the class you want to learn about. Enter [X] to exit ", "fahbx")
            when (userinput) {
                'f' -> {println(FIGHTER[DESCRIPTION]); println(); continue}
                'a' -> {println(ARCHER[DESCRIPTION]); println(); continue}
                'b' -> {println(BARBARIAN[DESCRIPTION]); println(); continue}
                'h' -> {println(HOPLITE[DESCRIPTION]); println(); continue}
                'x' -> break
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
    for (steps in 1..distance)println("_")
    print(p2Name)
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