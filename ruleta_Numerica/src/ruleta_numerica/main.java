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
import javafx.stage.Stage;
import static clases.anillo.eliminar;
import static clases.anillo.rotarDerecha;
import static clases.anillo.rotarIzquierda;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

public class main extends Application {

    public static Group ruleta = new Group();
    public static Group ruleta2 = new Group();
    public static HBox p1 = new HBox();
    public static HBox p2 = new HBox();
    public static HBox p3 = new HBox();
    public static CircularDoublyLinkedList<Integer> c1 = new CircularDoublyLinkedList<>();
    public static CircularDoublyLinkedList<Integer> c2 = new CircularDoublyLinkedList<>();
    public static ComboBox combo = new ComboBox();
    public static ComboBox combo2 = new ComboBox();
    public static Button derecha = new Button("Girar derecha");
    public static Button izquierda = new Button("Girar izquierda");
    public static Button eliminar = new Button("Eliminar");
    public static Button comodin = new Button("Comodin");
    public static Button salir=new Button("Salir");
    public static Button reiniciar=new Button("Reiniciar");
    public static CircularPane circulo2 = new CircularPane();
    public static int total;
    public static int numCircul;
    public static int apuestaA;
    public static Button ayuda;
    public static Stage ventana;
    public static int turno;
    public static Label operacion = new Label("");

