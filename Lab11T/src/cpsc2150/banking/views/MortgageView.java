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
    private Scanner stdIn;
    private IMortgageController myController;
    public void setController(IMortgageController c){myController = c;}

    public MortgageView(){stdIn = new Scanner(System.in);}
    //get the cost of the users house
    public double getHouseCost(){
        double cost;
        String rawIn;
        System.out.println("How much does the house cost?");
        cost = stdIn.nextDouble();
        stdIn.nextLine();

        return cost;
    }

    //get the cost of the user's downpayment
    public double getDownPayment(){
        double downPay;
        String rawIn;
        System.out.println("How much is the down payment?");
        downPay = stdIn.nextDouble();
        stdIn.nextLine();

        return downPay;
    }

    //get the amount of years the user will have to pay
    public int getYears(){
        int years;
        System.out.println("How many years?");

        years = stdIn.nextInt();
        stdIn.nextLine();

        return years;
    }

    //get the users monthly dept payments
    public double getMonthlyDebt(){
        double monthlyDebt;

        System.out.println("How much are your monthly debt payments?");

        monthlyDebt = stdIn.nextDouble();
        stdIn.nextLine()
        ;
        return monthlyDebt;
    }

    //get the user's yearly income
    public double getYearlyIncome(){
        double yearlyIncome;

        System.out.println("How much is your yearly income?");

        yearlyIncome = stdIn.nextDouble();
        stdIn.nextLine();

        return yearlyIncome;
    }

    //get the users credit score
    public int getCreditScore(){
        int creditScore = -1;

        System.out.println("What is your credit score?");

        creditScore = stdIn.nextInt();
        stdIn.nextLine();
        return creditScore;
    }

    //get the users name
    public String getName(){
        System.out.println("What's your name?");
        String string = stdIn.nextLine();
        return string;

    }

    public void printToUser(String s){System.out.println(s);}
    public void displayPayment(double p){System.out.println("Monthly Payment: $" + p);}

    //print the interest rate
    public void displayRate(double r){
        System.out.println("Interest Rate: " + String.format("%.1f", r) + "%");;
    }

    //display whether the user has been approved
    public void displayApproved(boolean a){System.out.println(a);}
    public boolean getAnotherMortgage(){

        char charIn;

        Boolean valid = false;
        Boolean choice = true;

        System.out.println("Would you like to apply for another mortgage? Y/N");
        charIn = stdIn.next().charAt(0);
        stdIn.nextLine();

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
        char charIn = input.charAt(0);
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