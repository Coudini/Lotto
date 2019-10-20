package fi.tuni.coudini.lotto.util;

import java.util.Scanner;

/**
 * The class Console contains methods that read and process user inputs.
 * This class is meant to be updated and more methdods added into it over time.
 * 
 * @author Toni Salminen
 */
public class Console {
    /**
     * Checks if user gives a valid number and returns it.
     * 
     * @param errorMessage Error message that is displayed if needed.
     * @return int number read from user.
     */
    public static int readInt(String errorMessage) {
        Scanner s = new Scanner(System.in);
        int number = 0;
        boolean error = true;
        while (error) {
            try {
                int input = Integer.parseInt(s.nextLine());  
                number = input;
                error = false;
            } catch(NumberFormatException e) {
                System.out.println(errorMessage);
            }
        } 
        return number;
    }
    /**
     * Checks if user gives a valid number and returns it.
     * 
     * @param min Minimum value that user input needs to be.
     * @param max Maximum value that user input needs to be.
     * @param errorMessageNonNumeric Error message that is displayed if needed.
     * @param errorMessageNonMinAndMax Error message that is displayed if needed.
     * @return int number read from user.
     */
    public static int readInt(int min, int max, String errorMessageNonNumeric, String errorMessageNonMinAndMax) {
        Scanner s = new Scanner(System.in);
        int number = 0;
        boolean error = true;
        while (error) {
            try {
                int input = Integer.parseInt(s.nextLine());
                number = input;
            }
            catch(NumberFormatException e) {
                System.out.println(errorMessageNonNumeric);
                number = readInt(errorMessageNonNumeric);
            }
            if (number >= min && number <= max) {
                error = false;
            }
            else {
                System.out.println(errorMessageNonMinAndMax);
            }
        }
        return number;
    }
}