/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ruleta_numerica;


import clases.CircularDoublyLinkedList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class main extends Application {

    public static Group ruleta = new Group();
    public static Group ruleta2 = new Group();
    public static HBox p1 = new HBox();
    public static CircularPane circulo1 = new CircularPane();
    public static CircularDoublyLinkedList c1;
    public static CircularDoublyLinkedList c2;

    public static CircularPane circulo2 = new CircularPane();

    @Override
    public void start(Stage stage) {
        Label l1 = new Label("Número de círculos: ");
        Label l2 = new Label("Tamaño círculo: ");
        Label l3 = new Label("Apuesta inicial: ");
        TextField t1 = new TextField();
        TextField t2 = new TextField();
        TextField t3 = new TextField();

//Creating the play button 
        Button play = new Button("Play");
        play.setAlignment(Pos.CENTER);
        //Instantiating the HBox class  
        HBox panel2 = new HBox();
        VBox panel1 = new VBox();

        //Setting the space between the nodes of a HBox pane 
        panel2.setSpacing(10);

        //Setting the margin to the nodes 
        panel2.setMargin(t1, new Insets(20, 20, 20, 20));
        panel2.setMargin(t2, new Insets(20, 20, 20, 20));
        panel2.setMargin(t3, new Insets(20, 20, 20, 20));

        // creando circulos
        //retrieving the observable list of the HBox 
        ObservableList list = panel2.getChildren();
        ObservableList list2 = panel1.getChildren();

        //Adding all the nodes to the observable list (HBox) 
        list.addAll(l1, t1, l2, t2, l3, t3);
        String inputText = t2.getText();
        CircularDoublyLinkedList<Integer> c1 = new CircularDoublyLinkedList();
        CircularDoublyLinkedList<Integer> c2 = new CircularDoublyLinkedList();
        botones(play, c1, c2, t2);
        p1.setSpacing(40);
        p1.setAlignment(Pos.CENTER);
        list2.addAll(panel2, play, p1);

        //Creating a scene object
        Scene scene = new Scene(panel1, 900, 500);

        //Setting title to the Stage 
        stage.setTitle("Ruleta Numérica");

        //Adding scene to the stage 
        stage.setScene(scene);

        //Displaying the contents of the stage 
        stage.show();
    }

    public static void crearCirculo(CircularDoublyLinkedList c1, CircularDoublyLinkedList c2) {
        CircularPane pane = new CircularPane();
        for (int i = 0; i < c1.size(); i++) {
            Button button = new Button("" + c1.get(i));
            pane.getChildren().add(button);
        }
        CircularPane pane2 = new CircularPane();
        for (int i = 0; i < c2.size(); i++) {
            Button button2 = new Button("" + c2.get(i));
            pane2.getChildren().add(button2);
        }

        ruleta.getChildren().addAll(pane);
        ruleta2.getChildren().addAll(pane2);
        p1.getChildren().addAll(ruleta, ruleta2);

    }

    public static void botones(Button play, CircularDoublyLinkedList c1, CircularDoublyLinkedList c2, TextField t2) {
        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String usuario = t2.getText();
                if (usuario == null) {
                    JOptionPane.showMessageDialog(null, "No se puede");
                    

                } else {
                    
                    int tamano = Integer.parseInt(usuario);
                    for (int i = 0; i < tamano; i++) {
                        int numAleatorio = (int) (Math.random() * 10);
                        c1.addLast(numAleatorio);

                    }
                    for (int i = 0; i < tamano; i++) {
                        int numAleatorio2 = (int) (Math.random() * 10);
                        c2.addLast(numAleatorio2);

                    }
                    crearCirculo(c1, c2);

                }
            }
        });

        }


   public static void main(String args[]) {
        launch(args);
    }
}

