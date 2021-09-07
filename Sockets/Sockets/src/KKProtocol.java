import javax.swing.*;
import java.net.*;
import java.io.*;

class KKProtocol extends javax.swing.JFrame{
    private static final int WAITING = 0;
    private static final int STUDENTNUMBER = 1;
    private static final int STUDENTNAME = 2;
    private static final int FACULTYCOURSEDEGREE = 3;
    private static final int THANKYOU = 4;
    private static final int ONEMESSAGE = 5;
    private static final int SESSIONEND = 6;
    private static final int SESSIONRENEWAL = 7;

    private int state = WAITING;

    String processInput(String theInput) {
        String theOutput = null;

        if (state == WAITING) {
            theOutput = "Please send your student number";
            state = STUDENTNUMBER;

        } else if (state == STUDENTNUMBER) {
            theOutput = "Please send your student name";
            state = STUDENTNAME;

        } else if (state == STUDENTNAME) {
            theOutput = " Please send your Faculty, Course and Degree";
            state = FACULTYCOURSEDEGREE;

        } else if (state == FACULTYCOURSEDEGREE) {
            theOutput = " Please send a Thank You note";
            state = THANKYOU;

        } else if (state == THANKYOU) {
            theOutput = " Please send all the above in one message";
            state = ONEMESSAGE;

        } else if(state == ONEMESSAGE) {
            theOutput = "Thank you for your submissions and time. Would you like to end the session or enter more data (y/n)";
            state = SESSIONRENEWAL;

        }else if (state == SESSIONRENEWAL) {
            if (theInput.equalsIgnoreCase("y")){
                state = WAITING;
            } else {
                theOutput = "Bye";
                state = SESSIONEND;
            }
        }
        return theOutput;
    }


}