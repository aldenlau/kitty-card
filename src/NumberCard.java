import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class NumberCard extends Card{
    private int number;
    private Color color;

    public NumberCard(Player owner, int number, Color color) {
        super(owner);
        this.number = number;
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public static NumberCard getRandomNumberCard(Player owner) {
        Color[] allColors = Color.values();
        int randomIndex = ThreadLocalRandom.current().nextInt(0, allColors.length);
        return new NumberCard(owner, ThreadLocalRandom.current().nextInt(1, 7),allColors[randomIndex]);
    }
}
