# Plan for Testing the Program

The test plan lays out the actions and data I will use to test the functionality of my program.

Terminology:

- **VALID** data values are those that the program expects
- **BOUNDARY** data values are at the limits of the valid range
- **INVALID** data values are those that the program should reject

---

## Rules preference entry

the player can choose whether to read the rules or not by entering y or n

### Test Data To Use

valid = Y, N, y, n (in order to check whether the intended outcomes are reached and that it can accept both upper and lower case)
Invalid = 1 (to make sure it refuses int inputs), lorem (to make sure it refuses string inputs), g (to make sure it refuses chars other than y and n), a blank input to check that it refuses them

### Expected Test Result

when Y or y is entered the player will be shown the rules. If N or n is entered the program will move onto the next section. if anything else is entered the program will ask the user to try again with a valid entry.

---

## Example Test Name

Example test description. Example test description. Example test description. Example test description. Example test description. Example test description.

### Test Data To Use

Details of test data and reasons for selection. Details of test data and reasons for selection. Details of test data and reasons for selection.

### Expected Test Result

Statement detailing what should happen. Statement detailing what should happen. Statement detailing what should happen. Statement detailing what should happen. 

---


