package com.example.chess;

   public abstract class Piece {

       private boolean white;
       private Square square;

       public Piece(boolean white, Square square) {
           this.white = white;
           this.square = square;
           square.setPiece(this);
       }

       public boolean isWhite() {
           return white;
       }

       public Square getSquare() {
           return square;
       }

       public void setSquare(Square square) {
           this.square = square;
       }

       public abstract boolean canMove(SquaretoCol = toSquare.getCol();

       // Check if the move is valid for a pawn
    int rowDiff = toRow - fromRow;
    int colDiff = Math.abs(toCol - fromCol);
    if (isWhite()) {
        if (rowDiff == -1 && colDiff == 0 && toSquare.getPiece() == null) {
            return true;
        } else if (rowDiff == -2 && colDiff == 0 && fromRow == 6 && toSquare.getPiece() == null && squares[fromRow - 1][fromCol].getPiece() == null) {
            return true;
        } else if (rowDiff == -1 && colDiff == 1 && toSquare.getPiece() != null && !toSquare.getPiece().isWhite()) {
            return true;
        }
    } else {
        if (rowDiff == 1 && colDiff == 0 && toSquare.getPiece() == null) {
            return true;
        } else if (rowDiff == 2 && colDiff == 0 && fromRow == 1 && toSquare.getPiece() == null && squares[fromRow + 1][fromCol].getPiece() == null) {
            return true;
        } else if (rowDiff == 1 && colDiff == 1 && toSquare.getPiece() != null && toSquare.getPiece().isWhite()) {
            return true;
        }
    }

    return false;
}


class Rook extends Piece {
    public Rook(boolean white, Square square) {
        super(white, square);
    }
    
    @Override
    public boolean canMove(Square toSquare) {
        int fromRow = getSquare().getRow();
        int fromCol = getSquare().getCol();
        int toRow = toSquare.getRow();
        int toCol = toSquare.getCol();
    
        // Check if the move is valid for a rook
        if (fromRow == toRow || fromCol == toCol) {
            // Check if there are any pieces in the way
            int rowStep = fromRow == toRow ? 0 : (fromRow < toRow ? 1 : -1);
            int colStep = fromCol == toCol ? 0 : (fromCol < toCol ? 1 : -1);
            int row = fromRow + rowStep;
            int col = fromCol + colStep;
            while (row != toRow || col != toCol) {
                if (getSquare().getPiece() != null) {
                    return false;
                }
                row += rowStep;
                col += colStep;
            }
            return true;
        }
    
        return false;
    }
}

class Knight extends Piece {

    public Knight(boolean white, Square square) {
        super(white, square);
    }
    
    @Override
    public boolean canMove(Square toSquare) {
        int fromRow = getSquare().getRow();
        int fromCol = getSquare().getCol();
        int toRow = toSquare.getRow();
        int toCol = toSquare.getCol();
    
        // Check if the move is valid for a knight
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }
}

class Bishop extends Piece {
    public Bishop(boolean white, Square square) {
        super(white, square);
    }
    
    @Override
    public boolean canMove(Square toSquare) {
        int fromRow = getSquare().getRow();
        int fromCol = getSquare().getCol();
        int toRow = toSquare.getRow();
        int toCol = toSquare.getCol();
    
        // Check if the move is valid for a bishop
        if (Math.abs(toRow - fromRow) == Math.abs(toCol - fromCol)) {
            // Check if there are any pieces in the way
            int rowStep = fromRow < toRow ? 1 : -1;
            int colStep = fromCol < toCol ? 1 : -1;
            int row = fromRow + rowStep;
            int col = fromCol + colStep;
            while (row != toRow || col != toCol) {
                if (getSquare().getPiece() != null) {
                    return false;
                }
                row += rowStep;
                col += colStep;
            }
            return true;
        }
    
        return false;
    }
}

class Queen extends Piece {
    public Queen(boolean white, Square square) {
        super(white, square);
    }
    
    @Override
    public boolean canMove(Square toSquare) {
        int fromRow = getSquare().getRow();
        int fromCol = getSquare().getCol();
        int toRow = toSquare.getRow();
        int toCol = toSquare.getCol();
        // Check if the move is valid for a queen
    if (fromRow == toRow || fromCol == toCol) {
        // Check if there are any pieces in the way
        int rowStep = fromRow == toRow ? 0 : (fromRow < toRow ? 1 : -1);
        int colStep = fromCol == toCol ? 0 : (fromCol < toCol ? 1 : -1);
        int row = fromRow + rowStep;
        int col = fromCol + colStep;
        while (row != toRow || col != toCol) {
            if (getSquare().getPiece() != null) {
                return false;
            }
            row += rowStep;
            col += colStep;
        }
        return true;
    } else if (Math.abs(toRow - fromRow) == Math.abs(toCol - fromCol)) {
        // Check if there are any pieces in the way
        int rowStep = fromRow < toRow ? 1 : -1;
        int colStep = fromCol < toCol ? 1 : -1;
        int row = fromRow + rowStep;
        int col = fromCol + colStep;
        while (row != toRow || col != toCol) {
            if (getSquare().getPiece() != null) {
                return false;
            }
            row += rowStep;
            col += colStep;
        }
        return true;
    }

    return false;
    }
}

class King extends Piece {
    public King(boolean white, Square square) {
        super(white, square);
    }
    
    @Override
    public boolean canMove(Square toSquare) {
        int fromRow = getSquare().getRow();
        int fromCol = getSquare().getCol();
        int toRow = toSquare.getRow();
        int toCol = toSquare.getCol();
    
        // Check if the move is valid for a king
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        return rowDiff <= 1 && colDiff <= 1;
    }
}

class Square extends Pane {
    private int row;
private int col;
private Piece piece;

public Square(int row, int col) {
    this.row = row;
    this.col = col;
    setPrefSize(50, 50);
    setStyle((row + col) % 2 == 0 ? "-fx-background-color: white;" : "-fx-background-color: gray;");
    setOnMouseClicked(e -> handleMouseClick());
}

public int getRow() {
    return row;
}

public int getCol() {
    return col;
}

public Piece getPiece() {
    return piece;
}

public void setPiece(Piece piece) {
    this.piece = piece;
    getChildren().clear();
    if (piece != null) {
        getChildren().add(piece);
    }
}

private void handleMouseClick() {
    if (ChessBoard.selectedSquare == null) {
        // Select this square
        if (piece != null && piece.isWhite() == ChessBoard.whiteTurn) {
            setStyle("-fx-background-color: lightblue;");
            ChessBoard.selectedSquare = this;
        }
    } else {
        // Move the piece to this square
        if (ChessBoard.selectedSquare.getPiece().canMove(this)) {
            Piece piece = ChessBoard.selectedSquare.getPiece();
            ChessBoard.selectedSquare.setPiece(null);
            setPiece(piece);
            ChessBoard.whiteTurn = !ChessBoard.whiteTurn;
        }
        ChessBoard.selectedSquare.setStyle((getRow() + getCol()) % 2 == 0 ? "-fx-background-color: white;" : "-fx-background-color: gray;");
        ChessBoard.selectedSquare = null;
    }
}
}

class Pawn extends Piece {
    public Pawn(boolean white, Square square) {
        super(white, square);
    }
    
    @Override
    public boolean canMove(Square toSquare) {
        Square[][] squares = getSquare().getParent().getChildren().stream()
                .filter(node -> node instanceof Square)
                .map(node -> (Square) node)
                .toArray(Square[][]::new);
    
        int fromRow = getSquare().getRow();
        int fromCol = getSquare().getCol();
        int toRow = toSquare.getRow();
        int toCol = toSquare.getCol();
    
        // Check if the move is valid for a pawn
        int rowDiff = toRow - fromRow;
        int colDiff = Math.abs(toCol - fromCol);
        if (isWhite()) {
            if (rowDiff == -1 && colDiff == 0 && toSquare.getPiece() == null) {
                return true;
            } else if (rowDiff == -2 && colDiff == 0 && fromRow == 6 && toSquare.getPiece() == null && squares[fromRow - 1][fromCol].getPiece() == null) {
                return true;
                } else if (rowDiff == -1 && colDiff == 1 && toSquare.getPiece() != null && !toSquare.getPiece().isWhite()) {
                return true;
                }
                } else {
                if (rowDiff == 1 && colDiff == 0 && toSquare.getPiece() == null) {
                return true;
                } else if (rowDiff == 2 && colDiff == 0 && fromRow == 1 && toSquare.getPiece() == null && squares[fromRow + 1][fromCol].getPiece() == null) {
                return true;
                } else if (rowDiff == 1 && colDiff == 1 && toSquare.getPiece() != null && toSquare.getPiece().isWhite()) {
                return true;
                }
                }return false;
            }
}
