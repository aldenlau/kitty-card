import java.util.ArrayList;
import java.util.List;

public class Player {
    private final List<NumberCard> numberCards;
    private final List<AssistCard> assistCards;

    private boolean canPlayAssist;
    private boolean canPlayNumber;

    private final int playerId;

    public int getPlayerId() {
        return playerId;
    }

    public boolean isCanPlayAssist() {
        return canPlayAssist;
    }

    public void setCanPlayAssist(boolean canPlayAssist) {
        this.canPlayAssist = canPlayAssist;
    }

    public boolean isCanPlayNumber() {
        return canPlayNumber;
    }

    public void setCanPlayNumber(boolean canPlayNumber) {
        this.canPlayNumber = canPlayNumber;
    }

    private final List<AssistCard> lastPlayedAssistCards;

    public Player(int initialNumberCards, int initialAssistCards, int playerId) {
        this.numberCards = new ArrayList<>();
        this.assistCards = new ArrayList<>();
        this.canPlayAssist = true;
        this.canPlayNumber = true;
        this.playerId = playerId;
        this.lastPlayedAssistCards = new ArrayList<>();
        for (int i = 0; i<initialAssistCards;i++) {
            drawAssistCard();
        }
        for (int i = 0; i<initialNumberCards;i++) {
            drawNumberCard();
        }
    }

    public void drawNumberCard() {
        this.numberCards.add(NumberCard.getRandomNumberCard(this));
    }
    //TODO
    public void drawAssistCard() {

    }

    //TODO
    public void playAssistCards() {
    }

    public int numberCardCount() {
        return this.numberCards.size();
    }

    public boolean playNumberCard(int row, int col, Board board, int numberCardIndex) {
        NumberCard cardToPlay = this.numberCards.get(numberCardIndex);
        if (board.addCard(cardToPlay, row, col)) {
            this.numberCards.remove(numberCardIndex);
            return true;
        }
        else {
            return false;
        }

    }

    public void printNumberCards() {
        System.out.println("Your number cards:");
        for (int i = 0; i < this.numberCards.size();i++) {
            NumberCard card = this.numberCards.get(i);
            System.out.println(String.format("(%d): %s %d", i, card.getColor().name(), card.getNumber()));
        }
    }
}
