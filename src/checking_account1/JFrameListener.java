
package checking_account1;

import java.awt.*; 
import java.awt.event.*;
import javax.swing.*; 

public class JFrameListener extends JFrame
{
  public JFrameListener(String name)
    {
   super(name);
   FrameListener frameListen = new FrameListener();
   addWindowListener(frameListen);
    }     
    private class FrameListener extends WindowAdapter{
        
     @Override
     public void windowClosing(WindowEvent e)
        {
         if(Checking_Account1.changed == true)
            {
         int ask;
         String question;
          question = "Would you like to save your changes?";
          ask = JOptionPane.showConfirmDialog(null, question, "Close?", JOptionPane.YES_NO_OPTION);
        if(ask == JOptionPane.YES_OPTION)
           {
              Checking_Account1.writeToFile();
           }
         else
            {
             Checking_Account1.frame.setVisible(false);
             System.exit(0);
             }
            }
            else
            {
             Checking_Account1.frame.setVisible(false);
             System.exit(0);
            }
        }
    }
}
