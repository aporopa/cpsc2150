/**
 * Name: Zachary Supina, Abigail Poropatich
 * Date Submitted: 4/04/2023
 * Section: 1
 * Assignment Name: Model View Controller (Lab 11)
 */
package cpsc2150.banking.controllers;

import cpsc2150.banking.models.*;
import cpsc2150.banking.views.*;

public class MortgageController implements IMortgageController{

    private IMortgageView myView;

    public MortgageController(IMortgageView v){myView = v;}
    public void submitApplication(){
        Boolean quit = false;
        Boolean newMort = true;
        Boolean valid = false;

        ICustomer myCust;

        String name ="";
        double income = -1;
        double monthlyDebt = -1;
        int creditScore = -1;
        double cost = -1;
        double downPayment = -1;
        int years = -1;

        while(!quit){
            //get user's name
            name = myView.getName();

            //get yearly income
            while(!valid){
                income = myView.getYearlyIncome();
                if(income < 0){System.out.println("Income must be greater than 0");}
                else{valid = true;}
            }
            //reset flag for next validation
            valid = false;

            //get monthly debt from user and validate
            while(!valid){
                monthlyDebt = myView.getMonthlyDebt();
                if(income < 0){System.out.println("Debt must be greater than 0");}
                else{valid = true;}
            }
            //reset flag for next validation
            valid = false;

            //get credit score from user and validate
            while(!valid){
                creditScore = myView.getCreditScore();
                if((creditScore < 0) || (creditScore > 850)){System.out.println("Credit Score must be greater than 0 and less than 850");}
                else{valid = true;}
            }
            //reset flag for next validation
            valid = false;

            //apply data to a new customer object
            myCust = new Customer(monthlyDebt, income, creditScore, name);

            //apply for mortgage
            while(newMort){
                //get house cost and validate
                while(!valid){
                    cost = myView.getHouseCost();
                    if(cost < 0){System.out.println("Cost must be greater than 0");}
                    else{valid = true;}
                }
                //reset flag for next validation
                valid = false;

                //get down payment and validate
                while(!valid){
                    downPayment = myView.getDownPayment();
                    if((downPayment < 0) || (downPayment > cost)){System.out.println("Down payment must be greater than 0 and less than the cost of the house");}
                    else{valid = true;}
                }
                //reset flag for next validation
                valid = false;

                //get number of years and validate
                while(!valid){
                    years = myView.getYears();
                    if(years < 0){System.out.println("Years must be greater than 0");}
                    else{valid = true;}
                }
                //reset flag for next validation
                valid = false;

                myCust.applyForLoan(downPayment, cost, years);

                myView.printToUser(myCust.toString());

                //Ask if the user would like to apply for a new loan
                if(!myView.getAnotherMortgage()){
                    newMort = false;
                }
            }
            //reset flag for the next iteration
            newMort = true;

            //Ask the user if they'd like to start a new customer
            if(!myView.getAnotherCustomer()){
                quit = true;
            }
        }
    }
}
