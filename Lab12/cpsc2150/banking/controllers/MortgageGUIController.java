package cpsc2150.banking.controllers;
import cpsc2150.banking.models.Customer;
import cpsc2150.banking.models.ICustomer;
import cpsc2150.banking.views.*;
public class MortgageGUIController implements IMortgageController {
    private IMortgageView view;
    public MortgageGUIController(IMortgageView v) {
        view = v;
    }
    public void submitApplication() {
        ICustomer myCust;

        Boolean loanApproved = false;
        Boolean validIncome = true;
        Boolean validDebt = true;
        Boolean validCredit = true;
        Boolean validCost = true;
        Boolean validPayment = true;
        Boolean validYears = true;
        String name ="";
        double income = -1;
        double monthlyDebt = -1;
        int creditScore = -1;
        double cost = -1;
        double downPayment = -1;
        double rate = -1;
        int years = -1;

        //get user's name
        name = view.getName();

        //get yearly income

        income = view.getYearlyIncome();
        if(income < 0){validIncome = false;}

        //get monthly debt from user and validate
        monthlyDebt = view.getMonthlyDebt();
        if(income < 0){validDebt = false;}

        //get credit score from user and validate
        creditScore = view.getCreditScore();
        if((creditScore < 0) || (creditScore > 850)){validCredit = false;}

        //apply data to a new customer object
        myCust = new Customer(monthlyDebt, income, creditScore, name);

        //apply for mortgage

        //get house cost and validate
        cost = view.getHouseCost();
        if(cost < 0){validCost = false;}

        //get down payment and validate
        downPayment = view.getDownPayment();
        if((downPayment < 0) || (downPayment > cost)){validPayment = false;}

        //get number of years and validate
        years = view.getYears();
        if(years < 0){validYears = false;}

        if(validCost && validCredit && validDebt && validIncome && validPayment && validYears) {

            loanApproved = myCust.applyForLoan(downPayment, cost, years);
            if(loanApproved) {
                view.displayApproved(loanApproved);
                view.displayPayment(downPayment);
                view.displayRate(myCust.getRate());
            }
            else{
                view.displayApproved(loanApproved);
            }
        }
    }
}