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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static clases.anillo.eliminar;
import static clases.anillo.rotarDerecha;
import static clases.anillo.rotarIzquierda;

public class main extends Application {

    public static Group ruleta = new Group();
    public static Group ruleta2 = new Group();
    public static HBox p1 = new HBox();
    public static HBox p2 = new HBox();
//    public static CircularPane circulo1 = new CircularPane();
    public static CircularDoublyLinkedList<Integer> c1 = new CircularDoublyLinkedList<>();
    public static CircularDoublyLinkedList<Integer> c2 = new CircularDoublyLinkedList<>();
    public static ComboBox combo = new ComboBox();
    public static Button derecha = new Button("Girar derecha");
    public static Button izquierda = new Button("Girar izquierda");
    public static CircularPane circulo2 = new CircularPane();
    public static int total;

    @Override
    public void start(Stage stage) {
        Label l1 = new Label("Número de círculos: ");
        Label l2 = new Label("Tamaño círculo: ");
        Label l3 = new Label("Apuesta inicial: ");
        TextField t1 = new TextField();
        TextField t2 = new TextField();
        TextField t3 = new TextField();

        CircularDoublyLinkedList<CircularDoublyLinkedList<Integer>> ctodas = new CircularDoublyLinkedList<>();

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
        combo.setVisible(false);
        izquierda.setVisible(false);
        derecha.setVisible(false);
        moverIzquierda(izquierda, combo, ctodas);
        moverDerecha(derecha, combo, ctodas);
        //Adding all the nodes to the observable list (HBox) 
        list.addAll(l1, t1, l2, t2, l3, t3);
        //String inputText = t2.getText();
        botones(play, c1, c2, t1, t2, t3, ctodas);

        llenarCombo();
        p1.setSpacing(40);
        p1.setAlignment(Pos.CENTER);
        p2.setSpacing(40);
        p2.setAlignment(Pos.CENTER);
        panel1.setSpacing(10);
        list2.addAll(panel2, play, p1, combo, izquierda, derecha, p2);

        //Creating a scene object
        Scene scene = new Scene(panel1, 900, 500);

        //Setting title to the Stage 
        stage.setTitle("Ruleta Numérica");

        //Adding scene to the stage 
        stage.setScene(scene);

        //Displaying the contents of the stage 
        stage.show();
    }

    public static void crearCirculo(CircularDoublyLinkedList c1, CircularDoublyLinkedList c2, CircularDoublyLinkedList<CircularDoublyLinkedList<Integer>> ctodas) {
        p1.getChildren().clear();
        CircularPane pane = new CircularPane();
        for (int i = 0; i <= c1.size() - 1; i++) {
            Button button = new Button("" + c1.get(i));
            pane.getChildren().add(button);
        }
        CircularPane pane2 = new CircularPane();
        for (int i = 0; i <= c2.size() - 1; i++) {
            Button button2 = new Button("" + c2.get(i));
            pane2.getChildren().add(button2);
        }
        sumar(ctodas);
        Label lTot1 = new Label("Total de suma: ");
        Label lTot2 = new Label(Integer.toString(total));
        p2.getChildren().clear();
        p2.getChildren().addAll(lTot1, lTot2);

        ruleta.getChildren().clear();
        ruleta2.getChildren().clear();
        ruleta.getChildren().addAll(pane);
        ruleta2.getChildren().addAll(pane2);
        p1.getChildren().addAll(ruleta, ruleta2);

    }

    public static void sumar(CircularDoublyLinkedList<CircularDoublyLinkedList<Integer>> ctodas) {
        int k = 0;
        for (int i = 0; i <= ctodas.size() - 1; i++) {

            for (int e = 0; e <= ctodas.get(i).size() - 1; e++) {
                k = k + ctodas.get(i).get(e);
                System.out.println(k);
            }
        }
        total = k;
    }

    public static void llenarCombo() {
        combo.getItems().add("Circulo 1");
        combo.getItems().add("Circulo 2");
    }

