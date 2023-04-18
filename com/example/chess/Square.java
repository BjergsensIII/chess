package com.example.chess;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends StackPane {

    private Piece piece;
    private int row;
    private int col;

    public Square(int row, int col) {
        this.row = row;
        this.col = col;

        Rectangle square = new Rectangle(50, 50);
        square.setFill((row + col) % 2 == 0 ? Color.WHITE : Color.LIGHTGRAY);
        getChildren().add(square);
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}