import javax.swing.*;
import java.awt.*;

public class Board extends JFrame {

    private Card[][] board;
    private final Player player = new Player();
    private DifficultyLevel difficulty;
    private CardTheme theme;
    private int seconds;

    private final JButton startButton = new JButton("Start new game");
    private final JButton viewHighScore = new JButton("View high scores");
    private final JButton aboutRules = new JButton("About/Rules");

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
        getContentPane().setBackground(new Color(255, 222, 222)); //Tyckte grisrosa var passande

        //Buttons

    }

    public static void main(String[] args) {
        Board MemeOry = new Board();
    }
}