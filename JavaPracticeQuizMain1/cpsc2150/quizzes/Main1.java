package cpsc2150.quizzes;

import java.util.*;
public class Main1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //initialization of main variables
        int numberRange = 100;
        int sum = 0, min = Integer.MAX_VALUE, max = 0;

        //Ask for user input and keep track of the sum by adding each subsequent value
        System.out.println("Enter " + numberRange + " integers: ");
        for(int i = 0; i < numberRange; i++){
            int newNum = scan.nextInt();
            sum = sum + newNum;

            //if the newly scanned in number is less than the minimum
            //then update the variable with the new minimum value
            if(newNum < min)
                min = newNum;

            //if the newly scanned in number is greater than the maximum value
            //then update the variable with the new maximum value
            if(newNum > max)
                max = newNum;
        }

        //close the scanner to prevent memory leaks
        scan.close();

        //print to screen the required information and establish an average variable
        double average =  (double) sum/numberRange;
        System.out.println("The sum of all numbers is " + sum);
        System.out.println("The average of all numbers is " + average);
        System.out.println("The min of all numbers is " + min);
        System.out.println("The max of all numbers is " + max);
    }
}