import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.event.*;
import javax.swing.*;
public class ChatClient extends JFrame implements Runnable {
    Socket socket;
    JTextArea ta;
    Thread thread;
    DataInputStream din;
    DataOutputStream dout;
    String LoginName;
    JButton logout,send;
    JTextField tf;
    
    
    ChatClient(String login) throws UnknownHostException, IOException {
        
        super(login);
        LoginName =login;
        addWindowListener(new WindowAdapter(){
        public void windowClosing(WindowEvent e){
            try{
            dout.writeUTF(LoginName + " " + "LOGOUT");
            System.exit(1);
        }
        catch(Exception f){
            System.out.println(f);
        }}
            });
        ta=new JTextArea(18,50);
        tf=new JTextField(50);
        
        tf.addKeyListener(new KeyListener(){
            public void keyTyped(KeyEvent e){
                
            }
            public void keyPressed(KeyEvent e){
                 
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                 try{
                 if(tf.getText().length()>0)
                   dout.writeUTF(LoginName+" "+"Data "+tf.getText().toString());
                tf.setText("");
                }
                catch(Exception d){
                System.out.println(d);
                }
                }}
                 
            public void keyReleased(KeyEvent e){
                
            }
        });
        
        send=new JButton("SEND");
        logout=new JButton("LOGOUT");
        
        send.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                if(tf.getText().length()>0)
                  dout.writeUTF(LoginName+" "+"Data "+tf.getText().toString());
                tf.setText("");
                }
                catch(Exception d){
                System.out.println(d);
                }
                
                }
            
        }
    );
        
        logout.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                dout.writeUTF(LoginName+" "+"LOGOUT");
                System.exit(1);
                }
                catch(Exception d){
                System.out.println(d);
                }
                
                }
            
        }
    );
        
        socket=new Socket("localhost",5216);
        din=new DataInputStream(socket.getInputStream());
        dout=new DataOutputStream(socket.getOutputStream());
        dout.writeUTF(LoginName);
        dout.writeUTF(LoginName + " " + "LOGIN");
        thread=new Thread(this);
        thread.start();
        setup();
    }
    private void setup(){
        setSize(600,400);
        JPanel panel=new JPanel();
        panel.add(new JScrollPane(ta));
        panel.add(tf);
        panel.add(send);
        panel.add(logout);
        add(panel);
        
        setVisible(true);
    }
     public void run(){
        while(true){
            try{
                ta.append(din.readUTF()+" \n");
                    
                }
            catch(Exception e){
                System.out.println(e);
            }
        }
     }
    
    }

