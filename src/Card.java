import javax.swing.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Card {
    Boolean isFlipped = false;
    Boolean isMatched;
    Icon front;
    Icon back;
    JButton button;
    int cardID;

    public Card(JButton b, int id) {
        this.button = b;
        this.cardID = id;
        this.button.putClientProperty("card", this);

    }

    public Icon getFront() {
        return front;
    }

    public void setFront(Icon front) {
        this.front = front;
    }

    public Icon getBack() {
        return back;
    }

    public void setBack(Icon back) {
        this.back = back;
    }

    public Boolean getFlipped() {
        return isFlipped;
    }

    public void setFlipped(Boolean Flipped) {
        isFlipped = Flipped;
        if (isFlipped) {
            button.setIcon(front);
        } else {
            button.setIcon(back);
        }
    }

    public Boolean getMatched() {
        return isMatched;
    }

    public void setMatched(Boolean matched) {
        isMatched = matched;
    }

    public JButton getButton() {
        return button;
    }


    public int getID() {
        return cardID;
    }

}
