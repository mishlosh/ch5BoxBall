import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

/**
 * A ball which will bounce around within a box
 *
 * @author Michal Legocki
 * @version 2018/10/15
 */
public class BoxBall
{
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private final int botWall;      // y position of ground
    private final int topWall;
    private final int rightWall;
    private final int leftWall;
    private Canvas canvas;
    private int ySpeed;                
    private int xSpeed;
    private final int botBox;
    private final int leftBox;
    private final int rightBox;
    private final int topBox;

    /**
     * Constructor for objects of class BoxBall
     * 
     * @Param xPos x position of ball
     * @Param yPos y position of ball
     * @param ballDiameter diameter of the ball
     * @param ballColor color of the ball
     * @param botWall the bottom wall of the box
     * @param leftWall the left wall of the box
     * @param topWall the top wall of the box
     * @param rightWall the right wall of the box
     * @param drawingCanvas the canvas upon which this ball is drawn
     */
    public BoxBall(int xPos, int yPos, int ballDiameter, Color ballColor,
                        int botWall, int leftWall, int topWall, int rightWall, 
                        Canvas drawingCanvas,
                        int botBox, int leftBox, int topBox, int rightBox)
    {
        xPosition = xPos;
        yPosition = yPos;
        color = ballColor;
        diameter = ballDiameter;
        this.botWall = botWall;
        this.leftWall = leftWall;
        this.topWall = topWall;
        this.rightWall = rightWall;
        canvas = drawingCanvas;
        this.botBox = botBox;
        this.leftBox = leftBox;
        this.topBox = topBox;
        this.rightBox = rightBox;
        
        Random rnd = new Random();
        xSpeed = rnd.nextInt(7)+1;
        ySpeed = rnd.nextInt(7)+1;
        
        if(rnd.nextBoolean()){
            xSpeed = -xSpeed;
        }
        if(rnd.nextBoolean()){
            ySpeed = -ySpeed;
        }
    }

    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }
    
    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }    

    /**
     * Move this ball according to its position and speed and redraw.
     **/
    public void move()
    {
        // remove from canvas at the current position
        erase();
            
        // compute new position
        yPosition += ySpeed;
        xPosition += xSpeed;

        // check if it has hit the ground
        if(yPosition >= (botWall - diameter) && ySpeed > 0) {
            yPosition = (int)(botWall - diameter);
            ySpeed = -ySpeed; 
        }
        // check if it has hit the ceiling
        if(yPosition <= (topWall) && ySpeed < 0) {
            yPosition = (int)(topWall);
            ySpeed = -ySpeed;
        }
        // check if it has hit the left wall
        if(xPosition <= (leftWall) && xSpeed < 0) {
            xPosition = (int)(leftWall);
            xSpeed = -xSpeed;
        }
        // check if it has hit the right wall
        if(xPosition >= (rightWall - diameter) && xSpeed > 0) {
            xPosition = (int)(rightWall - diameter);
            xSpeed = -xSpeed;
        }
        
        // **********************BOX**********************
        
        // check if it has hit the bot box
        if(yPosition <= (botBox) && 
            yPosition > (botBox + ySpeed) && 
            ySpeed < 0 && 
            (xPosition >= (leftBox - diameter) && xPosition <= rightBox)) 
        {
            yPosition = (int)(botBox);
            ySpeed = -ySpeed;
        }
        // check if it has hit the top box
        if(yPosition >= (topBox - diameter) && 
            yPosition < (topBox - diameter + ySpeed) && 
            ySpeed > 0 &&
            (xPosition >= (leftBox - diameter) && xPosition <= rightBox)) 
        {
            yPosition = (int)(topBox - diameter);
            ySpeed = -ySpeed;
        }
        // check if it has hit the left box
        if(xPosition >= (leftBox - diameter) && 
            xPosition < (leftBox - diameter + xSpeed) && 
            xSpeed > 0 &&
            (yPosition >= (topBox - diameter) && yPosition <= botBox)) 
        {
            xPosition = (int)(leftBox - diameter);
            xSpeed = -xSpeed;
        }
        // check if it has hit the right box
        if(xPosition <= (rightBox) && 
            xPosition > (rightBox + xSpeed) && 
            xSpeed < 0 &&
            (yPosition >= (topBox - diameter) && yPosition <= botBox)) 
        {
            xPosition = (int)(rightBox);
            xSpeed = -xSpeed;
        }
           
        // draw again at new position
        draw();
    }    

    /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPosition()
    {
        return yPosition;
    }
}
