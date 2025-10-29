package flyweight;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Document implements Serializable {
    private static final long serialVersionUID = 1L;

    private static class CharacterData implements Serializable {
        char character;
        CharacterProperty property;

        CharacterData(char c, CharacterProperty p) {
            this.character = c;
            this.property = p;
        }

        @Override
        public String toString() {
            return character + " [" + property + "]";
        }
    }

    private final List<CharacterData> content = new ArrayList<>();

    public void addCharacter(char c, CharacterProperty property) {
        content.add(new CharacterData(c, property));
    }

    public void display() {
        for (CharacterData data : content) {
            System.out.println(data);
        }
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
            System.out.println("Document saved as " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Document loadFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            System.out.println("Document loaded from " + filename);
            return (Document) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
