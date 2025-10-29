package flyweight;

import java.util.HashMap;
import java.util.Map;

public class CharacterPropertyFactory {
    private static final Map<String, CharacterProperty> propertyMap = new HashMap<>();

    public static CharacterProperty getCharacterProperty(String font, String color, int size) {
        String key = font + "-" + color + "-" + size;
        if (!propertyMap.containsKey(key)) {
            propertyMap.put(key, new CharacterProperty(font, color, size));
        }
        return propertyMap.get(key);
    }

    public static int getTotalPropertiesCreated() {
        return propertyMap.size();
    }

    public static void resetFactory() {
        propertyMap.clear();
    }
}
