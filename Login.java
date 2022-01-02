import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.event.*;
import javax.swing.*;
public class Login {
    public static void main(String[] args){
        //JFrame login=new JFrame("Login");
        JPanel panel=new JPanel();
        JTextField loginName=new JTextField(20);
        JButton enter=new JButton("Login");
        
        panel.add(loginName);
        panel.add(enter);
       /* login.setSize(300, 100);
        login.add(panel);
        login.setVisible(true);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        */enter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){  
                try{
                ChatClient client=new ChatClient(loginName.getText());
                login.setVisible(false);
                login.dispose();
                }
                catch(Exception d){
                System.out.println(d);
                }
                
                }
            
        });
        loginName.addKeyListener(new KeyListener(){
            public void keyTyped(KeyEvent e){
                
            }
            public void keyPressed(KeyEvent e){
                 
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    try{
                 ChatClient client=new ChatClient(loginName.getText());
                login.setVisible(false);
                login.dispose();
            }
                  catch(Exception d){
                System.out.println(d);
                }
                
                }}
                 
            public void keyReleased(KeyEvent e){
                
            }
        });
}
}
