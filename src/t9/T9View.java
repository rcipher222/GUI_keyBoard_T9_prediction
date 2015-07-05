/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t9;

/**
 *
 * @author rb
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

    public class T9View extends JFrame implements ActionListener
{   String str="",buffer="";
    private final JButton[] buttons;
    private final JTextField display,predict;
    JButton backspace, spaceBar; 
    public T9View()
    {
        display = new JTextField();
        display.setEditable( false );
        display.setHorizontalAlignment(JTextField.RIGHT);
        
        
        predict = new JTextField();
        predict.setEditable( false );
        predict.setHorizontalAlignment(JTextField.RIGHT);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout( new GridLayout(0, 10) );
        buttons = new JButton[31];
         int i=0;
         String s="qwertyuiop asdfhjkl  zxcvbnm  ";
        for (i = 0; i <s.length(); i++)
        {
            String text = ""+s.charAt(i);
            JButton button = new JButton( text );
            button.addActionListener( this );
            button.setMnemonic( text.charAt(0) );
            buttons[i] = button;
            buttonPanel.add( button );
        }
        spaceBar = new JButton("Spacebar Key");
        
        spaceBar.addActionListener(this);

        backspace = new JButton("BackSpace Key");
      
        backspace.addActionListener(this);
        
        //buttonPanel.setLayout(new GridLayout(10,19));//setLayout( new GridLayout(0, 9) );
        
       getContentPane().add(predict, BorderLayout.PAGE_START);
       getContentPane().add(display, BorderLayout.PAGE_END);
       getContentPane().add(buttonPanel, BorderLayout.CENTER);
       getContentPane().add(spaceBar,BorderLayout.EAST);
       getContentPane().add(backspace,BorderLayout.WEST);
        setResizable( false );
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        JButton source = (JButton)e.getSource();
        buffer+=source.getActionCommand();
           if (e.getSource() == backspace) {
          //   buffer="backsp" ; 
           buffer = buffer.substring(0,buffer.length()-14);
           display.setText(""+buffer);
                 }
            else if (e.getSource() == spaceBar){
                
           buffer = buffer.substring(0,buffer.length()-12);
           buffer += " ";
           display.setText(""+buffer);
            }
            else{
                display.setText(""+buffer);
            }
    try {
        str=T9algo.predict(buffer);
    } catch (IOException ex) {
        Logger.getLogger(T9View.class.getName()).log(Level.SEVERE, null, ex);
    }
        predict.setText(str);
   //      display.setText(""+buffer);
      //  display.replaceSelection(buffer );
    }

    public static void run()
    {
        T9View frame = new T9View();
        frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
        frame.pack();
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
    }
}

