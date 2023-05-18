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

import teistris.view.MainWindow;
import java.util.HashMap;
import teistris.BarPiece;
import teistris.LPiece;
import teistris.SquarePiece;
import teistris.TPiece;
import teistris.view.MainWindowMainh;

/**
 * Clase que implementa o comportamento do xogo do Tetris
 *
 * @author Profe de Programación
 */
public class Game {

    /**
     * Constante que define o tamaño en pixels do lado dun cadrado
     */
    public final static int SQUARE_SIDE = 20;

    /**
     * Constante que define o valor máximo da coordenada x no panel de cadrados
     */
    public final static int MAX_Y = 400;

    /**
     * Constante que define o valor máximo da coordenada x no panel de cadrados
     */
    public final static int MAX_X = 280;

    /**
     * HashMap que garda os cadrados e as súas coordenadas en formato String
     */
    private HashMap<String, Square> groundSquares;

    /**
     * Referenza á peza actual do xogo, que é a única que se pode mover
     */
    private Piece currentPiece;

    /**
     * Referenza á ventá principal do xogo
     */
    private MainWindow mainWindow;

    /**
     * Referenza á ventá principal do xogo
     */
    private MainWindowMainh mainWindowMainh;

    /**
     * Flag que indica se o xogo está en pausa ou non
     */
    private boolean paused;

    /**
     * Número de liñas feitas no xogo
     */
    private int numberOfLines;

    /**
     * @return Referenza á ventá principal do xogo
     */
    public MainWindow getMainWindow() {
        return mainWindow;
    }

    /**
     * @param mainWindow Ventá principal do xogo a establecer
     */
    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * Dvuelve la a ventana principal del juego.
     *
     * @return Referencia a la ventana principal del juego.
     */
    public MainWindowMainh getMainWindowMainh() {
        return mainWindowMainh;
    }

    /**
     * Cambia la ventana principal del juego.
     *
     * @param mainWindowMainh Ventana principla del juego a establecer.
     */
    public void setMainWindowMainh(MainWindowMainh mainWindowMainh) {
        this.mainWindowMainh = mainWindowMainh;
    }

    /**
     * @return O estado de pausa do xogo
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * @param paused O estado de pausa a establecer
     */
    public void setPaused(boolean paused) {
        this.paused = paused;
        if (paused) {
            mainWindowMainh.getjToggleButtonStopPlay().setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/stopped.png")));
        } else {
            mainWindowMainh.getjToggleButtonStopPlay().setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/played.png")));
        }
    }

    /**
     * @return Número de liñas feitas no xogo
     */
    public int getNumberOfLines() {
        return numberOfLines;
    }

    /**
     * @param numberOfLines O número de liñas feitas no xogo
     */
    public void setNumberOfLines(int numberOfLines) {
        this.numberOfLines = numberOfLines;
    }

    /**
     * Construtor da clase, que crea unha primeira peza
     *
     * @param mainWindow Referenza á ventá principal do xogo
     */
    public Game(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.numberOfLines = 0;
        this.paused = false;
        this.groundSquares = new HashMap();
        this.createNewPiece(this);
    }

    /**
     * Construtor da clase, que crea unha primeira peza
     *
     * @param mainWindowMainh Referenza á ventá principal do xogo MAINH
     */
    public Game(MainWindowMainh mainWindowMainh) {
        this.mainWindowMainh = mainWindowMainh;
        this.numberOfLines = 0;
        this.paused = false;
        this.groundSquares = new HashMap();
        this.createNewPiece(this);
    }

    /**
     * Move a ficha actual á dereita, se o xogo non está pausado
     */
    public void movePieceRight() {
        if (!paused) {
            currentPiece.moveRight();
        }
    }

    /**
     * Move a ficha actual á esquerda, se o xogo non está pausado
     */
    public void movePieceLeft() {
        if (!paused) {
            currentPiece.moveLeft();
        }
    }

    /**
     * Rota a ficha actual, se o xogo non está pausado
     */
    public void rotatePiece() {
        if (!paused) {
            currentPiece.rotate();
        }
    }

