
package checking_account1;

public class Check extends Transaction
{
    private int checkNum; 

    public Check(int tCount, int tId, double tAmt, int checkNumber){
     super(tCount, tId, tAmt);    
     checkNum = checkNumber;
    } 
    
    public int getCheckNumber(){
        return checkNum;
    }   
    
    public void setCheckNumber(int checkNumber){
        checkNum = checkNumber;
    }
}
