# Lotto App

**Lotto app** is an application that calculates how many years it would take for you to win the lottery.

## About

Lotto app's purpose is to end your *false hopes* for winning big in the lottery.

Beyond that it also demonstrates use of packages and how the methods in them are meant to be useful for other developers and reusable in different projects.
The methods included are mostly methods that deal with arrays but also methods for user inputs and mathematical functions.
*For example a simple selection sort method:*
```
public static int[] sort(int [] array) {
    for(int i = 0; i < array.length - 1; i++) {
        int min = i;
        for (int j = i + 1; j < array.length; j++) {
            if (array[j] < array[min]) {
                min = j;
            }
        }
        int temp = array[min];
        array[min] = array[i];
        array[i] = temp;
    }
    return array;
}
```

## Run

Simply run the java program in command prompt.
You can either specify your lottery numbers as you initialize the program in command prompt for example `java Lotto 1 2 3 4 12 23 34` or just let the program ask them for you.

## How

The program keeps on running until you get all your numbers right within a lifetime *(120 years)*.
By default the program is set to receive **7** unique numbers from the user from numbers **1** to **40**.
These values can be easily changed in the code as they are introduced in the main method and passed on to other methods:
```
public static void main(String [] args) {
    int victory = 7;
    int pool = 40;
    int []user = checkArgs(args, victory, pool);
    boolean print = ask();
    winLotto(user, print, victory, pool);
}
```
