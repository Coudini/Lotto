package fi.tuni.coudini.lotto.util;

/**
* The class Arrays contains methods that receive arrays as arguments.
* This class is meant to be updated and more methdods added into it over time.
* 
* @author Toni Salminen
*/
public class Arrays {
    /**
    * Returns an array that has been transformed to int array from String Array.
    *
    * @param array array to be transformed.
    * @return transformed array.
    */
    public static int [] toIntArray(String [] array) {
        int [] intArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            int temp = Integer.parseInt(array[i]);
            intArray[i] = temp;
        }
        return intArray;
    }
    /**
     * Checks if a value is found within the array.
     * 
     * @param value value that is searched for within an array.
     * @param array array where a value is searched from.
     * @return true or false depending if argument is found in the array.
     */
    public static boolean contains(int value, int [] array) {
        for (int i = 0; i < array.length; i++) {
            if (value == array[i]) {
                return true;
            }
        }
        return false;
    }
    /**
     * Counts how many values in given arrays are the same.
     * 
     * @param array1 array that is compared with the other array.
     * @param array2 array that is compared with the other array.
     * @return the amount of values that are same.
     */
    public static int containsSameValues(int [] array1, int [] array2) {
        int amount = 0;
        if (array1.length <= array2.length) {
            for (int i = 0; i < array1.length; i++) {
                for (int j = 0; j < array1.length; j++) {
                    if (array1[i] == array2[j]) {
                        amount++;
                    }
                }
            }
        }
        else {
            for (int i = 0; i < array2.length; i++) {
                for (int j = 0; j < array2.length; j++) {
                    if (array2[i] == array1[j]) {
                        amount++;
                    }
                }
            }
        }
        return amount;
    }
    /**
     * Generates a new array from an array received as an argument excluding the original array's [index].
     * 
     * @param original array where a value on [index] is removed from.
     * @param index [index] that is to be removed from an array.
     * @return array generated from original array.
     */
    public static int[] removeIndex(int [] original, int index) {
        int [] array = new int [original.length - 1];
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if (i == index) {
                j++;
            }
            array[i] = original[j];
            j++;
        }
        return array;
    }
    /**
     * Sorts an array in an ascending order.
     * 
     * @param array array to be sorted.
     * @return sorted array.
     */
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
    /**
     * Converts the array from int type to String type.
     * Automatically prefixes zero's before the values in an array depending on the length of the longest string of numbers.
     * 
     * @param original original array.
     * @return prefixed String array.
     */
    public static String[] prefixZero(int [] original) {
        String [] array = new String [original.length];
        int longest = 0;
        for (int i = 0; i < original.length; i++) {
            array[i] = Integer.toString(original[i]);
            if (array[i].length() > longest) {
                longest = array[i].length();
            }
        }
        for (int i = 0; i < original.length; i++) {
            if (array[i].length() < longest) {
                String temp = array[i];
                array[i] = "";
                for (int j = 0; j < longest - temp.length(); j++) {
                    array[i] = array[i] + "0";
                }
                array[i] = array[i] + temp;
            }
        }
        return array;
    }
    /**
     * Converts the array from int type to String type.
     * Prefixes zero's before the values in an array depending on the number of zero's and the length of the longest string of numbers.
     * 
     * @param original original array.
     * @param zeros amount of zero's to add.
     * @return prefixed String array.
     */
    public static String[] prefixZero(int [] original, int zeros) {
        String [] array = new String [original.length];
        int longest = 0;
        for (int i = 0; i < original.length; i++) {
            array[i] = Integer.toString(original[i]);
            if (array[i].length() > longest) {
                longest = array[i].length();
            }
        }
        for (int i = 0; i < original.length; i++) {
            String temp = array[i];
            array[i] = "";
            for(int j = temp.length(); j < zeros + 1; j++) {
                array[i] = array[i] + "0";
            }
            array[i] = array[i] + temp;
        }
        return array;
    }
}