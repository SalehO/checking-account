
package checking_account1;

import java.io.Serializable;

public class Transaction implements Serializable
{
   protected int transNum; 
   protected int transId;   
   protected double transAmount;
   
    public Transaction(){
    transNum = 0;     
    transId = 0;      
    transAmount = 0.0;
    }
  public Transaction(int number, int id, double amount){
    transNum = number;
    transId = id;    
    transAmount = amount;
    }
    
  public int getTransNumber(){
    return transNum;
    }
    
  public int getTransId(){
      return transId;
    }
    
  public double getTransAmount(){
      return transAmount;
    }
   
}

