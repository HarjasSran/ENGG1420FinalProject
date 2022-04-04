/*
Harjas Sran, Samy Rashad, Affan Ahmed, Sayansh Verma
Final Project
ENGG 1420
Animation Player
Animation Player class which reads file and runs animation using javafx
 */
package engg1420finalproject;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author harja
 */
public abstract class animationPlayer2 extends Application {
   
    //Attributes of AnimationPlayer
    static int frames;
    static int speed;
    static int elements;
    Shape[] shapes = {null};
    static int counter = 0;
    static int tCounter = 0;
    static String[] effects;
    static int countEffects = 0;
    static String[] circleEffects;
    static int countCircleEffects = 0;
    static String[] rectEffects;
    static int countRectEffects = 0;
    static String[] lineEffects;
    static int countLineEffects = 0;
     
    /**
     * Primary Constructor for AnimationPlayer
     */
    public animationPlayer2(){
        frames = 1000;
        speed = 10;
        elements = 2;
        shapes = new Shape[elements];
    }
    
    /**
     * Secondary contructor for AnimationPlayer
     * @param frames
     * @param speed
     * @param elements 
     */
    public animationPlayer2(int frames, int speed, int elements){
        this.frames = frames;
        this.speed = speed;
        this.elements = elements;
        shapes = new Shape[elements +1];
    }
    
    
    /**
     * Method which runs the animation
     * @param stage 
     */
    public void run(Stage stage) {

        //set title of stage
        stage.setTitle("Animation Player");
        
        //test if circle is able to run on screen
        Shape circle = shapes[0];
        
        //set the scene so it 
        Group root = new Group(circle);
        Scene scene = new Scene(root,400,300);
        
        //show the user the screen with test circle on it 
        stage.setScene(scene);
        stage.show();
        
        //create animation timer with handle to count and use frames
        AnimationTimer timer = new AnimationTimer(){
            private long lastRun = 0;
            //handle method for animation timer to update and use frames, constantly running this method
            public void handle(long now){
                //if the last run was at 0, update it 
                if(lastRun == 0){
                    lastRun = now;
                    return;
                }
                
                //elapsed time is the time right now minus the time it last ran ( division to convert to seconds)
                double elapsed = (now - lastRun)/1e9;
                
                //add elapsed time to time counter
                tCounter += elapsed;
                
                //find the frame which the show effect occurs for our test cirlce (2nd element)
                int frameOccur = getInt(circleEffects[1]);
                
                //find the time the circle effect is supposed to take place (in seconds from given frames)
                int circleEffectTime = frameOccur/speed;
                
                //if the time counter is equal to or greater than the time when the circle effect is supposed to take place
                if(tCounter >= circleEffectTime){
                    //show the test circle due to the effect being the show method
                    circle.setOpacity(1.0);
                }
                //update the last time the method ran
                lastRun = now;
                //start of the animation timer 
                start();
            }
        };
        //start the animation timer
        timer.start();
        
        
    }
    
