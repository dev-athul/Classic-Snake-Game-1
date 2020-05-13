import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame jframeObj = new JFrame();
        gPlay gPlayObj=new gPlay();

        jframeObj.setBounds(10,10,905,700);
        jframeObj.setBackground(Color.darkGray);
        jframeObj.setResizable(false);
        jframeObj.setVisible(true);
        jframeObj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframeObj.add(gPlayObj);
    }
}
