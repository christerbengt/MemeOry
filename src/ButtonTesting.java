import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonTesting extends JFrame implements ActionListener {
    public ButtonTesting() {
        //Card card=new Card();

        this.setLayout(new FlowLayout());
        JPanel panel=new JPanel();
        JButton[] cardButton;
        this.setSize(900,750);
        panel.setSize(850,700);
        panel.setLayout(new GridLayout(6, 6));
        Card[] cards;
        /*for(int i=0;i<cards.length;i++) {
            cards[i] = new JButton();
            cards[i].setPreferredSize(new Dimension(140,140));
            cards[i].putClientProperty("ButtonID", buttonID);
           // card.createCard("animals",buttonID, cards[i]);
            panel.add(cards[i]);
            if (i % 2 == 1) {
                buttonID++;
            }
        }*/
        //cards.setSize(140,140);
        //card.createCard("Characters", 1, button);
        CardFactory factory=new CardFactory();

        cards=factory.getMemoryCards(24, "characters");
        //cardButton=new JButton[cards.length];
        for (Card card : cards) {
            JButton button = card.getButton();
            //button.setIcon(card.getFront());
            panel.add(button);
            button.addActionListener( this);
        }

        //panel.add(button);
        this.add(panel);
        //this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        Card clickedCard = (Card) button.getClientProperty("card");
        if (!clickedCard.getFlipped()){
            clickedCard.setFlipped(true);
        }
        else{
            clickedCard.setFlipped(false);
        }
    }

    public static void main(String[] args) {
        new ButtonTesting();
    }


}
