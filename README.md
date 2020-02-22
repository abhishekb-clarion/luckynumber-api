# LuckyNumber SpringBoot Appliction
A simple Spring Boot application to find if a person's name number matches their lucky number(both numbers being reduced to single digits). The lucky number is calculated as a sum of one's birth date entered in dd-MM-yyyy format. e.g. The lucky number for a person with birth date 21-12-1990 would be 2+1+1+2+1+9+9+0 = 25 which in turn is = 2+5 = 7. The name number is calculated as a sum of the numbers corresponding to the letters in the name. e.g. The name number for Abc would be 1+2+3 = 6. If the name number does not match the lucky number then the application suggests the user to add appropriate vowels to their name.

## Getting Started
Import the source as a Maven project and run it.
The application is configured to run on port 8083.

## Prerequisites
Java version 8 or higher.

## Testing and Validations
Junits have been written for the service layer to cover various input scenarios. Validations are done on the client side.

### Scenario 1 - Lucky number is greater than name number
#### Sample input
```
Name = Ali
Birth Date = 13-01-1991
```
#### Sample output
```
Sorry Ali, your lucky number does not match your name.
Try to add an additional 'U' to your name.
```

### Scenario 2 - Name number is greater than lucky number
#### Sample input
```
Name = Jimmy
Birth Date = 24-03-2001
```
#### Sample output
```
Sorry Jimmy, your lucky number does not match your name.
Try to add an additional 'E' to your name.
```
