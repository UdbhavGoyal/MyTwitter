package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by udbhavgoyal on 16/11/15.
 */

@Component
public class EmailTest {

    @Autowired
    EmailAPI emailAPI;

    public EmailTest() {

    }

    @SuppressWarnings("resource")
    public void send(String toAddress,String password) {
        try {

            String toAddr = toAddress;
            String fromAddress = "udbhav12@gmail.com";

            // email subject
            String mailSubject = "Welcome to Twitter !";

            // email body
            String mailBody = "Congratulations ! You just created account on MyTwitter " + "\nYour login credentials are :\n" + "Email id :" + toAddr + "\nPassword : " + password ;

            emailAPI.readyToSendEmail(toAddr, fromAddress, mailSubject, mailBody);
        } catch (Exception e) {
            System.out.print("Error in email test " + e);
        }
    }
}