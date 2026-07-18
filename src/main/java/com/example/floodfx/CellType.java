package com.example.floodfx;

import javafx.scene.paint.Color;

public enum CellType {
    OPEN(Color.WHITE),
    WALL(Color.DARKGRAY),
    WATER(Color.DODGERBLUE),
    EXIT(Color.SPRINGGREEN);

    private final Color color;

    CellType(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}