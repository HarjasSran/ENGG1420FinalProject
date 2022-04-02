/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ENGG1420FinalProject;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.shape.Shape;
import javafx.animation.AnimationTimer;

/**
 *
 * @author harja
 */
public class ENGG1420FinalProject extends Application {
    
//        public Circle createCircle(int[] circleData){
//            Circle circle = new Circle();
//            circle.setCenterX(circleData[0]);
//            circle.setCenterY(circleData[1]);
//            circle.setRadius(circleData[2]);
//            circle.setFill(Color.BLUE);
//            circle.setStroke(Color.BLACK);
//            circle.setStrokeWidth(10);
//            return circle;
//        }
        
//        public Rectangle createRect(int[] rectData){
//            Rectangle rect = new Rectangle();
//            rect.setX(rectData[0]);
//            rect.setY(rectData[1]);
//            rect.setWidth(rectData[2]);
//            rect.setHeight(rectData[3]);
//            rect.setFill(Color.RED);
//            rect.setStroke(Color.WHITE);
//            rect.setStrokeWidth(10);
//            
//            return rect;
//        }
//        
//        public Line createLine(int[] lineData){
//            Line line = new Line();
//            line.setStartX(lineData[0]);
//            line.setStartY(lineData[1]);
//            line.setEndX(lineData[2]);
//            line.setEndY(lineData[3]);
//            line.setFill(Color.BLACK);
//            line.setStrokeWidth(10);
//            
//            return line;
//        }
        
        
        
    @Override
    public void start(Stage stage) {
        
        
        stage.setTitle("Testing Circle");
        
        AnimationPlayer.loadAnimationFromFile("src\\engg1420finalproject\\Test.txt");

        Shape circle = AnimationPlayer.shapes[0];
//        Circle circle = createCircle(circleData);
        
        Group root = new Group(circle);
        
        Scene scene = new Scene(root, 400, 300);
        
        stage.setScene(scene);
        
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
    }
    
}
