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

public class AnimationPlayer {
    int frames;
    int speed;
    int elements;
    
    public AnimationPlayer(){
        frames = 1000;
        speed = 10;
        elements = 2;
    }
    
    public AnimationPlayer(int frames, int speed, int elements){
        this.frames = frames;
        this.speed = speed;
        this.elements = elements;
    }
    
    
    public void loadAnimationFromFile(String file){
        try{
            File f = new File(file);
            Scanner s = new Scanner(f);
            while(s.hasNextLine()){
                //scans the first 3 lines to create an animation player with its 
                AnimationPlayer player = new AnimationPlayer(getInt(s.nextLine()), getInt(s.nextLine()), getInt(s.nextLine()));
                String[] shapes = new String[elements + 1];
                int i = 0;
                s.nextLine();
                if(s.nextLine().equals("Circle")){
                    shapes[i] = "Circle";
                    i++;
                    Circle c = new Circle();
                    c.setRadius(getInt(s.nextLine()));
                    c.setCenterX(getInt(s.nextLine()));
                    c.setCenterY(getInt(s.nextLine()));
                    String[] rgb = s.nextLine().split(",");
                    Color color = Color.rgb(getInt(rgb[0]), getInt(rgb[1]), getInt(rgb[2]));
                    c.setFill(color);      
                }
                
                else if(s.nextLine().equals("Rect")){
                    shapes[i] = "Rect";
                    i++;
                    
                    Rectangle r = new Rectangle();
                    r.setHeight(getInt(s.nextLine()));
                    r.setWidth(getInt(s.nextLine()));
                    r.setX(getInt(s.nextLine()));
                    r.setY(getInt(s.nextLine()));
                    r.setStrokeWidth(getInt(s.nextLine()));
                }
                
                else if(s.nextLine().equals("Line")){
                    shapes[i] = "Line";
                    i++;
                    
                    Line l = new Line();
                   
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println(e);
        }
    }
    
    
    
    public int getInt(String s){
        int r = Integer.parseInt(s);
        return r;
    }

}