    @Override
    public void start(Stage stage) {
        // Elementos que se encontraran al principio del juego
        Label l1 = new Label("Número de círculos: ");
        Label l2 = new Label("Tamaño círculo: ");
        Label l3 = new Label("Apuesta inicial: ");
        TextField t1 = new TextField();
        TextField t2 = new TextField();
        TextField t3 = new TextField();

        turno = 3;
        CircularDoublyLinkedList<CircularDoublyLinkedList<Integer>> ctodas = new CircularDoublyLinkedList<>();

      // Boton play permitirá a usuario jugar a la ruleta numerica
        Button play = new Button("Play");
        play.setAlignment(Pos.CENTER);

       // Boton ayuda aconsejará al usuario en el caso de que no sepa como se juega
        ayuda = new Button("Ayuda");
        AyudaUsuario(ayuda);
        
        HBox panel2 = new HBox();
        VBox panel1 = new VBox();

        panel2.setSpacing(10);

        // Aquí se ajustará los TextField para que tengan margen dentro del panel
        panel2.setMargin(t1, new Insets(20, 20, 20, 20));
        panel2.setMargin(t2, new Insets(20, 20, 20, 20));
        panel2.setMargin(t3, new Insets(20, 20, 20, 20));

        // Aqui se crean objetivos de tipo ObservableList para agregar elementos en los paneles.
        ObservableList list = panel2.getChildren();
        ObservableList list2 = panel1.getChildren();
        
        // Botones que por ahora no se mostraran en la pantalla
        combo.setVisible(false);
        combo2.setVisible(false);
        izquierda.setVisible(false);
        derecha.setVisible(false);
        eliminar.setVisible(false);
        comodin.setVisible(false);
        
        // metodos para el desarrollo del juego
        moverIzquierda(izquierda, ctodas);
        moverDerecha(derecha, ctodas);
        eliminarInd(eliminar, ctodas);
        pressComodin(comodin);
        
        // Agregando elementos al panel 2
        list.addAll(l1, t1, l2, t2, l3, t3);
        botones(play, c1, c2, t1, t2, t3, ctodas);

        p1.setSpacing(40);
        p1.setAlignment(Pos.CENTER);
        p2.setSpacing(40);
        p2.setAlignment(Pos.CENTER);
        panel1.setSpacing(10);
        // Agregando elementos al panel principal
        list2.addAll(panel2, play, ayuda, operacion, p1, combo, izquierda, derecha,p2, p3, reiniciar, salir);
        
        // Creacion de la escena y stage del programa
        Scene scene = new Scene(panel1, 1100, 700);
        stage.setTitle("Ruleta Numérica");
        stage.setScene(scene);
        stage.show();
    }
    // Metodo que creará y mostrará los círculos al usuario 
    public static void crearCirculo(CircularDoublyLinkedList<CircularDoublyLinkedList<Integer>> ctodas) {
        p1.getChildren().clear();

        for (int i = 0; i <= ctodas.size() - 1; i++) {
            Group ruletaTemp = new Group();
            CircularPane pane = new CircularPane();
            for (int e = 0; e <= ctodas.get(i).size() - 1; e++) {

                Button button = new Button("" + ctodas.get(i).get(e));
                pane.getChildren().add(button);
            }
            ruletaTemp.getChildren().addAll(pane);
            p1.getChildren().addAll(ruletaTemp);
        }

        sumar(ctodas);
        Label lTot1 = new Label("Total de suma: ");
        Label lTot2 = new Label(Integer.toString(total));
        p2.getChildren().clear();
        p2.getChildren().addAll(lTot1, lTot2);
    }
    // Metodo que suma los valores que tienen los circulos
    public static void sumar(CircularDoublyLinkedList<CircularDoublyLinkedList<Integer>> ctodas) {
        int k = 0;
        for (int i = 0; i <= ctodas.size() - 1; i++) {

            for (int e = 0; e <= ctodas.get(i).size() - 1; e++) {
                k = k + ctodas.get(i).get(e);
            }
        }
        total = k;
        if (total == apuestaA) {
            Ganaste();
        }
    }
    // Metodo que sirve para llenar el ComboBox que mostrará los circulos que hay en la pantalla
    public static void llenarCombo(CircularDoublyLinkedList<CircularDoublyLinkedList<Integer>> ctodas) {
        for (int i = 0; i <= ctodas.size() - 1; i++) {
            combo.getItems().add("Circulo " + (i + 1));
        }
    }
     // Metodo que sirve para llenar el ComboBox que mostrará los indices de los circulos
    public static void llenarCombo2(CircularDoublyLinkedList<Integer> c1) {
        combo2.getItems().clear();
        for (int i = 0; i <= c1.size() - 1; i++) {
            combo2.getItems().add((i + 1));
        }
    }
    // Metodo que tendra las acciones que haran ciertos botones
    public static void botones(Button play, CircularDoublyLinkedList c1, CircularDoublyLinkedList c2, TextField t1, TextField t2, TextField t3, CircularDoublyLinkedList<CircularDoublyLinkedList<Integer>> ctodas) {
        reiniciar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                JOptionPane.showMessageDialog(null, "Volvamos a empezar");
                try {
                    restartApplication();
                } catch (URISyntaxException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        salir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                JOptionPane.showMessageDialog(null, "Gracias por jugar");
                System.exit(0);
            }
        });
        
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

                } else if (!convInt(aux1) || !convInt(aux2) || !convInt(aux3)) {
                    Label lError1 = new Label("Porfavor ingrese solo numeros Naturales.");
                    p1.getChildren().clear();
                    p1.getChildren().addAll(lError1);
                } else {
                    int tamano = Integer.parseInt(t2.getText());
                    numCircul = Integer.parseInt(t1.getText());
                    ctodas.clear();
                    for (int i = 0; i <= numCircul - 1; i++) {
                        CircularDoublyLinkedList<Integer> c = new CircularDoublyLinkedList<>();
                        ctodas.addLast(c);
                    }

                    apuestaA = Integer.parseInt(t3.getText());

                    llenarCombo(ctodas);

                    for (int i = 0; i <= ctodas.size() - 1; i++) {
                        for (int e = 0; e <= tamano - 1; e++) {
                            int numAleatorio = (int) (Math.random() * 10);
                            ctodas.get(i).addLast(numAleatorio);
                        }
                    }

                    Label escojaEli = new Label("indice a eliminar: ");
                    p3.getChildren().clear();
                    p3.getChildren().addAll(escojaEli, combo2, eliminar, comodin);

                    crearCirculo(ctodas);
                    play.setVisible(false);
                    eliminar.setVisible(true);
                    combo2.setVisible(true);
                    combo.setVisible(true);
                    izquierda.setVisible(true);
                    derecha.setVisible(true);
                    comodin.setVisible(true);

                    llenarCombo2(ctodas.get(0));

                }
            }
        });

    }
    // Metodo que moverá el círculo a la izquierda
    public static void moverIzquierda(Button izquierda, CircularDoublyLinkedList<CircularDoublyLinkedList<Integer>> ctodas) {
        izquierda.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (turno == 0) {
                    operacion.setText("Te toca eliminar");

                } else {

                    p1.getChildren().clear();

                    String value = (String) combo.getValue();
                    CircularDoublyLinkedList<CircularDoublyLinkedList<Integer>> cRotar = new CircularDoublyLinkedList<>();
                    CircularDoublyLinkedList<Integer> cGuardar = new CircularDoublyLinkedList<>();
                    for (int i = 0; i <= ctodas.size() - 1; i++) {

                        if (value.equals("Circulo " + (i + 1))) {
                            cRotar.addLast(rotarIzquierda(ctodas.get(i)));
                            cGuardar = (cRotar.get(i));

                        } else {
                            cRotar.addLast(ctodas.get(i));
                        }
                    }

                    ctodas.clear();
                    for (int i = 0; i <= cRotar.size() - 1; i++) {
                        ctodas.addLast(cRotar.get(i));

                    }

                    crearCirculo(ctodas);
                    sumar(ctodas);
                    Label lTot1 = new Label("Total de suma: ");
                    Label lTot2 = new Label(Integer.toString(total));
                    p2.getChildren().clear();
                    p2.getChildren().addAll(lTot1, lTot2);
                    tieneNegativoOmas12(cGuardar);
                    turno = 0;
                    operacion.setText("Te toca eliminar");
                }

            }
        });
    }
    // Metodo que moverá el circulo a la derecha
    public static void moverDerecha(Button derecha, CircularDoublyLinkedList<CircularDoublyLinkedList<Integer>> ctodas) {
        derecha.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (turno == 0) {
                    operacion.setText("Te toca eliminar");
                } else {

                    p1.getChildren().clear();

                    String value = (String) combo.getValue();
                    CircularDoublyLinkedList<CircularDoublyLinkedList<Integer>> cRotar = new CircularDoublyLinkedList<>();
                    CircularDoublyLinkedList<Integer> cGuardar = new CircularDoublyLinkedList<>();
                    for (int i = 0; i <= ctodas.size() - 1; i++) {

                        if (value.equals("Circulo " + (i + 1))) {
                            cRotar.addLast(rotarDerecha(ctodas.get(i)));
                            cGuardar=(cRotar.get(i));
                        } else {
                            cRotar.addLast(ctodas.get(i));
                        }
                    }
                    ctodas.clear();
                    for (int i = 0; i <= cRotar.size() - 1; i++) {
                        ctodas.addLast(cRotar.get(i));

                    }

                    crearCirculo(ctodas);
                    sumar(ctodas);

                    Label lTot1 = new Label("Total de suma: ");
                    Label lTot2 = new Label(Integer.toString(total));
                    p2.getChildren().clear();
                    p2.getChildren().addAll(lTot1, lTot2);
                    tieneNegativoOmas12(cGuardar);
                    turno = 0;
                    operacion.setText("Te toca eliminar");
                }
            }
        });
    }
    // Metodo encargado de eliminar el indice que señale el usuario
    public static void eliminarInd(Button eliminar, CircularDoublyLinkedList<CircularDoublyLinkedList<Integer>> ctodas) {
        eliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (turno == 1) {
                    operacion.setText("Te toca girar");
                } else {

                    if (combo2.getValue() == null) {
                    } else {
                        Integer value = (Integer) combo2.getValue();
                        CircularDoublyLinkedList<CircularDoublyLinkedList<Integer>> ctodasTEMP = new CircularDoublyLinkedList<>();
                        for (int i = 0; i <= ctodas.size() - 1; i++) {
                            ctodasTEMP.addLast(eliminar(ctodas.get(i), value - 1));
                        }
                        ctodas.clear();
                        for (int i = 0; i <= ctodasTEMP.size() - 1; i++) {
                            ctodas.addLast(ctodasTEMP.get(i));
                        }
                        crearCirculo(ctodas);
                        sumar(ctodas);
                        Label lTot1 = new Label("Total de suma: ");
                        Label lTot2 = new Label(Integer.toString(total));
                        p2.getChildren().clear();
                        p2.getChildren().addAll(lTot1, lTot2);
                        llenarCombo2(ctodas.get(0));
                        if (ctodas.get(0).isEmpty()) {
                            p1.getChildren().clear();
                            p2.getChildren().clear();
                            Perdiste();
                            Label l5 = new Label("Se quedo sin objetos a eliminar");
                            p2.getChildren().addAll(l5);
                        }
                        turno = 1;
                        operacion.setText("Te toca girar");
                    }
                }
            }
        });
    }
    // Metodo que reinicia la aplicacion
    public static void restartApplication() throws URISyntaxException, IOException { 
        final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
        final File currentJar = new File(main.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        /* is it a jar file? */
        if (!currentJar.getName().endsWith(".jar")) {
            return; /* Build command: java -jar application.jar */
        }
        final ArrayList<String> command = new ArrayList<String>();
        command.add(javaBin);
        command.add("-jar");
        command.add(currentJar.getPath());
        final ProcessBuilder builder = new ProcessBuilder(command);
        builder.start();
        System.exit(0);
    }

    // Metodo que le dará un comodín al usuario
    public static void pressComodin(Button comodin) {
        comodin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (turno == 1) {
                    turno = 0;
                    operacion.setText("Te toca eliminar");
                    comodin.setVisible(false);
                } else if (turno == 0) {
                    turno = 1;
                    operacion.setText("Te toca girar");
                    comodin.setVisible(false);
                }else{
                    
                }
            }
        });
    }
    // Metodo que verificará si hay numeros negativos en la ruleta 
    public static void tieneNegativoOmas12(CircularDoublyLinkedList<Integer> c1) {
        for (int i = 0; i <= c1.size() - 1; i++) {
            if (c1.get(i) < 0) {
                p1.getChildren().clear();
                p2.getChildren().clear();
                Perdiste();
                Label l5 = new Label("un numero es menor a 0 ");
                p2.getChildren().addAll(l5);
            }else if(c1.get(i) > 12){
                p1.getChildren().clear();
                p2.getChildren().clear();
                Perdiste();
                Label l5 = new Label("un numero es mayor a 12 ");
                p2.getChildren().addAll(l5);
            }
        }
    }
    // Metodo que le indicará al usuario si perdio
    public static void Perdiste() {
        combo.setVisible(false);
        izquierda.setVisible(false);
        derecha.setVisible(false);
        p1.getChildren().clear();
        p2.getChildren().clear();
        p3.getChildren().clear();
        Label l4 = new Label("--PERDISTE--");
        p1.getChildren().addAll(l4);
    }
    // Metodo que le indicará al usuario si gano
    public static void Ganaste() {
        combo.setVisible(false);
        izquierda.setVisible(false);
        derecha.setVisible(false);
        p1.getChildren().clear();
        p2.getChildren().clear();
        p3.getChildren().clear();
        Label l4 = new Label("--Ganaste--");
        p1.getChildren().addAll(l4);
    }
    // Metodo que le dará unas instrucciones al usuario acerca de como se juega
    public static void AyudaUsuario(Button ayuda) {
        ayuda.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String mensaje = "1.Llenar los datos que le solicitan en la pantalla\n 2. Una vez seleccionado le aparecerá el "
                        + "# de círculos solicitados con la suma de valores que se encuentran en los círculos.\n" + "3.Abajo le aparecerá"
                        + "herramientas que le permitirán girar o eliminar la ruleta, recuerde que si primero pone girar, la siguiente operación"
                        + "tiene que ser eliminar y así sucesivamente.\n" + "4. Recuerde que al girar la ruleta a la derecha le aumentará en uno los valores del círculo respectivo"
                        + "en cambio, si gira a la izquierda decrecerán en uno y por lo tanto en los 2 casos le cambiará la suma total.\n"
                        + "5. Tendrá que seguir haciendo estas operaciones hasta que la suma total sea igual a la cantidad que puso en Apuesta inical, en ese caso usted gana"
                        + "sin embargo en el caso que no lo logré perderá ya sea porque la suma sea menor, le salga algún número negativo o si te quedas sin número.\n" + "Espero que disfrute del Juego.";
                Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
                dialogo.setTitle("Instrucciones");
                dialogo.setHeaderText("Instrucciones");
                dialogo.setContentText(mensaje);
                dialogo.initStyle(StageStyle.UTILITY);
                dialogo.showAndWait();
            }
        });
    }

    //Saber si una cadena puede ser convertida a Natural (sin contar el 0)
    public static boolean convInt(String a) {
        int length = a.length();
        if (a == null) {
            return false;
        }
        if (length == 0) {
            return false;
        }
        if (a.charAt(0) == '-' || a.charAt(0) == '0') {
            return false;
        }
        for (int i = 0; i < length; i++) {
            char c = a.charAt(i);
            if (c > '9' || c < '0') {
                return false;
            }
        }
        return true;
    }
   
    public static void main(String args[]) {
        launch(args);
    }

}
