import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Board {
    private int rows;
    private int cols;


    private final List<List<Cup>> cups;

    public Board(int r, int c) {
        rows = r;
        cols = c;
        cups = new ArrayList<>();
        for (int i = 0; i< r; i++) {
            cups.add(new ArrayList<>());
            for (int j = 0; j< c; j++) {
                if (!(i==Math.floorDiv(rows, 2) && j==Math.floorDiv(cols, 2))){
                    Cup newCup = Cup.getRandomEmptyCup();
                    cups.get(i).add(newCup);
                }
                else {
                    cups.get(i).add(null);
                }
            }
        }
    }

    public boolean isFull() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j<this.cols; j++) {
                if (!(i==Math.floorDiv(rows, 2) && j==Math.floorDiv(cols, 2))){
                    if (this.cups.get(i).get(j).getCurrentCard().isEmpty()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean addCard(NumberCard card, int row, int col) {
        if (!(row==Math.floorDiv(rows, 2) && col==Math.floorDiv(cols, 2)) && cups.get(row).get(col).getCurrentCard().isEmpty()) {
            cups.get(row).get(col).setCurrentCard(Optional.of(card));
            return true;
        }
        else {
            return false;
        }
    }
    public int getPlayerScore(Player player) {
        int score = 0;
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j<this.cols; j++) {
                if (!(i==Math.floorDiv(rows, 2) && j==Math.floorDiv(cols, 2))){
                    Cup currentCup = cups.get(i).get(j);
                    if (currentCup.getCurrentCard().isPresent()) {
                        NumberCard numberCard = currentCup.getCurrentCard().get();
                        if (numberCard.getOwner()==player) {
                            if (currentCup.getColor()==numberCard.getColor()) {
                                score+=2*numberCard.getNumber();
                            }
                            else if (currentCup.getColor()==null) {
                                score+=numberCard.getNumber();
                            }
                        }

                    }
                }
            }
        }
        return score;
    }

    public void printBoard() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (!(i==Math.floorDiv(rows, 2) && j==Math.floorDiv(cols, 2))){
                    Cup currentCup = cups.get(i).get(j);
                    if (currentCup.getCurrentCard().isPresent()) {
                        NumberCard numberCard = currentCup.getCurrentCard().get();
                        System.out.print(String.format("[%d:%d:%c,%c] ",
                                numberCard.getOwner().getPlayerId(),
                                numberCard.getNumber(),
                                numberCard.getColor().name().charAt(0),
                                currentCup.getColorChar()));
                    }
                    else {
                        System.out.print(String.format("[      %c] ", currentCup.getColorChar()));
                    }
                }
                else {
                    System.out.print("[       ] ");
                }
            }
            System.out.print("\n");
        }
    }
}
