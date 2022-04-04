/*
Harjas Sran, Samy Rashad, Affan Ahmed, Sayansh Verma
Final Project
ENGG 1420
Animation Player
Main method
 */
package ENGG1420FinalProject;


/**
 *
 * @author harja
 */
public class ENGG1420FinalProject {

    public static void main(String[] args){
        //using object oreinted programming, create animation player object
        animationPlayer2 player = new animationPlayer2();
        //load text file into animation player
        player.loadAnimationFromFile("src\\engg1420finalproject\\Test.txt");
        //run what was read from the animation player text file
        player.run();
    }

       
    
}
