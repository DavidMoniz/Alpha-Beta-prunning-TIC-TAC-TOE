Alpha-Beta-prunning-TIC-TAC-

JAVA IDE + SWING Components

JAVA IDE + SWING Components - I used Eclipse and windows builder. 
This is a Java Swing interface that implements a tic tac toe Game where you can choose the difficulty and play against the alpha-beta prunning algorithm.

Alpha Beta prunning is an optimization of the minimax and prunes unnecessary node expansions. 

In case you want to know what is added to the minimax algorithm, to achieve the alpha-beta-prunning:
For the maximizer player(the PC):
                      if(eval.score>=beta) {//If beta finds a lower value it will use prunning to avoid the expansion of that node. 
	                    	return maxEval;
	                    } 
	                    if(alpha<eval.score) {//If alpha finds a higher value it will update its score
	                    	alpha=eval.score;//Updates alpha but doesn't prune
	                    }
And for the Minmizer player(you):
                      if(eval.score<=alpha) {//If alpha finds a higher value it will use prunning to avoid the expansion of that node. 
	                    	return maxEval;
	                    }
	                    if(beta>eval.score) {//If beta finds a lower value it will update beta
	                    	beta=eval.score;
	                    }
                      
P.S - For fun, run the game and comment the code I mentioned above on the AlphaBetaPrunning and check on the console the difference on number of iterations necessary to get a solution. From tens of thousands to a few thousands.

Any questions about the algorithm, send me a message. 
