
package checking_account1;

import java.awt.Font; 
import javax.swing.*;
import static checking_account1.Checking_Account1.newChk; 
import static checking_account1.Checking_Account1.newDep;
import java.io.Serializable;
import java.util.Comparator;



public class CheckingAccount extends Account implements Serializable
{  
  public boolean first = true; 
  private double totSrvcChrg;
  public Transaction [] tList;
  private int tCount; 
  
  public CheckingAccount(String aName, double begBalance){
    super(aName, begBalance);
     tCount = 0;      
     totSrvcChrg = 0.00;      
     tList = new Transaction[2]; 
    }
    
   
    public void setBalanceCheck(double transAmt, int transCode, int checkNum){
    b -= transAmt;
    Checking_Account1.newChk = new Check(getTransCount(), transCode, transAmt, checkNum);
    addTrans(newChk);
    }

    public void setBalanceDeposit(double transAmt, int transCode, double depositCash, double depositCheck){
     b += transAmt;
     Checking_Account1.newDep = new Deposit(getTransCount(), transCode, transAmt, depositCash, depositCheck);
     addTrans(newDep);
    }

    public double getSerivceCharge(){
        return totSrvcChrg;
    }

    public void setCharge(double currentCharge){

        Transaction newCharge = new Transaction(getTransCount(), 3, currentCharge);
        addTrans(newCharge);
        totSrvcChrg += currentCharge;                
    }    
    
    public int getTransCount(){
        return tCount;
    }

    public void addTrans(Transaction newTrans){
        if (tCount == tList.length)
        {
            increaseSize();
        }     
        tList[tCount] = newTrans; 
        tCount++;
    }
    
    private void increaseSize(){
        Transaction[] temp = new Transaction[tList.length*2];
        for(int num = 0; num < tList.length; num++)
        {
       temp[num] = tList[num]; 
        }     
         tList = temp;
    }
    
    public boolean getFirstTime(){
        return first;
    }
    
    public void setFirstTime(boolean set){
    first = set;
    }
    
    public Comparator<CheckingAccount> nameCompare = new Comparator<CheckingAccount>(){
        
        public int compare(CheckingAccount acct1, CheckingAccount acct2){
            String aName1 = acct1.getName().toUpperCase();
            String aName2 = acct2.getName().toUpperCase();
            return aName1.compareTo(aName2);
        }
    };
}
