import javax.swing.*;
import java.awt.*;

public class ButtonTesting extends JFrame {
    public ButtonTesting() {
        Card card=new Card();

        this.setLayout(new FlowLayout());
        JPanel panel=new JPanel();
        JButton[] cards=new JButton[24];
        this.setSize(900,750);
        panel.setSize(850,700);
        panel.setLayout(new GridLayout(6, 6));
        int buttonID=0;
        for(int i=0;i<cards.length;i++) {
            cards[i] = new JButton();
            cards[i].setPreferredSize(new Dimension(140,140));
            cards[i].putClientProperty("ButtonID", buttonID);
            card.createCard("animals",buttonID, cards[i]);
            panel.add(cards[i]);
            if (i % 2 == 1) {
                buttonID++;
            }
        }
        //cards.setSize(140,140);
        //card.createCard("Characters", 1, button);

        //panel.add(button);
        this.add(panel);
        //this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new ButtonTesting();
    }
}
