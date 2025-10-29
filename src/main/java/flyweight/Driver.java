package flyweight;

public class Driver {
    public static void main(String[] args) {

        CharacterPropertyFactory.resetFactory();

        Document document = new Document();

        CharacterProperty p1 = CharacterPropertyFactory.getCharacterProperty("Arial", "Red", 12);
        CharacterProperty p2 = CharacterPropertyFactory.getCharacterProperty("Calibri", "Blue", 14);
        CharacterProperty p3 = CharacterPropertyFactory.getCharacterProperty("Verdana", "Black", 16);
        CharacterProperty p4 = CharacterPropertyFactory.getCharacterProperty("Arial", "Blue", 12);

        String text = "HelloWorldCS5800";

        for (int i = 0; i < text.length(); i++) {
            CharacterProperty property;
            if (i % 4 == 0) property = p1;       // Arial, Red, 12
            else if (i % 4 == 1) property = p2;  // Calibri, Blue, 14
            else if (i % 4 == 2) property = p3;  // Verdana, Black, 16
            else property = p4;                  // Arial, Blue, 12
            document.addCharacter(text.charAt(i), property);
        }

        System.out.println("Displaying document with 4 flyweight variations:\n");
        document.display();

        String filename = "HelloWorldCS5800.txt";
        document.saveToFile(filename);

        Document loaded = Document.loadFromFile(filename);
        if (loaded != null) {
            System.out.println("\nLoaded Document:");
            loaded.display();
        }

        System.out.println("\nUnique property objects created: " + CharacterPropertyFactory.getTotalPropertiesCreated());
    }
}
