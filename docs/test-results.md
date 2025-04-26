# Results of Testing

The test results show the actual outcome of the testing, following the [Test Plan](test-plan.md)

---

## Asking the user if they want to read the rules

I will enter valid and invalid inputs into the rules prompt and record the outcomes

### Test Data Used

#### valid: 
    Y, N, y, n (in order to check whether the intended outcomes are reached)
#### Invalid: 
    1 (to make sure it refuses int inputs), lorem (to make sure it refuses string inputs), g (to make sure it refuses chars other than y and n), a blank input to check that it refuses them 

### Test Result

---

![rules-y.png](screenshots/rules-y.png)

---
![rules-n.png](screenshots/rules-n.png)

---
![rules-lorem.png](screenshots/rules-lorem.png)

---
As expected on entry of valid inputs correct outcomes are achieved and on invalid inputs the user is prompted to renter another input

---

## Player setup: 
* p1Name/p2Name = test of getString() 
* descClass()
* p1Class/p2Class = test of chooseClass() function & getString()


### String retrieval aka user input e.g. getting player names and class input

the getString() function when used should ask the user to enter a string by printing the
prompt defined in the function call, when a string is received it should be checked if it is blank if it is ask
for a new string if it does contain a valid then return it

to test this I will use the assignment of p1Name and the assignment of the value userInput in the chooseclass function

#### Test Data Used
I will enter the following data into the relevant fields:

#### p1Name:
    valid: "Jeve Stobs" (for string check), 42 (check if ints are taken as strings), b (check if chars are taken as strings)
    invalid: blank input (to check if it is refused)

#### userInput:
    valid: "Fighter", 42, z
    invalid: blank input (to check if it is refused)

#### Test Result
p1Name is shown as name in the following images 
userInput is shown as Class in the following images:
---
#### p1name:  

Jeve Stobs:  
![getString()JeveStobsP1Name.png](screenshots/getString()JeveStobsP1Name.png)  
    
42:  
![getString()42P1Name.png](screenshots/getString%28%2942P1Name.png)
    
b:  
![getString()bP1Name.png](screenshots/getString()bP1Name.png)  
    
Blank:  
![getStringblankP1Name.png](screenshots/getString()blankP1Name.png)  

As expected on entry of valid inputs correct outcomes are achieved and on invalid inputs the user is prompted to renter another input

---
## Full Setup
When the full setup runs the user will be asked for their name, whether they
want to read the class descriptions and then asked what class they want to choose
and finally these choices are displayed to the players

descClass():
The user can choose whether to read the class descriptions with y or n then
if they chose yes then they can enter the first letter of the class they wish to
read about or type x in order to exit and continue to class selection

Contains getString(),descClass(),chooseClass()

### Test data used 
#### getString():
    valid: "Jeve Stobs" (for string check), 42 (check if ints are taken as strings), b (check if chars are taken as strings)  
    invalid: blank input (to check if it is refused)
#### descClass():
    valid: f,x
    invalid: Clone (String test), 7(Integer test),g(Incorrect char test)
#### chooseClass():
    valid:f(Correct char), fighter(Correct string)
    invalid:Picture(Incorrect String),k(Incorrect Char), 5(Integer test)

#### Test result:
