/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ruleta_numerica;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
// Clase que sirve para que los nodos se muestren al usuario de forma circular
public class CircularPane extends Pane{
    @Override
    protected void layoutChildren() {
        final int radio = 100;
        final double incremento = 360 / getChildren().size();
        double grados = 0;
        for (Node node : getChildren()) {
            double valor_x = radio * Math.cos(Math.toRadians(grados)) + getWidth() / 2;
            double valor_y = radio * Math.sin(Math.toRadians(grados)) + getHeight() / 2;
            layoutInArea(node, valor_x - node.getBoundsInLocal().getWidth() / 2, valor_y - node.getBoundsInLocal().getHeight() / 2, getWidth(), getHeight(), 0.0, HPos.LEFT, VPos.TOP);
            grados += incremento;
        }
    }
    
}