    /**
     * Move a peza actual abaixo, se o xogo non está pausado Se a peza choca con
     * algo e xa non pode baixar, pasa a formar parte do chan e créase unha nova
     * peza
     */
    public void movePieceDown() {
        if ((!paused) && (!currentPiece.moveDown())) {
            this.addPieceToGround();
            this.createNewPiece(this);
            if (this.hitPieceTheGround()) {
                this.mainWindowMainh.showGameOver();
            }
        }
    }

    /**
     * Método que permite saber se unha posición x,y é válida para un cadrado
     *
     * @param x Coordenada x
     * @param y Coordenada y
     * @return true se esa posición é válida, se non false
     */
    public boolean isValidPosition(int x, int y) {
        return !((x == MAX_X) || (x < 0) || (y == MAX_Y) || (groundSquares.containsKey(x + "," + y)));
    }

    /**
     * Crea unha nova peza e a establece como peza actual do xogo
     */
    private void createNewPiece(Game game) {
        int pieceType = new java.util.Random().nextInt(4);
        switch (pieceType) {
            case 0:
                currentPiece = new SquarePiece(game);
                break;
            case 1:
                currentPiece = new BarPiece(game);
                break;
            case 2:
                currentPiece = new LPiece(game);
                break;
            default:
                currentPiece = new TPiece(game);
                break;
        }
    }

    /**
     * Engade unha peza ao chan
     */
    private void addPieceToGround() {
        // Engadimos os cadrados da peza ao chan
        for (Square s : currentPiece.getSquares()) {
            groundSquares.put(s.getCoordinates(), s);
        }
        // Chamamos ao método que borra as liñas do chan que estean completas
        this.deleteCompletedLines();
    }

    /**
     * Se os cadrados que están forman unha liña completa, bórranse eses
     * cadrados do chan e súmase unha nova liña no número de liñas realizadas
     */
    private void deleteCompletedLines() {
        for (int y = MAX_Y - SQUARE_SIDE; y >= 0; y -= SQUARE_SIDE) {
            boolean emptyCell = false;
            for (int x = 0; (x < MAX_X) && (!emptyCell); x += SQUARE_SIDE) {
                // Si encuentra algún cuadrado vacío, no borra esta línea
                if (!groundSquares.containsKey(x + "," + y)) {
                    emptyCell = true;
                }
            }
            // Si no hay cuadrados vacíos, borra la línea
            if (!emptyCell) {
                deleteLine(y);
                mainWindowMainh.showNumberOfLines(++numberOfLines);
                y += SQUARE_SIDE;
            }
        }
    }

    /**
     * Borra todos os cadrados que se atopan na liña indicada pola coordenada y,
     * e baixa todos os cadrados que estean situados por enriba unha posición
     * cara abaixo
     *
     * @param y Coordenada y da liña a borrar
     */
    private void deleteLine(int y) {
        for (int x = 0; x < MAX_X; x += SQUARE_SIDE) {
            // Borra los cuadrados tanto del HashMap como del panel
            Square removedSquare = groundSquares.remove(x + "," + y);
            mainWindowMainh.deleteSquare(removedSquare.getLblSquare());
        }
        for (int z = y - SQUARE_SIDE; z >= 0; z -= SQUARE_SIDE) {
            for (int x = 0; x < MAX_X; x += SQUARE_SIDE) {
                // Si hay cuadrados por encima al borrar la fila, los borra
                // de su HashMap actual, los baja un SQUARE_SIDE y los mete
                // en el HashMap que tiene debajo
                if (groundSquares.containsKey(x + "," + z)) {
                    Square moveSquare = groundSquares.remove(x + "," + z);
                    moveSquare.setY(z + SQUARE_SIDE);
                    groundSquares.put(moveSquare.getCoordinates(), moveSquare);
                }
            }
        }
    }

    /**
     * Comproba se a peza actual choca cos cadrados do chan
     *
     * @return true se a peza actual choca cos cadrados do chan; se non false
     */
    private boolean hitPieceTheGround() {
        for (int y = MAX_Y - SQUARE_SIDE; y >= 0; y -= SQUARE_SIDE) {
            for (int x = 0; x < MAX_X; x += SQUARE_SIDE) {
                if (groundSquares.containsKey(x + "," + SQUARE_SIDE)) {
                    mainWindowMainh.getTimer().stop();
                    return true;
                }
            }
        }
        return false;
    }
}
