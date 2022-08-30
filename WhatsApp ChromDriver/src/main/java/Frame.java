import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Frame extends JFrame {


    Listener listener = new Listener();
    JButton start_btn;
    JButton send;
    JPanel mainPanel;
    JLabel error;
    JLabel label2;
    JLabel label1;
    JTextField text1;
    JTextField text2;
    WebDriver driver;


    public Frame() {
        //setting up the Main JFrame
        this.setResizable(false);
        this.setBackground(new Color(107, 103, 103));
        this.setLayout(null);
        this.setSize(1000, 1000);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);

        error = new JLabel("errorMessage"); //creating the errorMessage JLabel for future use

        mainPanel = new Panel(null, 0, 0, 1000, 1000);//creating the Main JPanel

        start_btn = new Button(Color.green, "Start", 450, 450, 100, 100); //creating the Start Button on the first page

        init(); // this function initiates some things
        initListener();// this function initiates the Listener
    }

    private void initListener() {
        //here we added to start_btn a listener so if anyone clicks on the start_btn it will send an event to the listener function in Class Listener
        start_btn.addActionListener(listener);
    }

    private void init() {
        mainPanel.setLayout(null);//setting the layout of the Main panel to null so that we can customize where we want to put it
        this.setContentPane(mainPanel);//sets the frames main panel to mainPanel
        mainPanel.add(start_btn);//adding the start_btn to mainPanel
    }

    public void onWeb() { //Everything that happens here is when you are already on the web page (web.whatsapp.com)
        start_btn.setVisible(false);
        send = new Button(Color.green, "Send", 400, 600, 100, 60); //creating the send button
        send.setVisible(true);

        text1 = new TextField(400, 440, 100, 30);// creating the phoneNumber text field
        text2 = new TextField(400, 470, 100, 30);// creating the text text field
        text1.setVisible(true);
        text2.setVisible(true);

        label1 = new JLabel("Phone Number");  // creating the labels for the phoneNumber
        label2 = new JLabel("Message");   // creating the labels for the text
        label1.setBounds(300, 443, 100, 20);
        label2.setBounds(320, 473, 60, 20);
        label1.setVisible(true);
        label2.setVisible(true);


        mainPanel.add(error);// adding the error label that we created in the constructor to the mainPane
        error.setVisible(false);//setting the visibility to false because this is when we move to the onWeb function

        // adding the rest of the labels/buttons/textField to the mainPanel
        mainPanel.add(send);
        mainPanel.add(label1);
        mainPanel.add(label2);
        mainPanel.add(text1);
        mainPanel.add(text2);

        send.addActionListener(listener); // connecting the send Button to a listener in the Listener Class
    }

    public void errorMsg(String msg, Color color) { //this function is what takes care of changing the error label to say whatever error you want to paste
        repaint(); // this is used to refresh the swing
        error.setText(msg);
        error.setForeground(color);
        error.setBounds(200, 700, 600, 50);
        error.setVisible(true);
    }

    String[] phoneNumber = new String[2];

    public void navigate(String tempNum) {  //this function is called when you need to navigate to the wep page with the phoneNumber you entered
        phoneNumber = tempNum.split(String.valueOf(0), 2);// this will split the phone number from (example) 0501938787 to 972501938787 so that you can connect correctly to the user
        phoneNumber[1] = "972" + phoneNumber[1];
        driver.get("https://web.whatsapp.com/send?phone=" + phoneNumber[1]); //opening the page to connect to the phoneNumber you entered
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); // this is just a timer to make sure that the page can load in time before it starts to look for other elements inside the HTML page
    }

    public void sendMsg(String text) {//this function sends the message to the phoneNumber you entered
        driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[1]/p")).sendKeys(text);// this finds the Writing area in whatsapp web page
        driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[2]/button/span")).click();// this finds the send button on the whatsapp web page


        try {
            TimeUnit.SECONDS.sleep(2);// this is a method to make a delay in the code so that everything can send properly
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        next();//Going to the next() function
    }


    public void next() { // this function is the last function to happen and it restarts the whole thing line requested on level 2
        driver.quit(); // this will close the chrome website that you are on

        //setting all the other components that we dont need on the first page to visible(false)
        text1.setVisible(false);
        text2.setVisible(false);
        label1.setVisible(false);
        label2.setVisible(false);
        send.setVisible(false);
        start_btn.setVisible(true);

        repaint();//refreshing the swing page
    }

}

