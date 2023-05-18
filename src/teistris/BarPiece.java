/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teistris;

import teistris.model.Square;
import teistris.model.Piece;
import teistris.model.Game;
import java.awt.Color;

/**
 * Clase que definde una pieza barra del juego.
 *
 * @author Miguel Bastos Gándara.
 */
public class BarPiece extends Piece {

    /**
     * Referenza á posición da peza
     */
    protected int position = 0;

    /**
     * Construtor da clase, que crea os catro cadrados que forman a peza barra.
     *
     * @param game Partida actual
     */
    public BarPiece(Game game) {
        this.game = game;
        squares[0] = new Square(Game.MAX_X / 2, 0, Color.CYAN, game);
        squares[1] = new Square(Game.MAX_X / 2, Game.SQUARE_SIDE, Color.CYAN, game);
        squares[2] = new Square(Game.MAX_X / 2, Game.SQUARE_SIDE * 2,
                Color.CYAN, game);
        squares[3] = new Square(Game.MAX_X / 2, Game.SQUARE_SIDE * 3, Color.CYAN, game);
    }

    /**
     * Método que implementa el movimiento de una pieza barra.
     *
     * @return true se o movemento da ficha é posible, se non false
     */
    @Override
    public boolean rotate() {
        switch (position) {
            case 0:
                if (!game.isValidPosition(squares[0].getX() - Game.SQUARE_SIDE, squares[0].getY() + Game.SQUARE_SIDE)
                        || !game.isValidPosition(squares[2].getX() + Game.SQUARE_SIDE, squares[2].getY() - Game.SQUARE_SIDE)
                        || !game.isValidPosition(squares[3].getX() + +Game.SQUARE_SIDE * 2, squares[3].getY() - Game.SQUARE_SIDE * 2)) {
                    return false;
                } else {
                    squares[0].setX(squares[0].getX() - Game.SQUARE_SIDE);
                    squares[0].setY(squares[0].getY() + Game.SQUARE_SIDE);
                    squares[2].setX(squares[2].getX() + Game.SQUARE_SIDE);
                    squares[2].setY(squares[2].getY() - Game.SQUARE_SIDE);
                    squares[3].setX(squares[3].getX() + Game.SQUARE_SIDE * 2);
                    squares[3].setY(squares[3].getY() - Game.SQUARE_SIDE * 2);
                    position++;
                    return true;
                }
            default:
                if (!game.isValidPosition(squares[0].getX() + Game.SQUARE_SIDE, squares[0].getY() - Game.SQUARE_SIDE)
                        || !game.isValidPosition(squares[2].getX() - Game.SQUARE_SIDE, squares[2].getY() + Game.SQUARE_SIDE)
                        || !game.isValidPosition(squares[3].getX() - Game.SQUARE_SIDE * 2, squares[3].getY() + Game.SQUARE_SIDE * 2)) {
                    return false;
                } else {
                    squares[0].setX(squares[0].getX() + Game.SQUARE_SIDE);
                    squares[0].setY(squares[0].getY() - Game.SQUARE_SIDE);
                    squares[2].setX(squares[2].getX() - Game.SQUARE_SIDE);
                    squares[2].setY(squares[2].getY() + Game.SQUARE_SIDE);
                    squares[3].setX(squares[3].getX() - Game.SQUARE_SIDE * 2);
                    squares[3].setY(squares[3].getY() + Game.SQUARE_SIDE * 2);
                    position = 0;
                    return true;
                }
        }
    }
}
