
package checking_account1;

import java.io.Serializable;

public class Account implements Serializable
{   
    protected double b;
    protected String n; 
  
    public Account (){
        b = 0.00;    
        n = "";
    }  
    
    public Account(String acctName, double initBalance){
        b = initBalance;     
        n = acctName;
    }    
    
    public String getName(){
        return n;
    }     
    
    public double getBalance(){
        return b;
    }  
    
    public void setBalance(double balance){
        this.b = balance;
    }
    
}
