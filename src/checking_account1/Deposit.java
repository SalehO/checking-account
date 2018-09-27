
package checking_account1;

public class Deposit extends Transaction
{      
    private double checkA;
    private double cashA; 

    public Deposit(int tCount, int tId, double tAmt, double cashAmt, double checkAmt)
    {
     super(tCount, tId, tAmt);     
    cashA = cashAmt;    
    checkA = checkAmt;
    }     
    public double getCashAmt()
    {
        return cashA;
    }     
    public double getCheckAmt()
    {
        return checkA;
    }
}
