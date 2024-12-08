import javax.swing.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Card {
    Boolean isFlipped=false;
    Boolean isMatched;
    Icon front;
    Icon back;
    JButton button;



    public Card(JButton b, int id){
        this.button=b;
        this.cardID=id;
        this.button.putClientProperty("card", this);

    }

    public void setFront(Icon front) {
        this.front = front;
    }

    public void setBack(Icon back) {
        this.back = back;
    }

    public Icon getFront() {
        return front;
    }

    public Icon getBack() {
        return back;
    }

    public void setMatched(Boolean matched) {
        isMatched = matched;
    }

    public void setFlipped(Boolean Flipped) {
        isFlipped = Flipped;
        if (isFlipped) {
            button.setIcon(front);
        }
        else {
            button.setIcon(back);
        }
    }

    int cardID;

    public Boolean getFlipped() {
        return isFlipped;
    }

    public Boolean getMatched() {
        return isMatched;
    }


    public JButton getButton() {
        return button;
    }


    public int getID(){
        return cardID;
    }

}
