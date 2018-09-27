
package checking_account1;

import java.awt.event.*;
import javax.swing.*; 
import java.awt.*; 

public class CheckOptionsPanel extends JPanel
{
    private JRadioButton  readFile, writeFile,enterTransaction , listTransaction, listCheck, listDeposit ;
    private JLabel prompt;
    
    public CheckOptionsPanel()
    {
      
     setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));  
     prompt = new JLabel ("Please choose one of the items:");          
     enterTransaction = new JRadioButton ("a) Enter Transaction");
     enterTransaction.setBackground(Color.green);
     listTransaction = new JRadioButton ("b) List All Transactions");
     listTransaction.setBackground(Color.green);
     listCheck = new JRadioButton ("c) List All Checks");
     listCheck.setBackground(Color.green);
     listDeposit = new JRadioButton("d) List All Deposits");
     listDeposit.setBackground(Color.green);    
     readFile = new JRadioButton ("e) Read from file");
     readFile.setBackground(Color.green);
     writeFile = new JRadioButton ("f) Write to the file");
     writeFile.setBackground(Color.green);
        
     ButtonGroup group = new ButtonGroup();
     group.add(enterTransaction);   
     group.add(listTransaction);    
     group.add(listCheck);     
     group.add(listDeposit);    
     group.add(readFile);     
     group.add(writeFile);
        
     TransactionListener listener = new TransactionListener();    
     enterTransaction.addActionListener(listener);  
     listTransaction.addActionListener(listener);  
     listCheck.addActionListener(listener);  
     listDeposit.addActionListener(listener); 
     readFile.addActionListener(listener);      
     writeFile.addActionListener(listener); 
         
     add (prompt);
     add (Box.createRigidArea (new Dimension (0, 10)));
     add (enterTransaction);
     add (Box.createRigidArea (new Dimension (0, 10)));
     add (listTransaction);
     add (Box.createRigidArea (new Dimension (0, 10)));
     add (listCheck);
     add (Box.createRigidArea (new Dimension (0, 10)));
     add (listDeposit);
     add (Box.createRigidArea (new Dimension (0, 10)));
     add (readFile);
     add (Box.createRigidArea (new Dimension (0, 10)));
     add (writeFile);   
     setBackground (Color.green);
     setPreferredSize (new Dimension(400, 250));
    }
    
    private class TransactionListener implements ActionListener{  
        public void actionPerformed(ActionEvent event) 
        {
            Object source = event.getSource();
            if (source == enterTransaction){
                Checking_Account1.processT();
            }
            
            else if (source == listTransaction){
                Checking_Account1.listTransactions();
            }
            
            else if (source == listCheck){
                Checking_Account1.listChecks();
            }
            
            else if (source == listDeposit){
                Checking_Account1.listDeposits();
            }
            
            else if (source == readFile){
                Checking_Account1.readFile();
            }
              else{
                Checking_Account1.writeToFile();
            }
        }
    }
}
