import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CardFactory {
    private Path animal= Paths.get("src/MemoryImg/animals");
    private Path character=Paths.get("src/MemoryImg/characters");
    private Icon[] characters;
    private Icon[] animals;
    private String categoryChoice;
    JButton[] guiButtons;
    Card[] cards;


    public CardFactory() {
        FileHandler files = new FileHandler();
        characters=files.iconReader(character, 12);
        animals=files.iconReader(animal, 12);


    }
        public Card[] getMemoryCards(int amount, String category) {

        this.categoryChoice=category;
        this.guiButtons = new JButton[amount];

        createCards();
        setIcons();
        
       return cards;
        }
        
        private void setIcons() {

            Icon[] tempIconHolder = new Icon[cards.length];
            switch (this.categoryChoice.toUpperCase()) {
                case "CHARACTERS":

                    tempIconHolder = characters;
                    break;
                case "ANIMALS":
                    tempIconHolder = animals;
                    break;
            }
            for(int i=0;i<cards.length;i++) {
                cards[i].setFront(tempIconHolder[cards[i].getID()]);
                }
            }
            private void createCards() {
                cards = new Card[guiButtons.length];
                int buttonID=0;
                for(int i=0;i<guiButtons.length;i++) {
                    guiButtons[i]=new JButton();
                    guiButtons[i].setPreferredSize(new Dimension(140,140));
                    Card card=new Card(guiButtons[i], buttonID);

                    this.cards[i] = card;
                    if (i % 2 == 1) {
                        buttonID++;
                    }
                }
            }
}