/*
Animation Player class
 */
package ENGG1420FinalProject;

/**
 *
 * @author harja
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javax.swing.JOptionPane;


public class AnimationPlayer {
    
    //Attributes of AnimationPlayer
    static int frames;
    static int speed;
    static int elements;
    static Shape[] shapes = {null};
    static int counter = 0;
     
    /**
     * Primary Constructor for AnimationPlayer
     */
    public AnimationPlayer(){
        frames = 1000;
        speed = 10;
        elements = 2;
        shapes = new Shape[elements];
    }
    
    /**
     * Secondary Constructor for AnimationPlayer
     * @param frames
     * @param speed
     * @param elements 
     */
    public AnimationPlayer(int frames, int speed, int elements){
        this.frames = frames;
        this.speed = speed;
        this.elements = elements;
        shapes = new Shape[elements +1];
    }
    
    
    /**
     * Method accessed to load the file with the animations within it 
     * @param file 
     */
    static public void loadAnimationFromFile(String file){
        try{
            //create file and scanner in order to correctly read file
            File f = new File(file);
            Scanner s = new Scanner(f);
            
            //While there is still another line to read within the file
            while(s.hasNextLine()){

                //The first 3 lines of the file will hold the...
                frames = getInt(s.nextLine());//Number of frames
                speed = getInt(s.nextLine());//FPS 
                elements = getInt(s.nextLine());//Number of elements 
                
                //there will be a space for the next section of the program
                s.nextLine();

                //if the text file is going to attempt to create a circle, create it adn add it into the shapes array
                if(s.nextLine().equals("Circle")){
                    
                    /**
                     * Input for Circle goes in this order
                     * Radius
                     * Center X Position
                     * Center Y Position
                     * Fill Color
                     * Border Width
                     * Border Color
                     */
                    Circle c = new Circle();
                    c.setRadius(getInt(s.nextLine()));
                    c.setCenterX(getInt(s.nextLine()));
                    c.setCenterY(getInt(s.nextLine()));
                    String[] rgb = s.nextLine().split(",");
                    Color color = Color.rgb(getInt(rgb[0]), getInt(rgb[1]), getInt(rgb[2]));
                    c.setFill(color); 
                    c.setStrokeWidth(getInt(s.nextLine()));
                    String[] rgb2 = s.nextLine().split(",");
                    Color color2 = Color.rgb(getInt(rgb2[0]), getInt(rgb2[1]), getInt(rgb2[2]));
                    c.setStroke(color2);
                    //add the circle into the shape array of AnimationPlayer
                    shapes[counter++] = c;
                }
                
                //if the text file is going to attemp to dcreate a circle, create it and add to shape array
                else if(s.nextLine().equals("Rect")){

                    /**
                     * Input for Rectangle goes in this order
                     * Length
                     * Width
                     * Top Left X Position
                     * Top Left Y Position
                     * Border Thickness
                     * Fill Color in RGB
                     * Border Color
                     */
                    Rectangle r = new Rectangle();
                    r.setHeight(getInt(s.nextLine()));
                    r.setWidth(getInt(s.nextLine()));
                    r.setX(getInt(s.nextLine()));
                    r.setY(getInt(s.nextLine()));
                    r.setStrokeWidth(getInt(s.nextLine()));
                    String[] rgb = s.nextLine().split(",");
                    Color color = Color.rgb(getInt(rgb[0]), getInt(rgb[1]), getInt(rgb[2]));
                    r.setFill(color); 
                    String[] rgb2 = s.nextLine().split(",");
                    Color color2 = Color.rgb(getInt(rgb2[0]), getInt(rgb2[1]), getInt(rgb2[2]));
                    r.setStroke(color2);
                    //add rectangle to shape array of AnimationPlayer
                    shapes[counter++] = r;
                }
                
                //if the text file is attempting to create a line, create it and add to the shape array
                else if(s.nextLine().equals("Line")){
                    /**
                     * Input for Line goes in this order
                     * Start X Position
                     * Start Y  Position
                     * End X Position
                     * End Y Position
                     * Border Thickness
                     * Border Color
                     */
                    Line l = new Line();
                    l.setStartX(getInt(s.nextLine()));
                    l.setStartY(getInt(s.nextLine()));
                    l.setEndX(getInt(s.nextLine()));
                    l.setEndY(getInt(s.nextLine()));
                    l.setStrokeWidth(getInt(s.nextLine()));
                    String[] rgb = s.nextLine().split(",");
                    Color color = Color.rgb(getInt(rgb[0]), getInt(rgb[1]), getInt(rgb[2]));
                    l.setStroke(color);
                    
                    //add line to the shape array of AnimationPlayer
                    shapes[counter++] = l;
                }
                
                //if the text file is attempting to describe an effect, read the effect differently dependent on effect
                else if(s.nextLine().equals("effect")){
                    /**
                     * Hide Effect
                     * Start Frame #
                     */
                    
                    /**
                     * Show Effect
                     * Start Frame #
                     */
                    
                    /**
                     * Jump Effect
                     * Start Frame #
                     * New X Position
                     * New Y Position
                     */
                    
                    /**
                     * ChangeColor effect
                     * Start Frame #
                     * RGB of changed color
                     */
                    

                }
                
                //if the next line is blank, nothing happens. While statement will continue
                else if(s.nextLine().equals("")){
                    
                }
                
                //if none of the top options are true, then show them error message
                else{
                    JOptionPane.showMessageDialog(null, "The file you have entered has a unreadable action which cannot be performed. Please make sure that you have entered the correct information into this file.");
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println(e);
        }
    }
    
    
    /**
     * Method used to parse string into integer
     * @param s - String which needs to be parsed
     * @return - integer of the string
     */
    static public int getInt(String s){
        int r = Integer.parseInt(s);
        return r;
    }
    
    

}
