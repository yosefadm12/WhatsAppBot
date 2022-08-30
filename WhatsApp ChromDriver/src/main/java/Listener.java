
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Listener implements ActionListener { // this class implements ActionListener so that you can listen for Actions
    JButton btn;

    @Override
    public void actionPerformed(ActionEvent e) {
        btn = (JButton) e.getSource(); //this makes the btn in this class equal to the same button that was clicked so that we can check the buttons name and source


        if (btn.getText().equalsIgnoreCase("Start")) {  // this checks if the button that was clickeds name was "start" if it is then go inside the If
            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe"); // this sets the property of the webDriver and also the location to your ChromeDriver.exe
            Main.frame.driver = new ChromeDriver(); // getting the driver from frame and making = new ChromeDriver();

            Main.frame.driver.get("https://web.whatsapp.com/"); // opening the WhatsAppWeb
            Main.frame.onWeb();// calling the function thats in the Frame class called onWeb();

        }// close the first if. If we didnt go inside the if then the button we clicked was not "start


        if (btn.getText().equalsIgnoreCase("send")) {// this checks if the button that was clickeds name was "send" if it is then go inside the If
            String phoneNumber = Main.frame.text1.getText(); // making a string phoneNumber to equal to the text1 JtextField in the Frame class
            String text = Main.frame.text2.getText();// making a string Text equal to the text2 JtextField in the Frame class

            if (phoneNumber.length() == 0) { //checks to see if phoneNumber is empty
                Main.frame.errorMsg("Please enter a phone number", Color.red); // if yes send an errorMsg from class Frame
            } else if (phoneNumber.length() != 10 && !phoneNumber.matches("[0-9]")) {// checks to see that the numbers are numbers and not letters and that the number length is 10
                Main.frame.errorMsg("Phone Number Must Be ONLY Numbers From 0-9 And ONLY Up To 10 Numbers", Color.red); //sends an errorMsg from class Frame
            } else if (text.length() == 0) {// checks to see if he entered a message or not
                Main.frame.errorMsg("Please enter a message", Color.red); // sends an errorMsg from classFrame if he didnt enter a message
            } else {
                if (Main.frame.driver.getCurrentUrl() != null) {// checks to make sure that the Driver is on whatsapp.com
                    Main.frame.navigate(phoneNumber); // sending the phone number to the Navigate fucntion thats in the Frame class
                    Main.frame.sendMsg(text);// sending the text to the sendMsg function thats in the Frame class
                    Main.frame.errorMsg("You have successfully sent " + text + " to " + phoneNumber, Color.green); // sending this errorMsg to let the user know that the message was sent successfully

//                    try {  // this is to make sure that it got sent correctly it makes it wait 1 second then it closes the window
//                        wait(1000);
//                    } catch (InterruptedException ex) {
//                        ex.printStackTrace();
//                    }

                }
            }


        }
    }
}
