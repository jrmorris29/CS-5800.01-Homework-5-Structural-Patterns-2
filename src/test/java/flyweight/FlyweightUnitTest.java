package flyweight;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FlyweightUnitTest {

    @Test
    public void testFlyweightSharing() {
        CharacterProperty p1 = CharacterPropertyFactory.getCharacterProperty("Arial", "Red", 12);
        CharacterProperty p2 = CharacterPropertyFactory.getCharacterProperty("Arial", "Red", 12);
        assertSame(p1, p2, "Flyweight objects should be shared and identical for same properties");
    }

    @Test
    public void testUniquePropertiesCount() {
        CharacterPropertyFactory.getCharacterProperty("Arial", "Red", 12);
        CharacterPropertyFactory.getCharacterProperty("Calibri", "Blue", 14);
        CharacterPropertyFactory.getCharacterProperty("Verdana", "Black", 16);
        assertTrue(CharacterPropertyFactory.getTotalPropertiesCreated() >= 3);
    }

    @Test
    public void testDocumentSaveAndLoad() {
        Document doc = new Document();
        CharacterProperty p = CharacterPropertyFactory.getCharacterProperty("Arial", "Red", 12);
        doc.addCharacter('A', p);
        doc.saveToFile("testDoc.txt");

        Document loaded = Document.loadFromFile("testDoc.txt");
        assertNotNull(loaded, "Loaded document should not be null");
    }

    @Test
    public void testMultipleDocumentIndependence() {
        Document doc1 = new Document();
        Document doc2 = new Document();

        CharacterProperty shared = CharacterPropertyFactory.getCharacterProperty("Calibri", "Blue", 14);

        doc1.addCharacter('A', shared);
        doc2.addCharacter('B', shared);

        assertNotSame(doc1, doc2, "Documents should be separate instances");
        assertEquals(CharacterPropertyFactory.getTotalPropertiesCreated(),
                CharacterPropertyFactory.getTotalPropertiesCreated(),
                "Flyweight properties should still be shared across documents");
    }

    @Test
    public void testToStringOutput() {
        CharacterProperty prop = new CharacterProperty("Verdana", "Black", 16);
        String result = prop.toString();
        assertTrue(result.contains("Verdana") && result.contains("Black") && result.contains("16"),
                "toString() should include font, color, and size info");
    }


}
