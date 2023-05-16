/**
 * Name: Zachary Supina, Abigail Poropatich
 * Date Submitted: 4/04/2023
 * Section: 1
 * Assignment Name: Model View Controller (Lab 11)
 */
package cpsc2150.banking.models;
public class Mortgage extends AbsMortgage implements IMortgage{
    private Double Payment;
    private Double Rate;
    private ICustomer Customer;
    private Double DebtToIncomeRatio;
    private Double Principal;
    private int NumberOfPayments;
    private Double PercentDown;
    private int years;

    /**
     * Constructor - given the amount of the loan, the down payment, the length (in years) of the loan,
     * and a Customer it will calculate the values of the private data members such that we can
     * determine if a loan is approved or denied.
     *
     * @param c is the amount of the loan
     * @param dp is the amount of the down payment
     * @param y is the length of the loan in years
     * @param cust is an Object defined by the ICustomer interface which contains necessary data
     *             to determine if a given loan is approved or denied
     *
     * @pre c > 0 && dp > 0 && y >= MIN_YEARS && y <= MAX_YEARS
     *
     * @post Customer == cust && years = y && Principal == c - dp && NumberOfPayments == y * MONTHS_IN_YEAR
     * && DebtToIncomeRatio == Customer.getMonthlyDebtPayments() / Customer.getIncome() && PercentDown ==
     * dp / c && Payment == ((Rate / MONTHS_IN_YEAR) * Principal) / (1 - (Math.pow(1 + Rate / MONTHS_IN_YEAR,
     * (NumberOfPayments * -1)))) && Rate == getRate()
     */
    Mortgage(double c, double dp, int y, ICustomer cust){
        Customer = cust;
        years = y;
        Principal = c - dp;
        NumberOfPayments = y * MONTHS_IN_YEAR;
        PercentDown = dp/c;
        Rate = getRate();
        Payment = ((Rate / MONTHS_IN_YEAR) * Principal)/
                (1 - (Math.pow(1 + Rate / MONTHS_IN_YEAR, (NumberOfPayments * -1))));
        DebtToIncomeRatio = ((Customer.getMonthlyDebtPayments() + Payment) * 12)/ Customer.getIncome();
    }
    public boolean loanApproved(){
        if((Rate < RATETOOHIGH) && (PercentDown >= MIN_PERCENT_DOWN) && (DebtToIncomeRatio <= DTOITOOHIGH) && ((getYears() >= MIN_YEARS) && (getYears() <= MAX_YEARS))){
            return true;
        }
        else{
            return false;
        }
    }
    public double getPayment(){return Payment;}
    public double getRate(){
        Rate = BASERATE;
        if(years < 30){
            Rate += 0.005;
        }
        else {
            Rate += 0.01;
        }
        if(PercentDown < PREFERRED_PERCENT_DOWN){
            Rate += 0.005;
        }
        if(Customer.getCreditScore() >= GREATCREDIT)
        {}
        else if(Customer.getCreditScore() >= GOODCREDIT){
            Rate += GOODRATEADD;
        }
        else if(Customer.getCreditScore() >= FAIRCREDIT){
            Rate += NORMALRATEADD;
        }
        else if(Customer.getCreditScore() >= BADCREDIT){
            Rate += BADRATEADD;
        }
        else{
            Rate += VERYBADRATEADD;
        }
        return Rate;
    }
    public double getPrincipal(){return Principal;}
    public int getYears(){return years;}
}
