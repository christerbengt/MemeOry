import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public FileHandler() {


    }


    public List<String> readText(Path p) {
        List<String> fileToText = new ArrayList<>();

        try {
            if (!Files.exists(p)) {
                Files.createFile(p);
            }
            fileToText = Files.readAllLines(p);
        } catch (IOException e) {
            System.out.println("Något gick fel när filen skulle läsas eller skapas");
            throw new RuntimeException(e);
        }
        return fileToText;
    }

    public Icon[] iconReader(Path p, int amount) {
        Icon[] icons = new Icon[amount];
        for (int i = 0; i < amount; i++) {
            String imagePath = p.toString() + "/" + (i + 1) + ".png";
            System.out.println(imagePath);
            try {
                Icon originalIcon = new ImageIcon(imagePath);
                icons[i] = originalIcon;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return icons;
    }

    public void write(Path p, String userName, int score) {
        int scoreFromFile = 0;
        List<String> lines;
        int lineIndex = -1;

        try {
            if (!Files.exists(p)) {
                Files.createFile(p);
            }
            lines = Files.readAllLines(p);
        } catch (IOException e) {
            System.out.println("Något gick fel när filen skulle läsas eller skapas");
            throw new RuntimeException(e);
        }

        for (int i = 0; i < lines.size(); i++) {

            String[] parts = lines.get(i).split(",");
            try {
                scoreFromFile = Integer.parseInt(parts[1].trim());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
            if (parts.length > 1 && scoreFromFile < score) {
                lineIndex = i;
                break;
            }
        }

        if (lineIndex < 5) {
            lines.add(lineIndex + 1, userName + "," + score);
        } else {

            lines.add(userName + "," + score);
        }
        try {
            Files.write(p, lines);
        } catch (IOException e) {
            System.out.println("Något gick fel när filen skulle skrivas");
        }
    }

}
