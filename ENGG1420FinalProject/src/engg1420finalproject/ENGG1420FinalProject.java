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

/**
 *
 * @author harja
 */
public class ENGG1420FinalProject extends Application {
    
        public Circle createCircle(int[] circleData){
            Circle circle = new Circle();
            circle.setCenterX(circleData[0]);
            circle.setCenterY(circleData[1]);
            circle.setRadius(circleData[2]);
            circle.setFill(Color.BLUE);
            return circle;
        }
        
        public Rectangle createRect(int[] rectData){
            Rectangle rect = new Rectangle();
            rect.setX(rectData[0]);
            rect.setY(rectData[1]);
            rect.setWidth(rectData[2]);
            rect.setHeight(rectData[3]);
            rect.setFill(Color.RED);
            
            return rect;
        }
        
        public Line createLine(int[] lineData){
            Line line = new Line();
            line.setStartX(lineData[0]);
            line.setStartY(lineData[1]);
            line.setEndX(lineData[2]);
            line.setEndY(lineData[3]);
            line.setFill(Color.BLACK);
            
            return line;
        }
    @Override
    public void start(Stage primaryStage) {
        int[] circleData =  {200, 150, 50};
        int[] rectData = {200, 150, 100, 100};
        int[] lineData = {200, 150, 500, 150};
        
        primaryStage.setTitle("Testing Circle");
        
        Circle circle = createCircle(circleData);
        Rectangle rect = createRect(rectData);
        Line line = createLine(lineData);
        
        Group root = new Group(circle, rect, line);
        
        Scene scene = new Scene(root, 400, 300);
        
        primaryStage.setScene(scene);
        
        primaryStage.show();

//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
//        Scene scene = new Scene(root, 400, 300);
//        
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
