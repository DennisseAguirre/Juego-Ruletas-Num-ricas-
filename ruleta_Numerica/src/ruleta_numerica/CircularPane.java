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

/**
 *
 * @author demera
 */
public class CircularPane extends Pane{
    @Override
    protected void layoutChildren() {
        final int radius = 100;
        final double increment = 360 / getChildren().size();
        double degreese = 0;
        for (Node node : getChildren()) {
            double x = radius * Math.cos(Math.toRadians(degreese)) + getWidth() / 2;
            double y = radius * Math.sin(Math.toRadians(degreese)) + getHeight() / 2;
            layoutInArea(node, x - node.getBoundsInLocal().getWidth() / 2, y - node.getBoundsInLocal().getHeight() / 2, getWidth(), getHeight(), 0.0, HPos.LEFT, VPos.TOP);
            degreese += increment;
        }
    }
    
}
