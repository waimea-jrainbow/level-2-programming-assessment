# Plan for Testing the Program

The test plan lays out the actions and data I will use to test the functionality of my program.

Terminology:

- **VALID** data values are those that the program expects
- **BOUNDARY** data values are at the limits of the valid range
- **INVALID** data values are those that the program should reject

---

## Asking the user if they want to read the rules

the player can choose whether to read the rules or not by entering y, n ,Y or N I will
test this by entering these values as well as invalid inputs as listed below

### Test Data To Use

#### valid:
    Y, N, y, n (in order to check whether the intended outcomes are reached and that it can accept both upper and lower case)

#### Invalid: 
    1 (to make sure it refuses int inputs), lorem (to make sure it refuses string inputs), g (to make sure it refuses chars other than y and n), a blank input to check that it refuses them

### Expected Test Result

when Y or y is entered the player will be shown the rules. If N or n is entered the program will move onto the next section. if anything else is entered the program will ask the user to try again with a valid entry.

---
# Setup


## String retrieval aka user input e.g. getting player names

the getString() function when used should ask the user to enter a string by printing the 
prompt defined in the function call, when a string is received it should be checked if it is blank if it is ask
for a new string if it does contain a valid then return it 

to test this I will use the assignment of p1Name and the assignment of the value userInput in the chooseclass function

### Test Data To Use

#### p1Name:
    valid: "Jeve Stobs" (for string check), 42 (check if ints are taken as strings), b (check if chars are taken as strings)    
    invalid: blank input (to check if it is refused)

#### userInput: 
    valid: "Fighter" (to test acceptance of valid class names),f (to check acceptance of valid first letters of class names), 42  
    invalid: blank input (to check if it is refused), z (to check invalid first letter chars)

### Expected Test Result

When the getString() function is called the defined prompt is printed then the user able to enter a string, if this
string is blank then the user is asked to enter a new string, otherwise the entered string is returned

---
## descClass
The user will be asked whether they wish to read the descriptions of the classes and be prompted with a y or n answer
on entry of n the program will move onto the next function ignoring the rest of descClass(). On entry of y the user will
be shown a list of the classes and be prompted to entry the first letter of the classes name in order to read about it
on entry of a letter the respective classes description will be printed and then the user will be asked if they wish to read another
description. On entry of the char x the loop will be broken and the program will move onto the next function

To test this I will enter all valid inputs for each prompt and then enter invalid values to test reactions to strings, ints, 
invalid char characters and blank inputs

#### Test data to use

#### userinput 1
    valid: y, n
    invalid: word, 72, x, blank
#### userinput 2
    valid: f, b, h, a, x
    invalid: word, 72, c, blank

#### Expected test result
I expect the first userinput to accept only y or n inputs of any capitalization and reject all other inputs by asking the user 
to enter a new input
I expect the second userinput to accept only the first letters of each class or the name of the class and the char x. On entry of a first letter the relevant 
description will be printed if x is entered the loop will break and the function will end if anything else is entered it will be refused 
by asking the user for a valid input

---

## Full setup
When the full setup runs the user will be asked for their name, whether they 
want to read the class descriptions and then asked what class they want to choose
and finally these choices are displayed to the players

descClass():
The user can choose whether to read the class descriptions with y or n then 
if they chose yes then they can enter the first letter of the class they wish to 
read about or type x in order to exit and continue to class selection

Contains getString(),descClass(),chooseClass()

### Test Data 

#### getString():
    valid: "Jeve Stobs"
#### descClass():
    valid: y
    valid: f, x
#### chooseClass():
    valid:f
    
### Expected Test Result 
After the rules() function runs the first player is asked for their name,whether they 
want to read class descriptions and what class they want and finally these decisions are shown printed for the players to read

---
## player 2 setup
This should work the same as player 1 setup but addressed to player 2 instead

I will test this by entering similar data into the inputs in the setup process
### Test data

#### getString():
    valid: "bobert"
#### descClass():
    valid: y
    valid: b, x
#### chooseClass():
    valid:b

### Expected test result
the second player is asked for their name,whether they
want to read class descriptions and what class they want 
and finally these decisions are shown printed for the players to read
---

### player actions

The player whos turn it currently is will be asked what they want to do on their turn from a list conainting attack move or heal and the player
selects these by enterting the first letter of these names once this is done the relevant function will run and the relevant
changes and action will be undertaken

I will test the playerAction() funcction by enterting the 3 different options

I will test the attack function by choosing it and amking sure it runs properly at different distances between players

I will test the heal function by choosing it making sure it changes the current players health correctly

I wll test the move function by choosing it and moving both left and right ensuring the player is moved the correct distance based on their speed

### Test data 

#### playerAction
    valid: a,h,m
#### attack
    N/A
#### heal
    N/A
#### move
    valid:  l,r

### expected test results
when the player chooses their action the relevant fucntion is run

when the player chooses to attack if the other player is within range then the player will attack and the correct amount of health will be removed from the other player 

when the player chooses to heal the correct amount of health will be added to the current player but not exceeding their original max health

when the player chooses to move they will be asked to choose whether to move left or right and the player will then do so but will not pass the other player if they attempt to


### Winning
When a players health goes to 0 or below then the other player will be told they have won and the game will end

I will test this by running through the game attacking until someone wins I will do this twice to test both players 

### Test data
Run the game till a player wins 

### expected test results
When a players health goes to 0 or below then the other player will be told they have won and the game will end