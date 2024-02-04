import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    // First player gets 2 assist and 2 number cards, second player gets 1 assist and 3 number cards
    // First draw assist card
    // Then play unlimited number of assist cards
    // Then, can choose to play 1 number card or draw 1 number card
    // Game ends when all cups are filled

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        Board board;
        int numRows = 3;
        int numCols = 3;
        board = new Board(numRows, numCols);
        int numPlayers = 2;
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player(2, 2, 0));
        for (int i = 1; i<numPlayers;i++) {
            players.add(new Player(3, 1, i));
        }

        for (int i = 0; !board.isFull(); i = (i+1)%numPlayers) {
            Player currentPlayer = players.get(i);
            System.out.println(String.format("Player %d's turn!", currentPlayer.getPlayerId()));

            board.printBoard();
            currentPlayer.printNumberCards();

            if (currentPlayer.isCanPlayAssist()) {
                currentPlayer.drawAssistCard();
                System.out.println("Which of your assist cards would you like to play?");
                currentPlayer.playAssistCards();
            }
            else {
                currentPlayer.setCanPlayAssist(true);
            }

            if (currentPlayer.isCanPlayNumber()){
                int drawOrPlay = 0;
                while (drawOrPlay != 1 && drawOrPlay != 2) {
                    System.out.println("Draw (1) or play (2) a number card?");
                    drawOrPlay = sc.nextInt();
                }
                if (drawOrPlay == 1) {
                    currentPlayer.drawNumberCard();
                }
                else {
                    boolean succeeded = false;
                    while (!succeeded) {
                        int numberCardToPlayIndex = -1;
                        while (!(0 <= numberCardToPlayIndex && numberCardToPlayIndex < currentPlayer.numberCardCount())) {
                            System.out.println("Which number card to play?");
                            numberCardToPlayIndex = sc.nextInt();
                        }



                        int row = -1;
                        while (!(0 <= row && row < numRows)) {
                            System.out.println("Which row?");
                            row = sc.nextInt();
                        }

                        int col = -1;
                        while (!(0 <= col && col < numCols)) {
                            System.out.println("Which column?");
                            col = sc.nextInt();
                        }
                        succeeded = currentPlayer.playNumberCard(row, col, board, numberCardToPlayIndex);
                        if (!succeeded) {
                            System.out.println("Can't play a number card there!");
                        }
                    }
                }
            }
            else {
                currentPlayer.setCanPlayNumber(true);
            }

        }

        // Game over
        System.out.println("Game over!");
        for (int i = 0; i<numPlayers;i++) {
            Player player = players.get(i);
            System.out.println(String.format("Player %d score: %d", i, board.getPlayerScore(player)));
        }
    }
}
