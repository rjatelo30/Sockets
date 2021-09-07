import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerProtocol {

    public static void main(String args[]) {

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
        myFrame.add(submit);

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(5555);
        } catch (IOException e) {
            System.out.println("Could not listen on port: " + 5555 + ", " + e);
            System.exit(1);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: " + 5555 + ", " + e);
            System.exit(1);
        }

        try {
            DataInputStream is = new DataInputStream(
                    new BufferedInputStream(clientSocket.getInputStream()));
            PrintStream os = new PrintStream(
                    new BufferedOutputStream(clientSocket.getOutputStream(), 1024), false);
            KKProtocol kkp = new KKProtocol();
            String inputLine, outputLine;

            outputLine = kkp.processInput(null);
            os.println(outputLine);
            os.flush();

            while ((inputLine = is.readLine()) != null) {
                outputLine = kkp.processInput(inputLine);
                os.println(outputLine);
                os.flush();
                if (outputLine.equals("Bye"))
                    break;
            }
            os.close();
            is.close();
            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
   }
}