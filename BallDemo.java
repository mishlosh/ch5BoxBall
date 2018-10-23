import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Bill Crosbie
 * @version 2015-March-BB
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 * 
 * @author Michal Legocki
 * @version 2018.10.15
 */

public class BallDemo   
{
    private Canvas myCanvas;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }
    
    /**
     * simulate multiple balls bouncing within a square
     * 
     * @param ballAmount the amount of balls to be simulated, limit 5-25
     */
    public void boxBounce(int ballAmount){
        ArrayList<BoxBall> balls = new ArrayList<BoxBall>();
        Random rnd = new Random();
        
        int rightWall = (int)myCanvas.getSize().getWidth()-50;
        int topWall = 50;
        int botWall = (int)myCanvas.getSize().getHeight()-50;
        int leftWall = 50;
        
        int middlex = ((rightWall-leftWall)/2) + leftWall;
        int middley = ((botWall-topWall)/2) + topWall;
        int leftBox = middlex - ((rightWall-leftWall)/6);
        int rightBox = middlex + ((rightWall-leftWall)/6);
        int topBox = middley - ((botWall-topWall)/6);
        int botBox = middley + ((botWall-topWall)/6);
        
        myCanvas.setVisible(true);
        
        myCanvas.drawLine(leftWall,botWall,rightWall,botWall);
        myCanvas.drawLine(leftWall,topWall,rightWall,topWall);
        myCanvas.drawLine(leftWall,botWall,leftWall,topWall);
        myCanvas.drawLine(rightWall,botWall,rightWall,topWall);
        
        myCanvas.drawLine(leftBox,botBox,rightBox,botBox);
        myCanvas.drawLine(leftBox,topBox,rightBox,topBox);
        myCanvas.drawLine(leftBox,botBox,leftBox,topBox);
        myCanvas.drawLine(rightBox,botBox,rightBox,topBox);
        
        //check to see that ballAmount is within limit of 5-25, random number if not
        
        if(ballAmount >=5 && ballAmount <= 25){
            for(int c = ballAmount; c>0; c--){
                int ypo;
                int xpo = rnd.nextInt(rightWall-76)+51;
                if (xpo > leftBox && xpo < rightBox){
                    ypo = rnd.nextInt(topBox-76)+51;
                    if(rnd.nextBoolean()){
                        ypo += botBox - topWall;
                    }
                }else{
                    ypo = rnd.nextInt(botWall-76)+51;
                }
                int dia = rnd.nextInt(16)+10;
                Color col = new Color(rnd.nextInt(226),rnd.nextInt(226),
                    rnd.nextInt(226));
                BoxBall nBall = new BoxBall(xpo,ypo,dia,col,botWall,
                                    leftWall,topWall,rightWall,myCanvas,
                                    botBox,leftBox,topBox,rightBox);
                balls.add(nBall);
                /*
                balls.add(new BoxBall(rnd.nextInt(rightWall-76)+51,
                    rnd.nextInt(botWall-76)+51,rnd.nextInt(16)+10 ,
                    new Color(rnd.nextInt(226),rnd.nextInt(226),rnd.nextInt(226)),
                    botWall,leftWall,topWall,rightWall,myCanvas,
                    botBox,leftBox,topBox,rightBox));
                    */
            }
        }else{
            for(int c = rnd.nextInt(26)+5; c>0; c--){
                int ypo;
                int xpo = rnd.nextInt(rightWall-76)+51;
                if (xpo > leftBox && xpo < rightBox){
                    ypo = rnd.nextInt(topBox-76)+51;
                    if(rnd.nextBoolean()){
                        ypo += botBox - topWall;
                    }
                }else{
                    ypo = rnd.nextInt(botWall-76)+51;
                }
                int dia = rnd.nextInt(16)+10;
                Color col = new Color(rnd.nextInt(226),rnd.nextInt(226),
                    rnd.nextInt(226));
                BoxBall nBall = new BoxBall(xpo,ypo,dia,col,botWall,
                                    leftWall,topWall,rightWall,myCanvas,
                                    botBox,leftBox,topBox,rightBox);
                balls.add(nBall);
                /*
                balls.add(new BoxBall(rnd.nextInt(rightWall-76)+51,
                    rnd.nextInt(botWall-76)+51,rnd.nextInt(16)+10 ,
                    new Color(rnd.nextInt(226),rnd.nextInt(226),rnd.nextInt(226)),
                    botWall,leftWall,topWall,rightWall,myCanvas,
                    botBox,leftBox,topBox,rightBox));
                    */
            }
        }
        
        //loop which simulates
        
        boolean finished = false;
        
        while(!finished){
            myCanvas.wait(50);
            for(BoxBall ball : balls){
                ball.move();
            }
            myCanvas.setForegroundColor(Color.black);
            myCanvas.drawLine(leftWall,botWall,leftWall,topWall);
            
            myCanvas.drawLine(leftBox,botBox,rightBox,botBox);
            myCanvas.drawLine(leftBox,topBox,rightBox,topBox);
            myCanvas.drawLine(leftBox,botBox,leftBox,topBox);
            myCanvas.drawLine(rightBox,botBox,rightBox,topBox);
        }
        
        
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(50, ground, 550, ground);

        // crate and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
}
