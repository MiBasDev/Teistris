/*
 * Copyright (C) 2019 Antonio de Andrés Lema
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

 /*
@modificadores Miguel Bastos Gándara & Ainhoa Barros Queimadelos
 */
package teistris.model;

/**
 * Clase que implementa a peza cadrada do xogo do Tetris
 *
 * @author Profe de Programación
 */
public abstract class Piece {

    /**
     * Referenza ao obxecto xogo
     */
    protected Game game;

    /**
     * Referenzas aos catro cadrados que forman a peza
     */
    protected Square[] squares = new Square[4];

    /**
     * Devolve o array de cadrados
     *
     * @return Array de cadrados
     */
    public Square[] getSquares() {
        return squares;
    }

    /**
     * Devolve a partida do xogo
     *
     * @return Partida do xogo
     */
    public Game getGame() {
        return game;
    }

    /**
     * Cambia o valor de game
     *
     * @param game Partida do xogo
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Move a ficha á dereita se é posible
     *
     * @return true se o movemento da ficha é posible, se non false
     */
    public boolean moveRight() {

        for (Square s : squares) {
            if (!game.isValidPosition(s.getX() + Game.SQUARE_SIDE, s.getY())) {
                return false;
            }
        }

        for (Square s : squares) {
            s.setX(s.getX() + Game.SQUARE_SIDE);
        }
        return true;
    }

    /**
     * Move a ficha á esquerda se é posible
     *
     * @return true se o movemento da ficha é posible, se non false
     */
    public boolean moveLeft() {

        for (Square s : squares) {
            if (!game.isValidPosition(s.getX() - Game.SQUARE_SIDE, s.getY())) {
                return false;
            }
        }

        for (Square s : squares) {
            s.setX(s.getX() - Game.SQUARE_SIDE);
        }
        return true;
    }

    /**
     * Move a ficha a abaixo se é posible
     *
     * @return true se o movemento da ficha é posible, se non false
     */
    public boolean moveDown() {

        for (Square s : squares) {
            if (!game.isValidPosition(s.getX(), s.getY() + Game.SQUARE_SIDE)) {
                return false;
            }
        }

        for (Square s : squares) {
            s.setY(s.getY() + Game.SQUARE_SIDE);
        }
        return true;
    }

    /**
     * Rota a ficha se é posible
     *
     * @return true se o movemento da ficha é posible, se non false
     */
    public boolean rotate() {
//         A rotación da ficha cadrada non supón ningunha variación na ficha,
//         por iso simplemente devolvemos true
        return true;
    }

}