    public static void botones(Button play, CircularDoublyLinkedList c1, CircularDoublyLinkedList c2, TextField t1, TextField t2, TextField t3, CircularDoublyLinkedList<CircularDoublyLinkedList<Integer>> ctodas) {
        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String aux1 = t1.getText().trim();
                String aux2 = t2.getText().trim();
                String aux3 = t3.getText().trim();
                if (aux1.length() == 0 || aux2.length() == 0 || aux3.length() == 0) {
                    Label lError1 = new Label("Porfavor llene todos los espacios.");
                    p1.getChildren().clear();
                    p1.getChildren().addAll(lError1);

                } else {
                    ctodas.addLast(c1);
                    ctodas.addLast(c2);
                    String usuario = t2.getText();
                    String numCirculos = t1.getText();
                    String apuesta = t3.getText();

                    int tamano = Integer.parseInt(usuario);
                    for (int i = 0; i < tamano; i++) {
                        int numAleatorio = (int) (Math.random() * 10);
                        c1.addLast(numAleatorio);

                    }
                    for (int i = 0; i < tamano; i++) {
                        int numAleatorio2 = (int) (Math.random() * 10);
                        c2.addLast(numAleatorio2);

                    }

                    crearCirculo(c1, c2, ctodas);
                    play.setVisible(false);
                    combo.setVisible(true);
                    izquierda.setVisible(true);
                    derecha.setVisible(true);

                }
            }
        });

    }

    public static void moverIzquierda(Button izquierda, ComboBox combo, CircularDoublyLinkedList<CircularDoublyLinkedList<Integer>> ctodas) {
        izquierda.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                p1.getChildren().clear();
                String value = (String) combo.getValue();
                if (value == ("Circulo 1")) {
                    System.out.println(c1.toString());
                    c1 = rotarIzquierda(c1);
                    crearCirculo(c1, c2, ctodas);
                    System.out.println(c1.toString());
                    tieneNegativo(c1);
                } else if (value == ("Circulo 2")) {
                    System.out.println(c2.toString());
                    c2 = rotarIzquierda(c2);
                    crearCirculo(c1, c2, ctodas);
                    System.out.println(c2.toString());
                    tieneNegativo(c2);
                }
                ctodas.clear();
                ctodas.addLast(c1);
                ctodas.addLast(c2);
                sumar(ctodas);
                Label lTot1 = new Label("Total de suma: ");
                Label lTot2 = new Label(Integer.toString(total));
                p2.getChildren().clear();
                p2.getChildren().addAll(lTot1, lTot2);
            }
        });
    }

    public static void moverDerecha(Button derecha, ComboBox combo, CircularDoublyLinkedList<CircularDoublyLinkedList<Integer>> ctodas) {
        derecha.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                p1.getChildren().clear();
                String value = (String) combo.getValue();
                if (value == ("Circulo 1")) {
                    System.out.println(c1.toString());
                    c1 = rotarDerecha(c1);
                    crearCirculo(c1, c2, ctodas);
                    System.out.println(c1.toString());
                } else if (value == ("Circulo 2")) {
                    System.out.println(c2.toString());
                    c2 = rotarDerecha(c2);
                    crearCirculo(c1, c2, ctodas);
                    System.out.println(c2.toString());
                }
                ctodas.clear();
                ctodas.addLast(c1);
                ctodas.addLast(c2);
                sumar(ctodas);
                Label lTot1 = new Label("Total de suma: ");
                Label lTot2 = new Label(Integer.toString(total));
                p2.getChildren().clear();
                p2.getChildren().addAll(lTot1, lTot2);
            }
        });
    }

    public static void tieneNegativo(CircularDoublyLinkedList<Integer> c1) {
        for (int i = 0; i <= c1.size() - 1; i++) {
            if (c1.get(i) < 0) {
                Perdiste();
                Label l5 = new Label("un numero es menor que 0 xD");
                p1.getChildren().addAll(l5);
            }
        }
    }

    public static void Perdiste() {
        combo.setVisible(false);
        izquierda.setVisible(false);
        derecha.setVisible(false);
        p1.getChildren().clear();
        Label l4 = new Label("PERDISTE");
        p1.getChildren().addAll(l4);
    }

    public static void main(String args[]) {
        launch(args);
    }

}
