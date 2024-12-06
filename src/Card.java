import javax.swing.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Card {
    Boolean isFLipped;
    Boolean isMatched;
    Path animal= Paths.get("src/MemoryImg/animals");
    Path character=Paths.get("src/MemoryImg/characters");
    int cardID;
    Icon[] characters;
    Icon[] animals;
    FileHandler files=new FileHandler();

    public Card(){
        characters=files.iconReader(character, 12);
        animals=files.iconReader(animal, 12);

    }

    public JButton createCard(String category, int cardID, JButton card) {

        //switch case?
        card.setIcon(animals[cardID]);

        return card;
    }
}
