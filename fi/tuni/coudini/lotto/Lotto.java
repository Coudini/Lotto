package fi.tuni.coudini.lotto;

import fi.tuni.coudini.lotto.util.Math;
import fi.tuni.coudini.lotto.util.Arrays;
import fi.tuni.coudini.lotto.util.Console;
import java.util.Scanner;

/**
 * An application that counts how many years it would take to win a lottery based on numbers chosen by the user.
 * If the lottery is won within lifetime (120 years) the application stops, otherwise it keeps on going.
 * This application uses packages for different methods to perform tasks, these methods are built so that they can be used in different applications.
 * 
 * @author Toni Salminen 
 */
public class Lotto {
    /**
     * Main method that calls other methods.
     * 
     * @param args Can be left empty or used as an input when initializing the application in the command prompt.
     */
    public static void main(String [] args) {
        int victory = 7;
        int pool = 40;
        int []user = checkArgs(args, victory, pool);
        boolean print = ask();
        winLotto(user, print, victory, pool);
    }
    /**
     * Checks if main method's String [] args's lenght is valid.
     * If not, jumps to a method for numbers input, else sends the array to be checked if it is valid.
     * 
     * @param array main method's String [] args array.
     * @param index amount of lottery numbers.
     * @param pool range of valid lottery numbers to choose starting from 1.
     * @return valid array.
     */
    public static int[] checkArgs(String [] array, int index, int pool) {
        int [] user;
        if (array.length > index || array.length < index) {
            user = start(index, pool);
        }
        else {
            user = Arrays.toIntArray(array);
            user = userInput(user, index, pool);
        }
        return user;
    }
    /**
     * Checks if user gave valid numbers in the command prompt while initializing the application.
     * If the numbers are invalid, jumps to a method that asks for user to input valid numbers.
     * 
     * @param array array transformed from String to int.
     * @param index amount of lottery numbers.
     * @param pool range of valid lottery numbers to choose starting from 1.
     * @return valid array.
     */
    public static int[] userInput(int [] array, int index, int pool) {
        int highest = 0;
        int lowest = 1;
        boolean same = false;
        int [] temp;
        for (int i = 0; i < array.length; i++) {
            if (highest < array[i]) {
                highest = array[i];
            }
            if (lowest > array[i]) {
                lowest = array[i];
            }
            int value = array[i];
            temp = Arrays.removeIndex(array, i);
            same = Arrays.contains(value, temp);
            if (same == true || highest > pool || lowest < 1) {
                array = start(index, pool);
                return array;
            }
        }
        return array;
    }
    /**
     * Asks the user to input numbers for the lottery.
     * Makes sure the numbers are valid, meaning that they are Integer numbers that stay withing the range of valid numbers.
     * Provides error messages in case they are needed.
     * 
     * @param index amount of lottery numbers.
     * @param pool range of valid lottery numbers to choose starting from 1.
     * @return an array filled with user chosen numbers.
     */
    public static int[] start(int index, int pool) {
        int [] array = new int [index];
        int number;
        boolean contains = true;
        String between = "Please give unique number between [1, " + pool + "]";
        for (int i = 0; i < index; i++) {
            System.out.println(between);
            number = Console.readInt(1, pool, "Please give number", between);
            contains = Arrays.contains(number, array);
            array[i] = number;
            if (contains == true) {
                i--;
                System.out.println("Not unique numbers!");
            }
        }
        return array;
    }
    /**
     * Asks the user if they want a visual output when there is a match between the numbers they've picked and the winning numbers.
     * 
     * @return false or true depending on user choice.
     */
    public static boolean ask() {
        Scanner s = new Scanner(System.in);
        boolean print = true;
        boolean error = true;
        while (error) {
            System.out.println("Do you want a visual output of your numbers and the winning numbers?(y/n)");
            String visual = s.nextLine();
            if (visual.equals("y")) {
                error = false;
            }
            if (visual.equals("n")) {
                print = false;
                error = false;
            }
        }
        return print;
    }
    /**
     * Checks if the user chosen numbers match the winning numbers and counts how many years it took to get the numbers right.
     * If the lotto is won within a lifetime (120 years) the loop is stopped.
     * 
     * @param user array containing user chosen numbers.
     * @param print true or false boolean deciding if a visual output of the numbers will be printed.
     * @param index amount of lottery numbers.
     * @param pool range of valid lottery numbers to choose starting from 1.
     */
    private static void winLotto(int [] user, boolean print, int index, int pool) {
        boolean lifeTime = false;
        while (!lifeTime) {
            int year = 0;
            int win = 0;
            int right = 0;
            boolean badLuck = true;
            while (badLuck) {
                for (int j = 0; j < 52; j++) {
                    int [] lotto = calculateLotto(index, pool);
                    right = Arrays.containsSameValues(user, lotto);
                    if (right > win) {
                        if (print) {
                            lotto = Arrays.sort(lotto);
                            user = Arrays.sort(user);
                            printLotto(user, lotto);
                        }
                        System.out.println("Got " + right + " right! Took " + year + " years!");
                        win = right;
                    }        
                }
                year++;
                if (win == index) {
                    if (year <= 120) {
                        System.out.println("Congratulations!");
                        lifeTime = true;
                    }
                    else {
                        System.out.println("Althought it took more than a lifetime, let's try it again.");
                    }
                    badLuck = false;
                }
            }
        }
    }
    /**
     * Calculates random lotto numbers into an array.
     * Everytime a number is picked from a range of available numbers, removes that number from the range so there is no unnecessary looping.
     * 
     * @param index amount of lottery numbers.
     * @param pool range of valid lottery numbers to choose starting from 1.
     * @return an array with randomly chosen winning lotto numbers.
     */
    private static int[] calculateLotto(int index, int pool) {
        int [] lotto = new int [pool];
        int [] lottoWin = new int [index];
        int lottoNr = 1;
        int random;
        for (int i = 0; i < lotto.length; i++) {
            lotto[i] = lottoNr;
            lottoNr++;
        }
        for (int i = 0; i < lottoWin.length; i++) {
            random = Math.getRandom(0, lotto.length-1);
            lottoWin[i] = lotto[random];
            lotto = Arrays.removeIndex(lotto, random);
        }
        return lottoWin;
    }
    /**
     * Calls for a method to prefix '0' before the numbers and then calls for a method to print the user chosen numbers and the winning numbers.
     * 
     * @param user array containing user chosen numbers.
     * @param lotto array containing winning numbers.
     */
    public static void printLotto(int [] user, int [] lotto) {
        String [] stringUser = Arrays.prefixZero(user, 1);
        String [] stringLotto = Arrays.prefixZero(lotto, 1);
        System.out.print("User lotto:   [");
        printArray(stringUser);
        System.out.println("]");
        System.out.print("Random lotto: [");
        printArray(stringLotto);
        System.out.println("]");
    }
    /**
     * Prints the array from [0[ and adds ','mark.
     * 
     * @param array array to be printed.
     */
    public static void printArray(String [] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
    }
}