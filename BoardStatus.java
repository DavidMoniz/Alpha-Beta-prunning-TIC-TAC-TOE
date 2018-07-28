
package Final;

/**
 * 
 * @author David Moniz - FERI - University of Maribor
 * This class is to check the status of the tic tac toe board game, for example, if one of the players have won or if it's a draw
 * There is player 1(you), player 2(the PC) and empty slot(0).
 * 
 * 
 */
public class BoardStatus {

	/**
	 * 
	 * This function is to check if a certain player of the game has won and returns a boolean
	 * Searches by row, column, diagonal and reverse diagonal
	 * @param player
	 * @param matrix
	 * @return
	 */
    public static boolean isPlayerWinner(int player, int matrix[][]) {

        for (int i = 0; i < 3; i++) {
            boolean flg = true;
            boolean flg2 = true;
            for (int j = 0; j < 3; j++) {
                flg &= (matrix[i][j] == player); //check rows
                flg2 &= (matrix[j][i] == player); //check columns
            }
            if (flg || flg2) {return true;}
        }
        boolean flg = true;
        for (int i = 0; i < 3; i++) {
            flg &= (matrix[i][i] == player); //checks diagonal
        }
        if (flg) {return true;}
        flg = true;
        for (int i = 0; i < 3; i++) {
            flg &= (matrix[i][3 - i - 1] == player); //checks reverse diagonal
        }
        if (flg) {return true;}
        return false;
    }

    /**
     * 
     * This function is to check if the game status is a draw and returns a boolean
     * Checks there is an empty option to play since draw needs all options taken.
     * THIS FUNCTION should only be used if you use the isPlayerWinner() function before, for both players. 
     * @param matrix
     * @return
     */
    public static boolean isDraw(int matrix[][]) {
        int numberOfFreeOptions = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                numberOfFreeOptions += (matrix[i][j] == 0 ? 1 : 0);//If there's a free empty space then false.
            }
        }
        if (numberOfFreeOptions == 0) {//The table is full
            return true;
        }
        return false;
    }

}
