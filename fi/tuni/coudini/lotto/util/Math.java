package fi.tuni.coudini.lotto.util;

/**
* The class Math contains simple mathematical methods to perform various tasks.
* This class is meant to be updated and more methdods added into it over time.
*
* @author Toni Salminen
*/
public class Math {
    /**
     * Generates a random number (int) between arguments (int min) and (int max).
     * 
     * @param min Minimal value which is subtracted from the maximum value.
     * @param max Maximum value which minimal value is subtracted from.
     * @return random number between [min, max[
     */
    public static int getRandom(int min, int max) {
        if (min > max) {
            int temp = max;
            max = min;
            min = temp;
        }
        return min + (int) (java.lang.Math.random() * ((max - min) + 1));
    }
}