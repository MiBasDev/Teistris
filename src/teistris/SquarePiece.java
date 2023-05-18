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
 * Clase de define una pieza cuadrada.
 *
 * @author Miguel Bastos Gándara.
 */
public class SquarePiece extends Piece {

    /**
     * Referenza á posición da peza
     */
    protected int position = 0;

    /**
     * Construtor da clase, que crea os catro cadrados que forman a peza
     * cadrado.
     *
     * @param game Partida actual
     */
    public SquarePiece(Game game) {
        this.game = game;
        squares[0] = new Square(Game.MAX_X / 2 - Game.SQUARE_SIDE, 0, Color.RED, game);
        squares[1] = new Square(Game.MAX_X / 2, 0, Color.RED, game);
        squares[2] = new Square(Game.MAX_X / 2 - Game.SQUARE_SIDE, Game.SQUARE_SIDE,
                Color.RED, game);
        squares[3] = new Square(Game.MAX_X / 2, Game.SQUARE_SIDE, Color.GRAY, game);
    }

    /**
     * Método que implementa el movimiento de una pieza cuadrada.
     *
     * @return true se o movemento da ficha é posible, se non false
     */
    @Override
    public boolean rotate() {
        switch (position) {
            case 0:
                squares[0].setX(squares[0].getX() + Game.SQUARE_SIDE);
                squares[1].setY(squares[1].getY() + Game.SQUARE_SIDE);
                squares[2].setY(squares[2].getY() - Game.SQUARE_SIDE);
                squares[3].setX(squares[3].getX() - Game.SQUARE_SIDE);
                position++;
                break;
            case 1:
                squares[0].setY(squares[0].getY() + Game.SQUARE_SIDE);
                squares[1].setX(squares[1].getX() - Game.SQUARE_SIDE);
                squares[2].setX(squares[2].getX() + Game.SQUARE_SIDE);
                squares[3].setY(squares[3].getY() - Game.SQUARE_SIDE);
                position++;
                break;
            case 2:
                squares[0].setX(squares[0].getX() - Game.SQUARE_SIDE);
                squares[1].setY(squares[1].getY() - Game.SQUARE_SIDE);
                squares[2].setY(squares[2].getY() + Game.SQUARE_SIDE);
                squares[3].setX(squares[3].getX() + Game.SQUARE_SIDE);
                position++;
                break;
            default:
                squares[0].setY(squares[0].getY() - Game.SQUARE_SIDE);
                squares[1].setX(squares[1].getX() + Game.SQUARE_SIDE);
                squares[2].setX(squares[2].getX() - Game.SQUARE_SIDE);
                squares[3].setY(squares[3].getY() + Game.SQUARE_SIDE);
                position = 0;
                break;
        }
        return true;
    }
}
