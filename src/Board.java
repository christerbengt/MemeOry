import javax.swing.*;
import java.awt.*;

public class Board extends JFrame {

    private Card[][] board;
    private final Player player = new Player();
    private DifficultyLevel difficulty;
    private CardTheme theme;
    private int seconds;

    private final JPanel startPanel = new JPanel();
    private final JPanel chooseDifficultyPanel = new JPanel();
    private final JPanel chooseThemePanel = new JPanel();

    private final JLabel startLabel = new JLabel("MemeOry");
    private final JButton startButton = new JButton("Start new game");
    private final JButton viewHighScoreButton = new JButton("View high scores");
    private final JButton aboutRulesButton = new JButton("About/Rules");

    private final JButton levelEasy = new JButton("Easy");
    private final JButton levelHard = new JButton("Hard");

    private final JButton themeAnimals = new JButton("Animals");
    private final JButton themeCharacters = new JButton("Characters");


    public Board(){
        displayStartPanel();
    }

    public enum DifficultyLevel {
        EASY ("easy"),
        HARD ("hard");

        public final String difficulty;

        DifficultyLevel(String d) {
            difficulty = d;
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
        setTitle("MemeOry");
        setLayout(null); //Kommer behöva hjälp att räkna på komponenters plats sen.... Jennifer
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        getContentPane().setBackground(new Color(255, 222, 222)); //Tyckte grisrosa var passande.

        startPanel.setBounds(0, 0, 700, 700);
        startPanel.setLayout(null);
        startPanel.setBackground(new Color(255, 222, 222));
        add(startPanel);
        startLabel.setBounds(315, 0, 400, 350);
        startPanel.add(startLabel);
        startButton.setBounds(250, 200, 200, 100); viewHighScoreButton.setBounds(250, 300, 200, 100);
        aboutRulesButton.setBounds(250, 400, 200, 100);
        startPanel.add(startButton); startPanel.add(viewHighScoreButton);
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

        themeAnimals.addActionListener(l -> chooseTheme());
        themeCharacters.addActionListener(l -> chooseTheme());
    }

    private void chooseTheme() {
        if(themeAnimals.isSelected()) {
            theme = CardTheme.ANIMALS;
        } else if(themeCharacters.isSelected()) {
            theme = CardTheme.CHARACTERS;
        }

        remove(chooseThemePanel);
        setBoard(difficulty, theme);
        revalidate();
        repaint();
    }

    public void setBoard(DifficultyLevel difficulty, CardTheme theme) {

    }

    public static void main(String[] args) {
        Board MemeOry = new Board();
    }
}