import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonTesting extends JFrame implements ActionListener {
    public ButtonTesting() {
        this.setLayout(new FlowLayout());
        JPanel panel = new JPanel();
        JButton[] cardButton;
        this.setSize(840, 840);
        panel.setPreferredSize(new Dimension(840, 840));
        panel.setLayout(new FlowLayout());
        Card[] cards;
        CardFactory factory = new CardFactory();

        cards = factory.getMemoryCards(12, "characters");

        for (Card card : cards) {
            JButton button = card.getButton();

            panel.add(button);
            button.addActionListener(this);
        }

        this.add(panel);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new ButtonTesting();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        Card clickedCard = (Card) button.getClientProperty("card");
        clickedCard.setFlipped(!clickedCard.getFlipped());
    }


}
