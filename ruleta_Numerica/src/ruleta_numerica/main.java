/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ruleta_numerica;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author demera
 */
public class main extends Application {
    
    @Override
    public void start(Stage stage) {
        Label l1=new Label("Número de círculos: ");
      Label l2=new Label("Tamaño círculo: ");
      Label l3=new Label("Apuesta inicial: ");
      TextField t1 = new TextField();       
      TextField t2 = new TextField();
      TextField t3= new TextField();
      Circle circle = new Circle(300, 135, 100); 

      Button play = new Button("Play");
      play.setAlignment(Pos.CENTER);
       
      HBox panel2 = new HBox();
      VBox panel1 = new VBox();  
      
      
      panel2.setSpacing(10);    
      
      panel2.setMargin(t1, new Insets(20, 20, 20, 20)); 
      panel2.setMargin(t2, new Insets(20, 20, 20, 20)); 
      panel2.setMargin(t3, new Insets(20, 20, 20, 20)); 
      
      ObservableList list = panel2.getChildren(); 
      ObservableList list2 = panel1.getChildren(); 
     
      list.addAll(l1,t1,l2,t2,l3,t3); 
      list2.addAll(panel2,play,circle);
     
      Scene scene = new Scene(panel1,900,500);  
      
      stage.setTitle("Ruleta Numérica"); 
         
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
