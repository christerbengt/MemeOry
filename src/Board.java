import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JFrame implements ActionListener {

    private Card[][] board;
    private final Player player = new Player();
    private DifficultyLevel difficulty;
    private CardTheme theme;
    private int seconds;

    private final JPanel startPanel = new JPanel();
    private final JPanel chooseDifficultyPanel = new JPanel();
    private final JPanel chooseThemePanel = new JPanel();
    private final JPanel boardPanel = new JPanel();

    private final JLabel startLabel = new JLabel("MemeOry");
    private final JButton startButton = new JButton("Start new game");
    private final JButton viewHighScoreButton = new JButton("View high scores");
    private final JButton aboutRulesButton = new JButton("About/Rules");

    private final JButton levelEasy = new JButton("Easy");
    private final JButton levelHard = new JButton("Hard");

    private final JButton themeAnimals = new JButton("Animals");
    private final JButton themeCharacters = new JButton("Characters");
    private Card[] cards;
    private Card cardToCheck1;
    private Card cardToCheck2;
    private boolean gameFinished = false;
    private boolean checkingMatch=false;
    private CardFactory factory = new CardFactory();


    public Board() {
        setTitle("MemeOry");
        setLayout(null); //Kommer behöva hjälp att räkna på komponenters plats sen.... Jennifer
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        getContentPane().setBackground(new Color(255, 222, 222)); //Tyckte grisrosa var passande.

        displayStartPanel();
    }


    public enum DifficultyLevel {
        EASY("easy", 12),
        HARD("hard", 24);

        public final String difficulty;
        public final int value;

        DifficultyLevel(String d, int v) {
            difficulty = d;
            value = v;
        }
    }

    public enum CardTheme {
        ANIMALS("animals"),
        CHARACTERS("characters");

        public final String theme;

        CardTheme(String t) {
            theme = t;
        }
    }

    public void displayStartPanel() {
        startPanel.setBounds(0, 0, 700, 700);
        startPanel.setLayout(null);
        startPanel.setBackground(new Color(255, 222, 222));
        add(startPanel);
        startLabel.setBounds(315, 0, 400, 350);
        startPanel.add(startLabel);
        startButton.setBounds(250, 200, 200, 100);
        viewHighScoreButton.setBounds(250, 300, 200, 100);
        aboutRulesButton.setBounds(250, 400, 200, 100);
        startPanel.add(startButton);
        startPanel.add(viewHighScoreButton);
        startPanel.add(aboutRulesButton);

        startButton.addActionListener(l -> {
            remove(startPanel);
            displayChooseDifficulty();
            revalidate();
            repaint();
        });
    }

    public void displayChooseDifficulty() {
        chooseDifficultyPanel.setBounds(0, 0, 700, 700);
        chooseDifficultyPanel.setLayout(null);
        chooseDifficultyPanel.setBackground(new Color(255, 222, 222));
        add(chooseDifficultyPanel);
        levelEasy.setBounds(250, 200, 200, 100);
        levelHard.setBounds(250, 350, 200, 100);
        chooseDifficultyPanel.add(levelEasy);
        chooseDifficultyPanel.add(levelHard);

        levelEasy.addActionListener(l -> {
            difficulty = DifficultyLevel.EASY;
            remove(chooseDifficultyPanel);
            displayChooseTheme();
            revalidate();
            repaint();
        });

        levelHard.addActionListener(l -> {
            difficulty = DifficultyLevel.HARD;
            remove(chooseDifficultyPanel);
            displayChooseTheme();
            revalidate();
            repaint();
        });
    }
/* isSelected() fungerar tydligen med JRadioButtons, kan vara värt att ändra knapparna till det i de
    här fallen. Alltså svårighetsgrad och tema. Bara att ändra om nästa tycker det!
    public void chooseDifficulty() {
        if(levelEasy.isSelected()) {
            difficulty = DifficultyLevel.EASY;
        } else if(levelHard.isSelected()) {
            difficulty = DifficultyLevel.HARD;
        }
        remove(chooseDifficultyPanel);
        revalidate();
        repaint();
        displayChooseTheme();
    }
 */

    public void displayChooseTheme() {
        chooseThemePanel.setBounds(0, 0, 700, 700);
        chooseThemePanel.setLayout(null);
        chooseThemePanel.setBackground(new Color(255, 222, 222));
        add(chooseThemePanel);
        themeAnimals.setBounds(250, 200, 200, 100);
        themeCharacters.setBounds(250, 350, 200, 100);
        chooseThemePanel.add(themeAnimals);
        chooseThemePanel.add(themeCharacters);

        themeAnimals.addActionListener(l -> {
            theme = CardTheme.ANIMALS;
            remove(chooseThemePanel);
            setBoard(difficulty, theme);
            revalidate();
            repaint();
        });

        themeCharacters.addActionListener(l -> {
            theme = CardTheme.CHARACTERS;
            remove(chooseThemePanel);
            setBoard(difficulty, theme);
            revalidate();
            repaint();
        });
    }

    /*private void chooseTheme() {
        if(themeAnimals.isSelected()) {
            theme = CardTheme.ANIMALS;
        } else if(themeCharacters.isSelected()) {
            theme = CardTheme.CHARACTERS;
        }

        remove(chooseThemePanel);
        setBoard(difficulty, theme);
        revalidate();
        repaint();
    }*/
    @Override
    public void actionPerformed(ActionEvent e) {
        if (checkingMatch) {
            return; // Ignore events while a match is being checked
        }
        JButton button = (JButton) e.getSource();
        Card clickedCard = (Card) button.getClientProperty("card");
        if (clickedCard == cardToCheck1 || clickedCard == cardToCheck2 || (clickedCard.isMatched)) {
            return;
        }
        clickedCard.setFlipped(!clickedCard.getFlipped());
        if (cardToCheck1 == null) {
            cardToCheck1 = clickedCard;
        } else if (cardToCheck2 == null) {
            cardToCheck2 = clickedCard;
            checkIfMatched(cardToCheck1, cardToCheck2);
        }

    }

    public void setBoard(DifficultyLevel difficulty, CardTheme theme) {
        cards = factory.getMemoryCards(difficulty.value, theme.theme);
        for (Card card : cards) {
                JButton button = card.getButton();
                card.getButton().setIcon(card.getBack());
                boardPanel.add(button);
                button.addActionListener(this);
        }

        boardPanel.setBounds(0, 0, 700, 700);
        boardPanel.setLayout(new FlowLayout());
        boardPanel.setBackground(new Color(255, 222, 222));
        add(boardPanel);

        //Behöver vi en displayGamePanel? Lite samma som denna?
    }

    private void checkIfMatched (Card card1, Card card2) {
        enableButtons(false);
        if (card1.getID() == card2.getID()) {
            card1.setMatched(true);
            card2.setMatched(true);
            cardToCheck1=null;
            cardToCheck2=null;
            winChecker();
        }
        else{
            Timer timer = new Timer(1000, evt -> {
                card1.setFlipped(false);
                card2.setFlipped(false);
                enableButtons(true);
                cardToCheck1=null;
                cardToCheck2=null;

                ((Timer) evt.getSource()).stop();
            });

            timer.setRepeats(false);
            timer.start();
        }

    }

    private void winChecker() {
        gameFinished = true;
        for (Card card : cards) {
            if (!card.isMatched) {
                gameFinished = false;
                enableButtons(true);
            }
        }
        if (gameFinished) {
            //winPanel här?
            //WOOP KOLLA HIT
            //WOOP SAKNAS EN WINPANEL
            //WOOOP
        }
    }

    private void enableButtons(Boolean trueOrFalse) {
        if (trueOrFalse) {
            checkingMatch=false;
        }
        else{
           checkingMatch=true;
        }

    }

    public static void main(String[] args) {
        Board MemeOry = new Board();
    }
}