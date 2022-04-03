/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engg1420finalproject;

import static ENGG1420FinalProject.AnimationPlayer.getInt;
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
    
    
    
    public void run(Stage stage) {

        stage.setTitle("Animation Player");
        
        Shape circle = shapes[0];
        Group root = new Group(circle);
        Scene scene = new Scene(root,400,300);
        
        stage.setScene(scene);
        stage.show();
        
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
                    Circle c = new Circle();
                    c.setRadius(getInt(br.readLine()));
                    c.setCenterX(getInt(br.readLine()));
                    c.setCenterY(getInt(br.readLine()));
                    String[] rgb = br.readLine().split(",");
                    Color color = Color.rgb(getInt(rgb[0]), getInt(rgb[1]), getInt(rgb[2]));
                    c.setFill(color); 
                    c.setStrokeWidth(getInt(br.readLine()));
                    String[] rgb2 = br.readLine().split(",");
                    Color color2 = Color.rgb(getInt(rgb2[0]), getInt(rgb2[1]), getInt(rgb2[2]));
                    c.setStroke(color2);
                    //add the circle into the shape array of AnimationPlayer
                    shapes[counter++] = c;
                    if(br.readLine().equals("effects")){
                        if(br.readLine().equals("Show")){
                            circleEffects[countCircleEffects++] = "Show";
                            circleEffects[countCircleEffects++] = br.readLine();
                        }
                        else if(br.readLine().equals("Hide")){
                            circleEffects[countCircleEffects++] = "Hide";
                            circleEffects[countCircleEffects++] = br.readLine();
                        }
                        else if(br.readLine().equals("Jump")){
                            circleEffects[countCircleEffects++] = "Jump";
                            circleEffects[countCircleEffects++] = br.readLine();
                            circleEffects[countCircleEffects++] = br.readLine();
                            circleEffects[countCircleEffects++] = br.readLine();
                        }
                        else if(br.readLine().equals("ChangeColor")){
                            circleEffects[countCircleEffects++] = "ChangeColor";
                            circleEffects[countCircleEffects++] = br.readLine();
                            String[] rgbC = br.readLine().split(",");
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
                    Rectangle r = new Rectangle();
                    r.setHeight(getInt(br.readLine()));
                    r.setWidth(getInt(br.readLine()));
                    r.setX(getInt(br.readLine()));
                    r.setY(getInt(br.readLine()));
                    r.setStrokeWidth(getInt(br.readLine()));
                    String[] rgb = br.readLine().split(",");
                    Color color = Color.rgb(getInt(rgb[0]), getInt(rgb[1]), getInt(rgb[2]));
                    r.setFill(color); 
                    String[] rgb2 = br.readLine().split(",");
                    Color color2 = Color.rgb(getInt(rgb2[0]), getInt(rgb2[1]), getInt(rgb2[2]));
                    r.setStroke(color2);
                    //add rectangle to shape array of AnimationPlayer
                    shapes[counter++] = r;
                    if(br.readLine().equals("effects")){
                        if(br.readLine().equals("Show")){
                            rectEffects[countRectEffects++] = "Show";
                            rectEffects[countRectEffects++] = br.readLine();
                        }
                        else if(br.readLine().equals("Hide")){
                            rectEffects[countRectEffects++] = "Hide";
                            rectEffects[countRectEffects++] = br.readLine();
                        }
                        else if(br.readLine().equals("Jump")){
                            rectEffects[countRectEffects++] = "Jump";
                            rectEffects[countRectEffects++] = br.readLine();
                            rectEffects[countRectEffects++] = br.readLine();
                            rectEffects[countRectEffects++] = br.readLine();
                        }
                        else if(br.readLine().equals("ChangeColor")){
                            rectEffects[countRectEffects++] = "ChangeColor";
                            rectEffects[countRectEffects++] = br.readLine();
                            String[] rgbC = br.readLine().split(",");
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
                    Line l = new Line();
                    l.setStartX(getInt(br.readLine()));
                    l.setStartY(getInt(br.readLine()));
                    l.setEndX(getInt(br.readLine()));
                    l.setEndY(getInt(br.readLine()));
                    l.setStrokeWidth(getInt(br.readLine()));
                    String[] rgb = br.readLine().split(",");
                    Color color = Color.rgb(getInt(rgb[0]), getInt(rgb[1]), getInt(rgb[2]));
                    l.setStroke(color);
                    
                    //add line to the shape array of AnimationPlayer
                    shapes[counter++] = l;
                    if(br.readLine().equals("effects")){
                        if(br.readLine().equals("Show")){
                            lineEffects[countLineEffects++] = "Show";
                            lineEffects[countLineEffects++] = br.readLine();
                        }
                        else if(br.readLine().equals("Hide")){
                            lineEffects[countLineEffects++] = "Hide";
                            lineEffects[countLineEffects++] = br.readLine();
                        }
                        else if(br.readLine().equals("Jump")){
                            lineEffects[countLineEffects++] = "Jump";
                            lineEffects[countLineEffects++] = br.readLine();
                            lineEffects[countLineEffects++] = br.readLine();
                            lineEffects[countLineEffects++] = br.readLine();
                        }
                        else if(br.readLine().equals("ChangeColor")){
                            lineEffects[countLineEffects++] = "ChangeColor";
                            lineEffects[countLineEffects++] = br.readLine();
                            String[] rgbC = br.readLine().split(",");
                            lineEffects[countLineEffects++] = rgbC[0];
                            lineEffects[countLineEffects++] = rgbC[1];
                            lineEffects[countLineEffects++] = rgbC[2];
                        }
                    }

                }
                
                
                
                //if the next line is blank, nothing happens. While statement will continue
                else if(br.readLine().equals("")){
                    
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

    
    
   

    
    
}
