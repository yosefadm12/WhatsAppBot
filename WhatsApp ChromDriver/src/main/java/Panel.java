import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    //this is a constructor for making JPanels

    public Panel(Color color, int x, int y, int w, int h) {
        this.setBackground(color);
        this.setBounds(x, y, w, h);
    }
}
