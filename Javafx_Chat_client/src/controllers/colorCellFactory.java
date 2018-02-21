/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;

/**
 *
 *  
 * A class which displays colored boxes in the chat window which allows the user to choose different colors for his message.
 */
public class colorCellFactory implements Callback<ListView<Color>, ListCell<Color>> {

    @Override
    public ListCell<Color> call(ListView<Color> param) {
        return new ListCell<Color>() {
            private final Rectangle rect;

            {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                rect = new Rectangle(20, 20);
            }

            @Override
            protected void updateItem(Color item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    rect.setFill(item);
                    setGraphic(rect);
                }
            }
        };
    }
}
