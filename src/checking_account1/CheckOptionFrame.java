
package checking_account1;

import java.awt.*; 
import javax.swing.*;
import java.awt.event.*;

public class CheckOptionFrame extends JFrameListener
{   
    public static final int HEIGHT = 400; 
    public static final int WIDTH = 500;  
    private JMenuBar menuBar; 
    private JMenu tMenu, accMenu, fMenu ;
    private JMenuItem  findAccount,listAccount,addNewAccount, readFile, writeFile, listAccountTrans, listAllChecks, listAllDeposits, addNewTrans;    
   
    
    public CheckOptionFrame(String title)
    {
        super(title);     
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
        
        fMenu = new JMenu("File");       
        accMenu = new JMenu("Account");     
        tMenu = new JMenu("Transactions");
          
        MenuListener menuListen = new MenuListener();    
        addNewAccount = new JMenuItem("Add new account");    
        addNewAccount.addActionListener(menuListen);
        accMenu.add(addNewAccount);
        
        listAccountTrans = new JMenuItem("List account transactions");
        listAccountTrans.addActionListener(menuListen);
        accMenu.add(listAccountTrans);
        
        listAllChecks = new JMenuItem("List all checks");   
        listAllChecks.addActionListener(menuListen);
        accMenu.add(listAllChecks);
        
        listAllDeposits = new JMenuItem("List all deposits");    
        listAllDeposits.addActionListener(menuListen);
        accMenu.add(listAllDeposits);
        
        listAccount = new JMenuItem("List all accounts");   
        listAccount.addActionListener(menuListen);
        accMenu.add(listAccount);
        
        findAccount = new JMenuItem("Find an account");    
        findAccount.addActionListener(menuListen);
        accMenu.add(findAccount);
        
        addNewTrans = new JMenuItem("Add transaction");   
        addNewTrans.addActionListener(menuListen);
        tMenu.add(addNewTrans);
        
        readFile = new JMenuItem("Read from file");   
        readFile.addActionListener(menuListen);
        fMenu.add(readFile);
        
        writeFile = new JMenuItem("Write to file");    
        writeFile.addActionListener(menuListen);
        fMenu.add(writeFile);
        
        menuBar = new JMenuBar();     
        menuBar.add(fMenu);    
        menuBar.add(accMenu);    
        menuBar.add(tMenu);      
        setJMenuBar(menuBar);
    }    
    
    private class MenuListener implements ActionListener{
        public void actionPerformed(ActionEvent event)
        {
            String source = event.getActionCommand();
            
            if(source.equals("Add new account"))
            {
                Checking_Account1.newAccount();
            }             else
                if(source.equals("List account transactions"))
                {
                    Checking_Account1.listTransactions();
                }             else
                if(source.equals("List all checks"))
                {
                    Checking_Account1.listChecks();
                }             else
                if(source.equals("List all deposits"))
                {
                    Checking_Account1.listDeposits();
                }             else
                if(source.equals("List all accounts"))
                {
                    Checking_Account1.listAcc();
                }                  else
                if(source.equals("Find an account"))
                {
                    Checking_Account1.findAcc();
                }             else
                if(source.equals("Add transaction"))
                {
                    Checking_Account1.processT();
                }             else
                if(source.equals("Read from file"))
                {
                    Checking_Account1.readFile();
                }             else
                if(source.equals("Write to file"))
                {
                    Checking_Account1.writeToFile();
                }
        }
    }
}
