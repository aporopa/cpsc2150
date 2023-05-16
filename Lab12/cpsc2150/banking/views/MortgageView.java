/**
 * Name: Zachary Supina, Abigail Poropatich
 * Date Submitted: 4/04/2023
 * Section: 1
 * Assignment Name: Model View Controller (Lab 11)
 */

package cpsc2150.banking.views;

import cpsc2150.banking.controllers.*;
import java.util.*;

public class MortgageView implements IMortgageView {
    private Scanner stdIn = new Scanner(System.in);
    private IMortgageController myController;
    public void setController(IMortgageController c){myController = c;}

    //get the cost of the users house
    public double getHouseCost(){
        double cost;
        String rawIn;
        System.out.println("How much does the house cost?");
        rawIn = stdIn.nextLine();
        cost = Double.parseDouble(rawIn);
        return cost;
    }

    //get the cost of the user's downpayment
    public double getDownPayment(){
        double downPay;
        String rawIn;
        System.out.println("How much is the down payment?");
        rawIn = stdIn.nextLine();
        downPay = Double.parseDouble(rawIn);
        return downPay;
    }

    //get the amount of years the user will have to pay
    public int getYears(){
        int years;
        String rawIn;
        System.out.println("How many years?");
        rawIn = stdIn.nextLine();
        years = Integer.parseInt(rawIn);
        return years;
    }

    //get the users monthly dept payments
    public double getMonthlyDebt(){
        double monthlyDebt;
        String rawIn;
        System.out.println("How much are your monthly debt payments?");
        rawIn = stdIn.nextLine();
        monthlyDebt = Double.parseDouble(rawIn);
        return monthlyDebt;
    }

    //get the user's yearly income
    public double getYearlyIncome(){
        double yearlyIncome;
        String rawIn;
        System.out.println("How much is your yearly income?");
        rawIn = stdIn.nextLine();
        yearlyIncome = Double.parseDouble(rawIn);
        return yearlyIncome;
    }

    //get the users credit score
    public int getCreditScore(){
        int creditScore = -1;
        String rawIn;
        System.out.println("What is your credit score?");
        rawIn = stdIn.nextLine();
        creditScore = Integer.parseInt(rawIn);
        return creditScore;
    }

    //get the users name
    public String getName(){
        System.out.println("What is your name?");
        String string = stdIn.nextLine();
        return string;

    }

    public void printToUser(String s){System.out.println(s);}
    public void displayPayment(double p){System.out.println("Monthy Payment: $" + p);}

    //print the interest rate
    public void displayRate(double r){
        System.out.println("Interest Rate: " + String.format("%.1f", r) + "%");;
    }

    //display whether the user has been approved
    public void displayApproved(boolean a){System.out.println(a);}
    public boolean getAnotherMortgage(){
        String rawIn;
        Character charIn;
        Boolean valid = false;
        Boolean choice = true;

        System.out.println("Would you like to apply for another mortgage? Y/N");
        rawIn = stdIn.nextLine();
        charIn = rawIn.charAt(0);

        //perform input validation
        while(!valid){
            switch (charIn){
                case 'y':
                case 'Y':
                    choice = true;
                    valid = true;
                    break;
                case 'n':
                case 'N':
                    choice = false;
                    valid = true;
                    break;
                default:
                    System.out.println("Would you like to apply for another mortgage? Y/N");
                    break;
            }
        }
        return choice;
    }

    //Ask the user if they would like to add another customer
    public boolean getAnotherCustomer() {
        System.out.println("Would you like to consider another customer? Y/N");
        String input = stdIn.nextLine();
        Character charIn = input.charAt(0);
        Boolean valid = false;
        Boolean choice = true;

        //perform input validation
        while(!valid){
            switch (charIn){
                case 'y':
                case 'Y':
                    choice = true;
                    valid = true;
                    break;
                case 'n':
                case 'N':
                    choice = false;
                    valid = true;
                    break;
                default:
                    System.out.println("Would you like to apply for another mortgage? Y/N");
                    break;
            }
        }
        return choice;
    }
}
