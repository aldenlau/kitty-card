import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class Cup {
    private Optional<NumberCard> currentCard;
    private Color color;

    public Optional<NumberCard> getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(Optional<NumberCard> currentCard) {
        this.currentCard = currentCard;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Cup(Optional<NumberCard> card, Color c) {
        this.currentCard = card;
        this.color = c;
    }

    public static Cup getRandomEmptyCup() {
        if (Math.random()<0.5) {
            // Cup should be white
            return new Cup(Optional.empty(), null);
        }
        else {
            // Cup should be colored
            Color[] allColors = Color.values();
            int randomIndex = ThreadLocalRandom.current().nextInt(0, allColors.length);
            return new Cup(Optional.empty(), allColors[randomIndex]);
        }

    }

    public char getColorChar() {
        if (this.color==null) {
            return 'W';
        }
        else {
            return this.color.name().charAt(0);
        }
    }
}
