package flyweight;

import java.io.Serializable;
import java.util.Objects;

public class CharacterProperty implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String font;
    private final String color;
    private final int size;

    public CharacterProperty(String font, String color, int size) {
        this.font = font;
        this.color = color;
        this.size = size;
    }

    public String getFont() { return font; }
    public String getColor() { return color; }
    public int getSize() { return size; }

    @Override
    public String toString() {
        return "Font: " + font + ", Color: " + color + ", Size: " + size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CharacterProperty)) return false;
        CharacterProperty that = (CharacterProperty) o;
        return size == that.size &&
                Objects.equals(font, that.font) &&
                Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(font, color, size);
    }
}
