

package checking_account1;

import java.awt.Font;
import javax.swing.*; 
import java.util.*;
import java.text.NumberFormat; 
import java.io.*; 
import java.awt.GridLayout;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;



public class Checking_Account1 
{
 public static CheckOptionFrame frame;  
 public static ArrayList<CheckingAccount> list;
 public static JTextArea textArea;  
 public static double initBalance;  
 public static CheckingAccount myAccount;   
 public static Check newChk;  
 public static Deposit newDep;   
 public static Transaction newTrans;   
 public static NumberFormat dollarfmt;   
 public static String filename = "file.dat"; 
 public static boolean changed = false;
 
    public static void main(String[] args) 
    {
        list = new ArrayList<CheckingAccount>();    
        frame = new CheckOptionFrame("Checking Account Options");  
        textArea = new JTextArea(10,50);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));   
        frame.getContentPane().add(textArea);
        frame.pack();   
        frame.setVisible(true);
    }     
    public static void newAccount(){
        String aName;
        String balance_str;
        
        aName = JOptionPane.showInputDialog("Enter account name: ");         
        balance_str = JOptionPane.showInputDialog("Enter your initial balance: ");
        initBalance = Double.parseDouble(balance_str);
        myAccount = new CheckingAccount(aName, initBalance);
        list.add(myAccount);     
        changed = true;
    }   
  public static Comparable binarySearch (Comparable[] list,
                                          Comparable target)
   {
      int min=0, max=list.length, mid=0;
      boolean found = false;

      while (!found && min <= max)
      {
         mid = (min+max) / 2;
         if (list[mid].equals(target))
            found = true;
         else
            if (target.compareTo(list[mid]) < 0)
               max = mid-1;
            else
               min = mid+1;
      }

      if (found)
         return list[mid];
      else
         return null;
   }
    public static void findAcc(){
         Collections.sort(list, myAccount.nameCompare);//******//
        String name, message;
        //int num;         
        name = JOptionPane.showInputDialog("Enter account name: ");
      int min=0, max;
     max = list.size();
     int mid=0;
      boolean found = false;
      while (!found && min <= max)
      {
         mid = (min+max) / 2;
         if (list[mid].getName().equals(name))
            found = true;
         else
            if (name.compareTo(list.get(mid).getName().toUpperCase()) < 0)
               max = mid-1;
            else
               min = mid+1;
      }
        if (found)
                { message = "Found account for " + name + "\n";
                textArea.setText(message);}
        else      
        textArea.setText("Name not found."); 
    } 
    
    public static void listAcc(){
        Collections.sort(list, myAccount.nameCompare);//******//
        String message = "Available Accounts \n";
        for(int num = 0; num!= list.size(); num++)
        {
            myAccount = list.get(num);
            message += myAccount.getName() + "\n";
        }
        textArea.setText(message);   
    }
    
    public static void processT(){
        
        double transAmt, depositCash, depositCheck;
        int transCode, checkNum;    
        changed = true;
        dollarfmt = NumberFormat.getCurrencyInstance();
        transCode = getTCode();
            
        if (transCode != 0)
        {          
            if (transCode == 1)
            {   
                checkNum = promptCheckNum();
                transAmt = getTAmtChk();
                myAccount.setBalanceCheck(transAmt, transCode, checkNum);
                processChk(transAmt);          
                frame.setVisible(true);
            }
                
            else if(transCode == 2)
            {
                String depCash_str, depCheck_str;
                JTextField cashField = new JTextField("");
                JTextField checkField = new JTextField("");
                JPanel panel2 = new JPanel(new GridLayout(0,1));
                panel2.add(new JLabel("Cash"));
                panel2.add(cashField);        
                panel2.add(new JLabel("Checks"));
                panel2.add(checkField);
                cashField.addAncestorListener(new SetFocus());//***********************************************
                int answer = JOptionPane.showConfirmDialog(null, panel2, "Deposit",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                
                if(answer == JOptionPane.OK_OPTION)
                {
                  depCash_str = cashField.getText();     
                  depositCash = Double.parseDouble(depCash_str);
                  depCheck_str = checkField.getText();
                  depositCheck = Double.parseDouble(depCheck_str);
                  transAmt = depositCash + depositCheck;
                  myAccount.setBalanceDeposit(transAmt, transCode, depositCash, depositCheck);
                  processD(transAmt);     
                  frame.setVisible(true);
                }
                else
                {
                    System.out.println("Cancelled");
                    System.exit(0);
                }
            }
            
        }
        
        if(transCode == 0)
        {
            totTransaction();
            frame.setVisible(true);
        }
    }
    
    public static int getTCode(){
        String transCode_str;    
        int transCode;
        
        transCode_str = JOptionPane.showInputDialog("0->End \n1->Check \n2->Deposit \nEnter trans code: ");
        transCode = Integer.parseInt(transCode_str);
        return transCode;
    }     
    
    public static double getTAmtChk(){
        String transAmt_str;     
        double transAmt;
        
        transAmt_str = JOptionPane.showInputDialog("Enter the check transaction amount: ");
        transAmt = Double.parseDouble(transAmt_str);
        return transAmt;
    }  
    
    public static int promptCheckNum(){
        String checkNum_str;     
        int checkNum;
        
        checkNum_str = JOptionPane.showInputDialog("Enter the check number: ");
        checkNum = Integer.parseInt(checkNum_str);
        return checkNum;
    }     
    
    public static void processChk(double transAmt){
        double currentCharge;
        String message;
        boolean firstTime = true;
        
        dollarfmt = NumberFormat.getCurrencyInstance();
        currentCharge = 0.15;
        myAccount.setCharge(currentCharge);
        message = myAccount.getName() +  "'s account-\n";
        message += "Transaction: Check #" + newChk.getCheckNumber() + " in the amount of " + dollarfmt.format(transAmt) + "\n";

        
        if(myAccount.getBalance() < 500 && myAccount.getFirstTime())
        {
            myAccount.setFirstTime(false); 
            message += "Service Charge: Below $500 --- charge $5.00 \n";
            currentCharge = 5.00;
            myAccount.setCharge(currentCharge);
        }
        
        if (myAccount.getBalance() < 50)
        {
            message += "Warning: Balance below $50 \n";
        }
        
        if (myAccount.getBalance() < 0)
        {
            message += "Service Charge: Below $0 --- charge $10.00 \n";
            currentCharge = 10.00;
            myAccount.setCharge(currentCharge);
        }      
        message += "Current Balance: " + dollarfmt.format(myAccount.getBalance()-myAccount. getSerivceCharge()) + "\n";
        message += "Service Charge: Check --- charge " + dollarfmt.format(currentCharge) + "\n";
        message += "Total Service Charge: " + dollarfmt.format(myAccount. getSerivceCharge()) + "\n";
       
        JOptionPane.showMessageDialog (null, message);
    }
    
    public static void processD(double transAmt){
        double currentCharge;
        String message;
        
        currentCharge = 0.10;
        myAccount.setCharge(currentCharge);
        dollarfmt = NumberFormat.getCurrencyInstance();
        message = myAccount.getName() + "'s account-\n";
        message += "Transaction: Deposit in amount of " + dollarfmt.format(transAmt) + "\n";

        
        if (myAccount.getBalance() < 50)
        {
            message += "Warning: Balance below $50 \n";
        }   
        message += "Current Balance: " + dollarfmt.format(myAccount.getBalance()-myAccount. getSerivceCharge()) + "\n";
        message += "Service Charge: Deposit --- charge " + dollarfmt.format(currentCharge) + "\n";   
        message += "Total Service Charge: " + dollarfmt.format(myAccount. getSerivceCharge()) + "\n";
        JOptionPane.showMessageDialog (null, message);
    }   
    
    public static void totTransaction(){
        String totalMessage;   
        double totalBalance;
        
        dollarfmt = NumberFormat.getCurrencyInstance();
        totalBalance = myAccount.getBalance() - myAccount.getSerivceCharge();         
        totalMessage = "Transaction: End \n";
        totalMessage += "Current Balance: " + dollarfmt.format(myAccount. getBalance()-myAccount. getSerivceCharge()) + "\n";//*****************************************
        totalMessage += "Total Service Charges: " + dollarfmt.format(myAccount. getSerivceCharge()) + "\n";
        totalMessage += "Final Balance: " + dollarfmt.format(totalBalance) + "\n";
       textArea.setText(totalMessage);
        
    }
    
    public static void listTransactions()
    {
        frame.setVisible(false);
        String messageList = "Account:" + myAccount.getName() + "\n";    
        messageList += "Balance: " + dollarfmt.format(myAccount.getBalance()-myAccount. getSerivceCharge()) + "\n";
        messageList += "Service Charge: " + dollarfmt.format(myAccount. getSerivceCharge()) + "\n\n";
        messageList += "List of all transactions:\n\n";
        int num;
        dollarfmt = NumberFormat.getCurrencyInstance();
 
        messageList += "ID         Type                     Amount \n";
        
        for(num = 0; num < myAccount.getTransCount(); num++)
        {
            String check = "Check";
            String svcCharge = "Svc. Charge";
            String deposit = "Deposit";
            
            if(myAccount.tList[num].getTransId() == 1)
            {
                messageList += String.format("%-6d     %-20s %10s \n",        
                myAccount.tList[num].getTransNumber(), check,
                dollarfmt.format(myAccount.tList[num].getTransAmount()));
            }
            
            if(myAccount.tList[num].getTransId() == 2)
            {
                messageList += String.format("%-6d     %-20s %10s \n",           
                myAccount.tList[num].getTransNumber(), deposit,
                dollarfmt.format(myAccount.tList[num].getTransAmount()));
            }
            
            if(myAccount.tList[num].getTransId() == 3)
            {
                messageList += String.format("%-6d     %-20s %10s \n",      
                myAccount.tList[num].getTransNumber(), svcCharge,   
                dollarfmt.format(myAccount.tList[num].getTransAmount()));
            }
        }

        textArea.setText(messageList);
        
        frame.setVisible(true);
    }
    
    public static void listChecks()
    {
        frame.setVisible(false);

        String messageList = "Listing all Checks for " + myAccount.getName() + "\n";
        messageList += "Balance: " + dollarfmt.format(myAccount.getBalance()-myAccount. getSerivceCharge()) + "\n";
        messageList += "Service Charge: " + dollarfmt.format(myAccount. getSerivceCharge()) + "\n\n";
        int num;
        dollarfmt = NumberFormat.getCurrencyInstance();
        
        messageList += "ID        Check         Amount \n";
        
        for(num = 0; num < myAccount.getTransCount(); num++)
        {
            if (myAccount.tList[num].getTransId() == 1)
            {

                newChk = (Check)myAccount.tList[num];
                
                messageList += String.format("%-5d     %-7d     %8s \n", myAccount.tList[num].getTransNumber(), newChk. getCheckNumber(), dollarfmt.format(myAccount.tList[num].getTransAmount()));
            }
        }
           
        textArea.setText(messageList);
        
        frame.setVisible(true); 
    }
    
    public static void listDeposits()
    {
        frame.setVisible(false);
        
        String messageList = "Listing all Deposits for " + myAccount.getName() + ":\n";
        messageList += "Balance: " + dollarfmt.format(myAccount.getBalance()-myAccount. getSerivceCharge()) + "\n";
        messageList += "Service Charge: " + dollarfmt.format(myAccount. getSerivceCharge()) + "\n\n";
        int num;
        dollarfmt = NumberFormat.getCurrencyInstance();
        
        messageList += "ID  Type      Checks     Cash      Amount \n";
        
        for(num = 0; num < myAccount.getTransCount(); num++)
        {
            if (myAccount.tList[num].getTransId() == 2)
            {
                newDep = (Deposit)myAccount.tList[num];
                
                messageList += String.format("%-2d  %-9s %6s  %7s   %9s \n", myAccount.tList[num].getTransNumber(), "Deposit", dollarfmt.format(newDep.getCheckAmt()), dollarfmt.format(newDep.getCashAmt()), dollarfmt.format(myAccount.tList[num].getTransAmount()));
            }
        }
         
        textArea.setText(messageList);
        frame.setVisible(true);
    }
    
    public static void readFile()
    {
        pickAFile(1);
        try
        {
            FileInputStream inputFile = new FileInputStream(filename);
            ObjectInputStream inData = new ObjectInputStream(inputFile);
                list = (ArrayList<CheckingAccount>)inData.readObject();
                inData.close();
            
                    }    
        catch(ClassNotFoundException e)	
                 { 
                     System.out.println(e);
                 }
                catch (IOException e) 
                 { 
                     System.out.println(e);
                 }
    }
    
    public static void writeToFile()
    {
        changed = false;
        
        pickAFile(2);
        try
        {
            FileOutputStream outputFile = new FileOutputStream(filename);
            ObjectOutputStream outData = new ObjectOutputStream(outputFile);
                outData.writeObject(list);
                outData.close();
            }
        
        
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
     private static class SetFocus implements AncestorListener//******************************************************
 {
        public void ancestorAdded(AncestorEvent e) 
  {
            JComponent component = e.getComponent();
             component.requestFocusInWindow();
  }
         public void ancestorMoved(AncestorEvent e)
         {     }
         public void ancestorRemoved(AncestorEvent e) 
         {     }
 }
    public static void pickAFile(int ioOption)
    {
        int status, confirm;
        
        
        String message = "Would you like to use current default file: \n" + filename;
        
        confirm = JOptionPane.showConfirmDialog(null, message);
        
        if(confirm == JOptionPane.YES_OPTION)
        return;
        JFileChooser chooser = new JFileChooser();
        if(ioOption == 1)
        {
            status = chooser.showOpenDialog(null);
        }        
        else
         status = chooser.showSaveDialog(null);
        
        if(status == JFileChooser.APPROVE_OPTION)
        {
            File file = chooser.getSelectedFile();
            filename = file.getPath();
        }
    }   
}
