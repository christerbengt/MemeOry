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
    private final JPanel rulesPanel = new JPanel();
    private final JPanel aboutPanel = new JPanel();
    private final JPanel gameOverPanel = new JPanel();

    private final JLabel startLabel = new JLabel("MemeOry");
    private final JButton startButton = new JButton("Start new game");
    private final JButton rulesButton = new JButton("Rules");
    private final JButton aboutButton = new JButton("About");

    private final JButton levelEasy = new JButton("Easy");
    private final JButton levelHard = new JButton("Hard");

    private final JButton themeAnimals = new JButton("Animals");
    private final JButton themeCharacters = new JButton("Characters");
    private Card[] cards;
    private CardFactory factory = new CardFactory();

    private final JTextArea rulesTA = new JTextArea("RULES RULES RULES");
    private final JButton backButton = new JButton("Back");
    private final JTextArea aboutTA = new JTextArea("ABOUT US US US");

    private final JLabel thankYouLabel = new JLabel("Thanks for playing!");
    private final JLabel scoreLabel = new JLabel("Score: " + player.getScore());
    private final JButton playAgainButton = new JButton("Play Again");
    private final JButton exitButton = new JButton("Exit game");

    public Board(){
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
        EASY ("easy",12),
        HARD ("hard",24);

        public final String difficulty;
        public final int value;

        DifficultyLevel(String d, int v) {
            difficulty = d;
            value = v;
        }
    }

    public enum CardTheme {
        ANIMALS ("animals"),
        CHARACTERS ("characters");

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
        startButton.setBounds(250, 200, 200, 100); rulesButton.setBounds(250, 300, 200, 100);
        aboutButton.setBounds(250, 400, 200, 100);
        startPanel.add(startButton); startPanel.add(rulesButton);
        startPanel.add(aboutButton);

        startButton.addActionListener(l -> {
            remove(startPanel);
            displayChooseDifficulty();
            revalidate();
            repaint();
        });
        rulesButton.addActionListener(l -> {
            remove(startPanel);
            displayRulesPanel();
            revalidate();
            repaint();
        });
        aboutButton.addActionListener(l -> {
            remove(startPanel);
            displayAboutPanel();
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
        chooseDifficultyPanel.add(levelEasy); chooseDifficultyPanel.add(levelHard);

        levelEasy.addActionListener(l -> {difficulty = DifficultyLevel.EASY;
        remove(chooseDifficultyPanel);
        displayChooseTheme();
        revalidate();
        repaint();});

        levelHard.addActionListener(l -> {difficulty = DifficultyLevel.HARD;
            remove(chooseDifficultyPanel);
            displayChooseTheme();
            revalidate();
            repaint();});
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
        chooseThemePanel.add(themeAnimals); chooseThemePanel.add(themeCharacters);

        themeAnimals.addActionListener(l -> {theme = CardTheme.ANIMALS;
        remove(chooseThemePanel);
        setBoard(difficulty, theme);
        revalidate();
        repaint();});
        themeCharacters.addActionListener(l -> {theme = CardTheme.CHARACTERS;
            remove(chooseThemePanel);
            setBoard(difficulty, theme);
            revalidate();
            repaint();});
    }

    /*
    private void chooseTheme() {
        if(themeAnimals.isSelected()) {
            ;
        } else if(themeCharacters.isSelected()) {
            theme = CardTheme.CHARACTERS;
        }

        remove(chooseThemePanel);
        setBoard(difficulty, theme);
        revalidate();
        repaint();
    }
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        Card clickedCard = (Card) button.getClientProperty("card");
        clickedCard.setFlipped(!clickedCard.getFlipped());


    }

    public void setBoard(DifficultyLevel difficulty, CardTheme theme) {
        cards = factory.getMemoryCards(difficulty.value, theme.theme);
        for (Card card : cards) {
            JButton button = card.getButton();

            boardPanel.add(button);
            button.addActionListener(this);
        }

        boardPanel.setBounds(0, 0, 700, 700);
        boardPanel.setLayout(null);
        boardPanel.setBackground(new Color(255, 222, 222));
        add(boardPanel);

    }

    public void displayRulesPanel() {
        rulesPanel.setBounds(0, 0, 700, 700);
        rulesPanel.setLayout(null);
        rulesPanel.setBackground(new Color(255, 222, 222));
        add(rulesPanel);
        rulesTA.setBounds(315, 0, 400, 350);
        rulesPanel.add(rulesTA);
        backButton.setBounds(250, 200, 200, 100);
        rulesPanel.add(backButton);

        backButton.addActionListener(l -> {
            remove(rulesPanel);
            displayStartPanel();
            revalidate();
            repaint();
        });
    }

    public void displayAboutPanel() {
        aboutPanel.setBounds(0, 0, 700, 700);
        aboutPanel.setLayout(null);
        aboutPanel.setBackground(new Color(255, 222, 222));
        add(aboutPanel);
        aboutTA.setBounds(315, 0, 400, 350);
        aboutPanel.add(aboutTA);
        backButton.setBounds(250, 200, 200, 100);
        aboutPanel.add(backButton);

        backButton.addActionListener(l -> {
            remove(aboutPanel);
            displayStartPanel();
            revalidate();
            repaint();
        });
    }

    public void displayGameOverPanel(int score) {
        // Panel setup
        gameOverPanel.setBounds(0, 0, 700, 700);
        gameOverPanel.setLayout(null);
        gameOverPanel.setBackground(new Color(255, 222, 222));
        add(gameOverPanel);

        // Position components - matching style from displayStartPanel()
        thankYouLabel.setBounds(315, 0, 400, 350);  // Same position as startLabel in displayStartPanel
        scoreLabel.setBounds(315, 100, 400, 350);

        // Buttons - matching button positioning style
        playAgainButton.setBounds(250, 200, 200, 100);  // Same position as startButton
        exitButton.setBounds(250, 300, 200, 100);       // Same position as rulesButton

        // Add all components to panel
        gameOverPanel.add(thankYouLabel);
        gameOverPanel.add(scoreLabel);
        gameOverPanel.add(playAgainButton);
        gameOverPanel.add(exitButton);

        // Update score text
        scoreLabel.setText("Din poäng: " + score);

        // Button actions
        playAgainButton.addActionListener(l -> {
            remove(gameOverPanel);
            displayStartPanel();
            revalidate();
            repaint();
        });

        exitButton.addActionListener(l -> {
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        Board memeOry = new Board();
    }
}