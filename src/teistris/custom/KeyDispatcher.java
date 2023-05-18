/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teistris.custom;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import javax.sound.sampled.Clip;
import javax.swing.Timer;
import teistris.model.Game;

/**
 *
 * @author Miguel Bastos Gándara & Ainhoa Barros Queimadelos
 */
public class KeyDispatcher implements KeyEventDispatcher {

    private Game game;
    private Timer timer;
    private Clip clip;

    /**
     * Constructor de la clase KeyDispatcher.
     *
     * @param game Objeto del juego actual.
     * @param timer Tiempo del juego.
     */
    public KeyDispatcher(Game game, Timer timer, Clip clip) {
        this.game = game;
        this.timer = timer;
        this.clip = clip;
    }

    /**
     * Método que captura los eventos de las teclas para mover las piezas del
     * juego.
     *
     * @param evt Evento de presionar la tecla.
     * @return True si mueve la pieza, false si no.
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent evt) {
        if (!game.isPaused()) {
            if (evt.getID() == KeyEvent.KEY_PRESSED) {
                switch (evt.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A:
                        game.movePieceLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
                        game.movePieceRight();
                        break;
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        game.movePieceDown();
                        break;
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_R:
                        game.rotatePiece();
                        break;
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_CONTROL:
                        game.setPaused(true);
                        clip.stop();
                        timer.stop();
                        break;
                }
            }
        } else {
            if (evt.getID() == KeyEvent.KEY_PRESSED) {
                if (evt.getKeyCode() == KeyEvent.VK_W || evt.getKeyCode() == KeyEvent.VK_CONTROL) {
                    game.setPaused(false);
                    clip.start();
                    timer.start();
                }
            }
        }
        return false;
    }
}
