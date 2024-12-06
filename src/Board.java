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

    private final JButton startButton = new JButton("Start new game");
    private final JButton viewHighScore = new JButton("View high scores");
    private final JButton aboutRules = new JButton("About/Rules");

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
        setLayout(new BorderLayout()); //Kommer behöva hjälp att räkna på komponenters plats sen.... Jennifer
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        getContentPane().setBackground(new Color(255, 222, 222)); //Tyckte grisrosa var passande

        //Buttons
        add(startPanel, BorderLayout.SOUTH);
        startPanel.setLayout(new GridLayout(3, 1));
        startPanel.add(startButton); startPanel.add(viewHighScore);
        startPanel.add(aboutRules);

        startButton.addActionListener(l -> {
            remove(startPanel);
            displayChooseDifficulty();
            revalidate();
            repaint();
        });
    }

    public void displayChooseDifficulty() {
        add(chooseDifficultyPanel, BorderLayout.SOUTH);
        chooseDifficultyPanel.add(levelEasy); chooseDifficultyPanel.add(levelHard);

        levelEasy.addActionListener(l -> chooseDifficulty());
        levelHard.addActionListener(l -> chooseDifficulty());
    }

    public void chooseDifficulty() {
        if(levelEasy.isSelected()) {
            difficulty = DifficultyLevel.EASY;
        } else if(levelHard.isSelected()) {
            difficulty = DifficultyLevel.HARD;
        }
        remove(chooseDifficultyPanel);
        revalidate();
        repaint();
        displaChooseTheme();
    }

    public void displaChooseTheme() {
        add(chooseThemePanel, BorderLayout.SOUTH);
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