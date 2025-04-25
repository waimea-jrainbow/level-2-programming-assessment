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

valid = Y, N, y, n (in order to check whether the intended outcomes are reached and that it can accept both upper and lower case)
Invalid = 1 (to make sure it refuses int inputs), lorem (to make sure it refuses string inputs), g (to make sure it refuses chars other than y and n), a blank input to check that it refuses them

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

p1Name:
    valid: "Jeve Stobs" (for string check), 42 (check if ints are taken as strings), b (check if chars are taken as strings)
    invalid: blank input (to check if it is refused)

userInput: 
    valid: "Fighter" (to test acceptance of valid class names),f (to check acceptance of valid first letters of class names), 42
    invalid: blank input (to check if it is refused), z (to check invalid first letter chars)

### Expected Test Result

When the getString() function is called the defined prompt is printed then the user able to enter a string, if this
string is blank then the user is asked to enter a new string, otherwise the entered string is returned

---

### Full setup

### Test Data 

### Expected Test Result 