    /**
     * Method accessed to load the file with the animations within it 
     * @param file 
     */
    public void loadAnimationFromFile(String file){
        try{
            //create file and scanner in order to correctly read file
            FileReader f = new FileReader(file);
            BufferedReader br = new BufferedReader(f);
            if(counter ==0){
                //The first 3 lines of the file will hold the...
                frames = getInt(br.readLine());//Number of frames
                speed = getInt(br.readLine());//FPS 
                elements = getInt(br.readLine());//Number of elements 
            }
            
            //While there is still another line to read within the file
            for(int i = 0; i<elements; i++){
                //there will be a space for the next section of the program
                br.readLine();
                String type = br.readLine();

                //if the text file is going to attempt to create a circle, create it adn add it into the shapes array
                if(type.equals("Circle")){
                    
                    /**
                     * Input for Circle goes in this order
                     * Radius
                     * Center X Position
                     * Center Y Position
                     * Fill Color
                     * Border Width
                     * Border Color
                     */
                    Circle c = new Circle(); //create circle
                    c.setRadius(getInt(br.readLine())); 
                    c.setCenterX(getInt(br.readLine()));
                    c.setCenterY(getInt(br.readLine()));
                    String[] rgb = br.readLine().split(","); //this line holds the rgb color of the shape, split at each comma and save into array
                    Color color = Color.rgb(getInt(rgb[0]), getInt(rgb[1]), getInt(rgb[2])); //create a Color from the rgb array 
                    c.setFill(color); //set the color of the shape to the rgb read from the file
                    c.setStrokeWidth(getInt(br.readLine()));
                    String[] rgb2 = br.readLine().split(","); //this line hold the rgb color of the stroke, split at each comma and save into array
                    Color color2 = Color.rgb(getInt(rgb2[0]), getInt(rgb2[1]), getInt(rgb2[2])); //create a color from the rgb array
                    c.setStroke(color2);
                    //add the circle into the shape array of AnimationPlayer
                    shapes[counter++] = c;
                    //check if there are effects
                    if(br.readLine().equals("effects")){
                        //if there is a show effect
                        if(br.readLine().equals("Show")){
                            //save into circle effect array and save the frame number it begins at
                            circleEffects[countCircleEffects++] = "Show";
                            circleEffects[countCircleEffects++] = br.readLine(); //frame number
                        }
                        //if there is a hide effect
                        else if(br.readLine().equals("Hide")){
                            //save into circle effect array and save the frame number it begins at
                            circleEffects[countCircleEffects++] = "Hide";
                            circleEffects[countCircleEffects++] = br.readLine(); //frame number
                        }
                        //if there is a jump effect
                        else if(br.readLine().equals("Jump")){
                            //save into circle effect array and save the frame number it begins at
                            circleEffects[countCircleEffects++] = "Jump";
                            circleEffects[countCircleEffects++] = br.readLine(); //frame number
                            circleEffects[countCircleEffects++] = br.readLine(); //x position of jump
                            circleEffects[countCircleEffects++] = br.readLine(); //y position of jump
                        }
                        //if there is a change color effect
                        else if(br.readLine().equals("ChangeColor")){
                            //save into circle effect array and save the frame number it begins at 
                            circleEffects[countCircleEffects++] = "ChangeColor";
                            circleEffects[countCircleEffects++] = br.readLine(); //frame number
                            String[] rgbC = br.readLine().split(","); //this line holds the rgb color code, split at the comma and save into array
                            //save the colours into the circle effect array
                            circleEffects[countCircleEffects++] = rgbC[0];
                            circleEffects[countCircleEffects++] = rgbC[1];
                            circleEffects[countCircleEffects++] = rgbC[2];
                        }
                    }
                }
                
                //if the text file is going to attemp to dcreate a circle, create it and add to shape array
                else if(br.readLine().equals("Rect")){

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
                    Rectangle r = new Rectangle(); //create rectangle shape
                    r.setHeight(getInt(br.readLine()));
                    r.setWidth(getInt(br.readLine()));
                    r.setX(getInt(br.readLine()));
                    r.setY(getInt(br.readLine()));
                    r.setStrokeWidth(getInt(br.readLine()));
                    String[] rgb = br.readLine().split(","); //this line has the rgb of the rect, split at the comma and save into array 
                    Color color = Color.rgb(getInt(rgb[0]), getInt(rgb[1]), getInt(rgb[2])); //create a Color using the rgb array made
                    r.setFill(color); ///set fill color
                    String[] rgb2 = br.readLine().split(","); //this line has the stoke color of the rect, split at the comma and save into array
                    Color color2 = Color.rgb(getInt(rgb2[0]), getInt(rgb2[1]), getInt(rgb2[2])); //create a Color using the rgb array made
                    r.setStroke(color2);
                    //add rectangle to shape array of AnimationPlayer
                    shapes[counter++] = r;
                    //check if there are effects
                    if(br.readLine().equals("effects")){
                        //if there is a show effect
                        if(br.readLine().equals("Show")){
                            //save into rect effect array and save the frame number it begins at 
                            rectEffects[countRectEffects++] = "Show";
                            rectEffects[countRectEffects++] = br.readLine(); //frame number
                        }
                        //if there is a hide effect
                        else if(br.readLine().equals("Hide")){
                            //save into rect effect array and save the frame number it begins at
                            rectEffects[countRectEffects++] = "Hide";
                            rectEffects[countRectEffects++] = br.readLine(); //frame number
                        }
                        //if there is a jump effect
                        else if(br.readLine().equals("Jump")){
                            //save into rect effect array and save the frame number it begins at
                            rectEffects[countRectEffects++] = "Jump";
                            rectEffects[countRectEffects++] = br.readLine(); //frame number
                            rectEffects[countRectEffects++] = br.readLine(); //x position of jump
                            rectEffects[countRectEffects++] = br.readLine(); //y position of jump
                        }
                        //if thee is a change color effect
                        else if(br.readLine().equals("ChangeColor")){
                            //save into rect effect array and save the frame number it begins at
                            rectEffects[countRectEffects++] = "ChangeColor";
                            rectEffects[countRectEffects++] = br.readLine(); //frame number
                            String[] rgbC = br.readLine().split(","); //this line holds the rgb color code
                            //save the rbg color codes into the rect effect array
                            rectEffects[countRectEffects++] = rgbC[0];
                            rectEffects[countRectEffects++] = rgbC[1];
                            rectEffects[countRectEffects++] = rgbC[2];
                        }
                    }
                }
                
                //if the text file is attempting to create a line, create it and add to the shape array
                else if(br.readLine().equals("Line")){
                    /**
                     * Input for Line goes in this order
                     * Start X Position
                     * Start Y  Position
                     * End X Position
                     * End Y Position
                     * Border Thickness
                     * Border Color
                     */
                    Line l = new Line(); //create line shape
                    l.setStartX(getInt(br.readLine()));
                    l.setStartY(getInt(br.readLine()));
                    l.setEndX(getInt(br.readLine()));
                    l.setEndY(getInt(br.readLine()));
                    l.setStrokeWidth(getInt(br.readLine()));
                    String[] rgb = br.readLine().split(","); //this line holds the stroke color, split at the comma and save into rgb array
                    Color color = Color.rgb(getInt(rgb[0]), getInt(rgb[1]), getInt(rgb[2])); //use rgb array to create a color
                    l.setStroke(color); //save the color as the stroke color 
                    
                    //add line to the shape array of AnimationPlayer
                    shapes[counter++] = l;
                    //check if there are effects
                    if(br.readLine().equals("effects")){
                        //if there is a show effect
                        if(br.readLine().equals("Show")){
                            //save into line effect array and save the frame number it begins at
                            lineEffects[countLineEffects++] = "Show";
                            lineEffects[countLineEffects++] = br.readLine();
                        }
                        //if there is a hide effect
                        else if(br.readLine().equals("Hide")){
                            //save into line effect array and save the frame number it begins at
                            lineEffects[countLineEffects++] = "Hide";
                            lineEffects[countLineEffects++] = br.readLine();
                        }
                        //if there is a jump effect
                        else if(br.readLine().equals("Jump")){
                            //save into line effect array and save the frame number it begins at
                            lineEffects[countLineEffects++] = "Jump";
                            lineEffects[countLineEffects++] = br.readLine();
                            lineEffects[countLineEffects++] = br.readLine(); //x position of jump
                            lineEffects[countLineEffects++] = br.readLine(); //y position of jump
                        }
                        //if there is a change color effect
                        else if(br.readLine().equals("ChangeColor")){
                            //save into line effect array and save the frame number it begins at
                            lineEffects[countLineEffects++] = "ChangeColor";
                            lineEffects[countLineEffects++] = br.readLine();
                            String[] rgbC = br.readLine().split(","); //this line holds the rgb code, split at the comma and save into rgb array
                            //save the rgb color code into the line effect array
                            lineEffects[countLineEffects++] = rgbC[0];
                            lineEffects[countLineEffects++] = rgbC[1];
                            lineEffects[countLineEffects++] = rgbC[2];
                        }
                    }

                }
                
                
                
                //if the next line is blank, nothing happens. While statement will continue
                else if(br.readLine().equals("")){
                    br.readLine();
                }
                
                //if none of the top options are true, then show them error message
                else{
                    JOptionPane.showMessageDialog(null, "The file you have entered has a unreadable action which cannot be performed. Please make sure that you have entered the correct information into this file.");
                }
            }
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
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
