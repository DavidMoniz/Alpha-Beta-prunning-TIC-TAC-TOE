package Final;

/**
 * 
 * @author David Moniz - FERI - University of Maribor
 * The AlphaBetaPrunning class is an Alpha-Beta prunning algorithm that improves the minimax algorithm by prunning unnecessary node expansions.
 *
 */
public class EliteNode {
    int move;//global variable of the nodes move
    int score;//global variable of the y nodes score
    
    /**
     * Constructor to updates the nodes move and score
     * @param move
     * @param score
     */
    public EliteNode(int move,int score){	
        this.move=move;
        this.score=score;
    }
}
