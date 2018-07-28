package Final;

/**
 * 
 * @author David Moniz - FERI - University of Maribor
 *	This purpose of this class is to get a position from 0 to 8, from the tic tac toe game, and convert it to an X and Y position.  
 *
 */
public class ConvertPositionToAxis {
    int axisX;//global variable of the x axis	
    int axisY;//global variable of the y axis
    
    /**
     * 
     * Constructor to update the global variables of the x axis and the y axis
     * @param axisX
     * @param axisY
     */
    public ConvertPositionToAxis(int axisX,int axisY){
        this.axisX=axisX;				
        this.axisY=axisY;				
    }
    
    /**
     * 
     * Convert the position of the button into an X and Y coordinate to update the matrix.
     * @param x
     * @return
     */
    public static ConvertPositionToAxis getPosition(int x){
         return new ConvertPositionToAxis(x/3, x%3);//For the x axis it divides by three and for the Y axis it gets the remainder	
    }
}
