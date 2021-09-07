import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ClientProtocol{

    public static void main(String[] args) {
        JFrame myFrame = new JFrame("Server");
        myFrame.setSize(1200, 800);
        myFrame.setLayout(new FlowLayout());
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);

        JTextArea textArea = new JTextArea(50,100);
        textArea.setLocation(400, 200);
        textArea.setSize(textArea.getPreferredSize());
        myFrame.add(textArea);

        JTextField textField = new JTextField(10);
        textField.setSize(150, 20);
        textField.setLocation(200, 150);
        myFrame.add(textField);

        JButton submit = new JButton("Submit");
        submit.setSize(100, 20);
        submit.setLocation(150, 450);
        submit.addActionListener();
        myFrame.add(submit);

        try {
            Socket kkSocket = new Socket("localhost", 5555);
            PrintStream os = new PrintStream(kkSocket.getOutputStream());
            DataInputStream is = new DataInputStream(kkSocket.getInputStream());
            StringBuffer buf = new StringBuffer(50);

            int c;
            String fromServer;

            while ((fromServer = is.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye"))
                    break;
                while ((c = System.in.read()) != '\n') {
                    buf.append((char)c);
                }
                System.out.println("Client: " + buf);
                os.println(buf.toString());
                os.flush();
                buf.setLength(0);
            }

            os.close();
            is.close();
            kkSocket.close();
        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (Exception e) {
            System.err.println("Exception:  " + e);
        }

    }

    public void actionPerformed(ActionListener e){

    }
}