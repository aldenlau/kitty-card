public class Card {
    private Player owner;

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Card(Player owner) {
        this.owner = owner;
    }
}
