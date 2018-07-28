package Final;

/*
 * 
 * David Moniz - FERI - University of Maribor
 * The AlphaBetaPrunning class is an optimization of the minimax algorithm that improves the minimax algorithm by pruning unnecessary node expansions.
 * Without the alpha pruning the minimaz takes a long time to search for the best value. 
 * 
 */
public class AlphaBetaPrunning {
	private static int numberOfRecursiveIterations = 0;
	private static int maxNumWinning = 0;
	private static int minNumWinning = 0;
	private static int NumDrawing = 0;
    private static int mat[][];
    
    private static EliteNode miniMax(int state[][], int depth, int alpha, int beta, int maximizingPlayer) {
    	numberOfRecursiveIterations++;//Counter of the game to check how many times it was recursed.      	
    	
        if (BoardStatus.isPlayerWinner(2, state)) {//If the MAXIMIZING PLAYER winned
        	maxNumWinning++;
            return new EliteNode(-1, depth);
//This means the end node of the tree that the alpha beta prunning found is a win for the computer so the score is positive.
//The higher the score in less depth the better.
        }
        if (BoardStatus.isPlayerWinner(1, state)) {//If the MINIMIZING PLAYER winned
        	minNumWinning++;
            return new EliteNode(-1, -depth);
//This means the end node of the tree that the alpha beta prunning found is a win for the opponent so the score is negative.
//The lower the score in less depth the better.            
        }
        if (BoardStatus.isDraw(state)) {
        	NumDrawing++;
            return new EliteNode(-1, 0);
//This means the end node of the tree that the alpha beta prunning found is a draw for the both players so the score is neutral.
//The score is neutral in all depth because it isn't beneficial to any player, but it's better to draw than to lose.
        }
               
        EliteNode maxEval = new EliteNode(-1, 0);
        if (maximizingPlayer == 2) {
            maxEval.score = -10;
        } 
        else {
            maxEval.score = 10;
        }
        for (int k = 0; k < 9; k++) {
        	
            int i = ConvertPositionToAxis.getPosition(k).axisX;
            int j = ConvertPositionToAxis.getPosition(k).axisY;
            
            if(depth!=0) {
	            if (state[i][j] == 0) {
	                if (maximizingPlayer == 2) {//MAXIMIZING PLAYER which has to get the highest score as possible.
	                    state[i][j] = 2;//Temporary puts MAX Player on a free position of the matrix.
	                    EliteNode eval = miniMax(state, depth-1,alpha,beta,1);//Runs 
	                    state[i][j] = 0;//Resets the move that it just evaluated
	                    if (eval.score > maxEval.score) {//MAXIMIZING algorithm updates the node if the value is higher
	                        maxEval.score = eval.score;
	                        maxEval.move = k;
	                    }
	                    if(eval.score>=beta) {//If beta finds a lower value it will use prunning to avoid the expansion of that node. 
	                    	return maxEval;
	                    } 
	                    if(alpha<eval.score) {//If alpha finds a higher value it will update its score
	                    	alpha=eval.score;//Updates alpha but doesn't prune
	                    }
	                } 
	                else {//MINIMIZING PLAYER which has to get the lowest score as possible.
	                    state[i][j] = 1;//Temporary puts MIN Player on a free position of the matrix.
	                    EliteNode eval = miniMax(state, depth-1,alpha,beta,2);
	                    state[i][j] = 0;//Resets the move that it just evaluated
	                    if (maxEval.score > eval.score) {//MINIMIZING algorithm updates the node if the value is lower
	                        maxEval.score = eval.score;
	                    }
	                    if(eval.score<=alpha) {//If alpha finds a higher value it will use prunning to avoid the expansion of that node. 
	                    	return maxEval;
	                    }
	                    if(beta>eval.score) {//If beta finds a lower value it will update beta
	                    	beta=eval.score;
	                    }
	                }
	            }
            }
        }
        return maxEval;
	}
    
	public static EliteNode run(int depth) {
        mat = TicTacToeBoard.matrixState.clone();//Gets a clone of the current board's matrix.
        int alpha = -10;//Alpha will start with a low value because its goal is to get the highest value when comparing with the next node.
        int beta = 10;//Beta will start with a high value because its goal is to get the highest value when comparing with the next node.
        int player = 2;//The algorithm is always for the PC player. This means the PC will be MAX and the user will be MIN.
        EliteNode node = miniMax(mat,depth,alpha,beta,player);//Get the best node, depending on the depth.
        System.out.println("--------------");
        System.out.println("The alpha beta prunning iterated " + numberOfRecursiveIterations + " times to get the best move.");       
        System.out.println("The COMPUTER found " + maxNumWinning + " WINS, " + minNumWinning + " LOSES and "+ NumDrawing + " DRAWs");    
        System.out.println("The best node move for the computer is " + (node.move+1) + "!");
        System.out.println();
        System.out.println();
        numberOfRecursiveIterations=0;
        maxNumWinning=0;
        minNumWinning=0;
        NumDrawing=0;
        return node;
    }
}


