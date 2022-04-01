/*
Animation Player class
 */
package engg1420finalproject;

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

public class AnimationPlayer {
    int frames;
    int speed;
    int elements;
    static Shape[] shapes = {null};
    
    public AnimationPlayer(){
        frames = 1000;
        speed = 10;
        elements = 2;
        shapes = new Shape[elements];
    }
    
    public AnimationPlayer(int frames, int speed, int elements){
        this.frames = frames;
        this.speed = speed;
        this.elements = elements;
        shapes = new Shape[elements +1];
    }
    
    static public void loadFile(String file){
        
        try{
            File f = new File(file);
            Scanner s = new Scanner(f);
            while(s.hasNextLine()){
                if(s.nextLine().equals("Circle")){
                    
                    Circle c = new Circle();
                    c.setRadius(getInt(s.nextLine()));
                    c.setCenterX(getInt(s.nextLine()));
                    c.setCenterY(getInt(s.nextLine()));
                    String[] rgb = s.nextLine().split(",");
                    Color color = Color.rgb(getInt(rgb[0]), getInt(rgb[1]), getInt(rgb[2]));
                    c.setFill(color);     
                    shapes[0] = c;
                }
                
                else if(s.nextLine().equals("Rect")){

                    
                    Rectangle r = new Rectangle();
                    r.setHeight(getInt(s.nextLine()));
                    r.setWidth(getInt(s.nextLine()));
                    r.setX(getInt(s.nextLine()));
                    r.setY(getInt(s.nextLine()));
                    r.setStrokeWidth(getInt(s.nextLine()));
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println(e);
        }
    }
    
    
    static public void loadAnimationFromFile(String file){
        try{
            File f = new File(file);
            Scanner s = new Scanner(f);
            while(s.hasNextLine()){

//                this.frames = getInt(s.nextLine());
//                this.speed = getInt(s.nextLine());
//                this.elements = getInt(s.nextLine());
//                
                int i = 0;
                s.nextLine();
                if(s.nextLine().equals("Circle")){
                    
                    
                    Circle c = new Circle();
                    c.setRadius(getInt(s.nextLine()));
                    c.setCenterX(getInt(s.nextLine()));
                    c.setCenterY(getInt(s.nextLine()));
                    String[] rgb = s.nextLine().split(",");
                    Color color = Color.rgb(getInt(rgb[0]), getInt(rgb[1]), getInt(rgb[2]));
                    c.setFill(color);      
                    shapes[i] = c;
                    i++;
                }
                
                else if(s.nextLine().equals("Rect")){

                    
                    Rectangle r = new Rectangle();
                    r.setHeight(getInt(s.nextLine()));
                    r.setWidth(getInt(s.nextLine()));
                    r.setX(getInt(s.nextLine()));
                    r.setY(getInt(s.nextLine()));
                    r.setStrokeWidth(getInt(s.nextLine()));
                    
                    shapes[i] = r;
                    i++;
                }
                
                /**
                else if(s.nextLine().equals("Line")){
                    shapes[i] = "Line";
                    i++;
                    
                    Line l = new Line();
                   
                }
                 **/
            }
        }
        catch(FileNotFoundException e){
            System.out.println(e);
        }
    }
    
    
    
    static public int getInt(String s){
        int r = Integer.parseInt(s);
        return r;
    }
    
    

}
