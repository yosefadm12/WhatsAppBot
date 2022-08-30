import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    //this is a constructor for making JButtons

    public Button(Color color, String name, int x, int y, int w, int h) {
        this.setText(name);
        this.setBackground(color);
        this.setBounds(x, y, w, h);
    }
}
