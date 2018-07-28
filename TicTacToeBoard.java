package Final;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * @author David Moniz - FERI - University of Maribor
 * This class is the STARTER of the Tic Tac Toe GAME.
 * It draws and redraws the Jframe to create the GUI SWING of the game.
 * Has all GUI SWING exceptions to prevent errors.
 *
 */
public class TicTacToeBoard implements ActionListener {

    public static int matrixState[][] = new int[3][3];//creation of back end matrix of the tic tac toe game
    private JFrame window;
    private JButton[] gameButtons;//creation of front end matrix of the tic tac toe game
    private final int width = 600;
    private final int height = 600;
    private final int elements = 9;//Number of elements
    private boolean gameEnded = false;
    private Image X;
    private Image O;
    private int depth = -1;//Depth starts with -1 in order to accept the input of difficulty, in order to play.
    /**
     * This main starts the Tic tac toe game
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        TicTacToeBoard gameBoard = new TicTacToeBoard();
    }
    
    /**
     * 
     * Constructor
     * Updates the images of X and O.
     * Starts a JFrame with a GridLayout(3,3) and updates each element of the grid to a button with an index number from 0 to 8;
     * Scales the images to the correct size of each button.
     * @throws IOException
     */
    public TicTacToeBoard() throws IOException {
        X = ImageIO.read(getClass().getResource("x.png"));//Gets X image
        O = ImageIO.read(getClass().getResource("o.png"));//Gets O image

        window = new JFrame("TicTacToe with alpha beta prunning");
        window.setSize(width, height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new GridLayout(3, 3));//sets as a grid layout for the game
        gameButtons = new JButton[elements];
        for (int i = 0; i < elements; i++) {
            gameButtons[i] = new JButton("");
            gameButtons[i].addActionListener(this);
            gameButtons[i].setBackground(Color.white);
            window.add(gameButtons[i]);
        }
        window.setVisible(true);
        X = X.getScaledInstance(window.getContentPane().getWidth() / 3, window.getContentPane().getHeight() / 3, Image.SCALE_SMOOTH);//Scales the x image
        O = O.getScaledInstance(window.getContentPane().getWidth() / 3, window.getContentPane().getHeight() / 3, Image.SCALE_SMOOTH);//Scales the o image
        depth = getInputOfDifficulty();
    }

    /**
     * 
     * Shows an option Panel with three difficulty options
     * Gets the input of the difficulty 
     * @return
     */
    public int getInputOfDifficulty() {
    	Object[] possibilities = {"EASY", "MEDIUM", "HARD"};
        String s = (String)JOptionPane.showInputDialog(
                            null,
                            "Choose the dificulty of the game:\n"
                            + "",
                            "",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            possibilities,
                            "OK");

        //If a string was returned, say so.
        if ((s != null) && (s.length() > 0)) {
            if(s.equals("EASY")) {
            	return 3;
            }
            else if(s.equals("MEDIUM")) {
            	return 5; 	
			}
            else if(s.equals("HARD")) {
            	return 9;
			}
        }
        else {
        	window.dispose();
        }
        return -1;
    }
    /**
     * 
     * Gets the index number of which button was clicked
     * @param e
     * @return
     */
    private int getIndexOfButton(ActionEvent e) {
        for (int i = 0; i < elements; i++) {
            if (e.getSource() == gameButtons[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 
     * This function is to check if the matrix is over by draw, win or loss to the user's perspective.
     * If the game is over then it runs the endGame function.
     * @param player
     * @return
     */
    private boolean isPlayerWinner(int player) {
        if (BoardStatus.isDraw(matrixState)) {
            System.out.println("DRAW");
            gameEnded = true;
            endGame(0);//Calls function with no winners.
            return true;
        } else if (player == 1) {
            if (BoardStatus.isPlayerWinner(player, matrixState)) {
                System.out.println("YOU WIN");
                gameEnded = true;
                endGame(1);//Calls function with winner player 1.
                return true;
            }
        } else {
            if (BoardStatus.isPlayerWinner(player, matrixState)) {
                System.out.println("Computer WIN");
                gameEnded = true;
                endGame(2);//Calls function with winner player 2.
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * The function means the the game ended and will update the user with the status message and ask for play again.
     * Updates message
     * Shows Dialog window with updated message and adds a Yes/No option button. 
     * @param winningPlayer
     */
    private void endGame(int winningPlayer) {
        String message = "";
        if (winningPlayer == 1) {
            message = "YOU WIN";
        }
        else if (winningPlayer == 2) {
            message = "YOU LOSE";
        }
        else if (winningPlayer == 0) {
            message = "DRAW";
        }

        int selectedOption = JOptionPane.showConfirmDialog(null, message + "\nPlay Again ?", "Choose", JOptionPane.YES_NO_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION) {
            matrixState = new int[3][3];//Resets the matrix because it's a new game
            for (int i = 0; i < 9; i++) {
                window.remove(gameButtons[i]);//Removes all elements of the array by deleting the buttons
                gameButtons[i] = new JButton("");
                gameButtons[i].addActionListener(this);
                window.add(gameButtons[i]);//Creates new buttons on the window JFrame

            }
            window.setVisible(true);
            gameEnded = false;
            depth = getInputOfDifficulty();//Sets alpha beta's depth value
        }
        if (selectedOption == JOptionPane.NO_OPTION) {
        	window.dispose();//Closes the window
        }
    }

    /**
     * 
     * With the actionPerformed we check the button clicked and play if the option is available.
     * The PC will play right after the user's
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    	if(depth != -1) {
    		
	        if (gameEnded) {
	            return;
	        }
	        int index = getIndexOfButton(e);//Gets the position of the 
	        ConvertPositionToAxis p = ConvertPositionToAxis.getPosition(index);
	        if (matrixState[p.axisX][p.axisY] != 0) {//If the option was choosen before, then no action is performed
	            return;
	        }
	            gameButtons[index].setIcon(new ImageIcon(X));//Update the buttton's image.
	            gameButtons[index].setContentAreaFilled(false);
	            matrixState[p.axisX][p.axisY] = 1;//Updates the move to the matrix element to player 2, the PC.
	
	            if (isPlayerWinner(1)) {//Checks if the user wins after his current move.
	                return;//Game is over and player 1 wins
	            }
	            else {//The user didn't win after his move so the PC will play again and
	            /*****************************************
	            
	            
	            
	            ****************************************/
	            EliteNode res = AlphaBetaPrunning.run(depth);//The ALpha Beta prunning is used to get the PC's(player2) best move and play 
	            /*****************************************
	            
	            
	            
	             ****************************************/
	            gameButtons[res.move].setIcon(new ImageIcon(O));
	            gameButtons[res.move].setContentAreaFilled(false);
	            p = ConvertPositionToAxis.getPosition(res.move);
	            matrixState[p.axisX][p.axisY] = 2;//Updates the move to the matrix element to player 2, the PC.
	            
	            if (isPlayerWinner(2)) {//Checks if the PC wins after his current move.
	                return;//Game is over and player 2 wins
	            }	
	        }
    	}
    }
}
