/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package teistris.view;

import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.Timer;
import teistris.custom.KeyDispatcher;
import teistris.model.Game;

/**
 * Clase que implementa la ventana principal del juego del Tetris.
 *
 * @author Miguel Bastos Gándara & Ainhoa Barros Queimadelos.
 */
public class MainWindowMainh extends javax.swing.JFrame {

    private Timer timer; // Referencia al timer del juego actual
    private Game game = null; // Referenza ao obxecto do xogo actual
    private KeyDispatcher dispatcher;
    private Clip clip; // Referencia a la música del juego actual

    /**
     * Devuelve el jToggleButton de parar o accionar el juego. Lo usamos para
     * poder cambiar su icono desde el setPaused del game.
     *
     * @return ToggleButton de parar o accionar el juego.
     */
    public JToggleButton getjToggleButtonStopPlay() {
        return jToggleButtonStopPlay;
    }

    /**
     * Devuelve el timer del juego.
     *
     * @return Timer del juego.
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * Cambia el timer del juego.
     *
     * @param timer Timer del juego.
     */
    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    /**
     * Creates new form MainWindowMAINH
     */
    public MainWindowMainh() {
        initComponents();
        int delay = 1000; //milliseconds
        this.timer = new Timer(delay, (ActionEvent e) -> {
            jBtnDownActionPerformed(e);
            proccessTick();
        });
    }

    /**
     * Método para iniciar la música del juego.
     */
    private void playSound() {
        try {
            File archivoMusica = new File("/home/mbasgan/Documentos/1º DAW/Programación/NetBeansProjects/Teistris/src/Music/Tetris.wav");
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(archivoMusica));
            clip.start();
            if (!clip.isRunning()) {
                clip.setMicrosecondPosition(0);
                clip.start();
            } else {
                clip.setMicrosecondPosition(0);
                clip.stop();
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Pinta un cadrado no panel de cadrados
     *
     * @param lblSquare Etiqueta co cadrado que se quere pintar no panel
     */
    public void drawSquare(JLabel lblSquare) {
        jPnlGame.add(lblSquare);
        jPnlGame.repaint();
    }

    /**
     * Borra un cadrado do panel de cadrados
     *
     * @param lblSquare Etiqueta co cadrado que se quere borrar do panel
     */
    public void deleteSquare(JLabel lblSquare) {
        jPnlGame.remove(lblSquare);
        jPnlGame.repaint();
    }

    /**
     * Actualiza na ventá o número de liñas que van feitas no xogo
     *
     * @param numberOfLines Número de liñas feitas no xogo
     */
    public void showNumberOfLines(int numberOfLines) {
        jLblNumLines.setText(String.valueOf(numberOfLines));
        if (numberOfLines % 10 == 0 && numberOfLines != 0) {
            timer.setDelay(timer.getDelay() - 100);
        }
    }

    /**
     * Mostra unha mensaxe informando ao usuario do final do xogo
     */
    public void showGameOver() {
        game = null;
        timer.restart();
        timer.stop();
        clip.stop();
        jToggleButtonStopPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/stopped.png")));
        KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(dispatcher);
        JOptionPane.showMessageDialog(this, "Fin do xogo");
        timer.restart();
        timer.stop();
    }

    /**
     * Inicia un novo xogo
     */
    private void startGame() {
        // Limpamos todo o que puidese haber pintado no panel do xogo
        jPnlGame.removeAll();
        // Creamos un novo obxecto xogo
        game = new Game(this);
        // Desactivamos o botón de pausa
        jToggleButtonStopPlay.setSelected(false);
        // Establecemos o número de liñas que se mostran na ventá a cero
        jLblNumLines.setText("0");
        jLblTimer.setText("0");
        playSound();
        timer.start();
        this.dispatcher = new KeyDispatcher(game, timer, clip);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(dispatcher);
    }

    private void proccessTick() {
        int n = Integer.parseInt(jLblTimer.getText()) + 1;
        jLblTimer.setText(Integer.toString(n));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPnlGame = new javax.swing.JPanel();
        jLblTitle = new javax.swing.JLabel();
        jLblLines = new javax.swing.JLabel();
        jLblNumLines = new javax.swing.JLabel();
        jBtnStartNewGame = new javax.swing.JButton();
        jBtnLeft = new javax.swing.JButton();
        jBtnRight = new javax.swing.JButton();
        jBtnDown = new javax.swing.JButton();
        jBtnRotate = new javax.swing.JButton();
        jToggleButtonStopPlay = new javax.swing.JToggleButton();
        jLblTimer = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MAINHTRIS");
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPnlGame.setBackground(new java.awt.Color(232, 232, 232));
        jPnlGame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPnlGame.setFocusable(false);
        jPnlGame.setMaximumSize(new java.awt.Dimension(280, 400));
        jPnlGame.setMinimumSize(new java.awt.Dimension(280, 400));
        jPnlGame.setPreferredSize(new java.awt.Dimension(280, 400));

        javax.swing.GroupLayout jPnlGameLayout = new javax.swing.GroupLayout(jPnlGame);
        jPnlGame.setLayout(jPnlGameLayout);
        jPnlGameLayout.setHorizontalGroup(
            jPnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );
        jPnlGameLayout.setVerticalGroup(
            jPnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );

        jLblTitle.setFont(new java.awt.Font("Nimbus Sans L", 1, 24)); // NOI18N
        jLblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLblTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/mainhtris-logo-color.png"))); // NOI18N

        jLblLines.setFont(new java.awt.Font("Nimbus Sans L", 1, 18)); // NOI18N
        jLblLines.setText("Filas:");

        jLblNumLines.setFont(new java.awt.Font("Nimbus Sans L", 1, 18)); // NOI18N
        jLblNumLines.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        jBtnStartNewGame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/start.png"))); // NOI18N
        jBtnStartNewGame.setBorderPainted(false);
        jBtnStartNewGame.setContentAreaFilled(false);
        jBtnStartNewGame.setFocusPainted(false);
        jBtnStartNewGame.setFocusable(false);
        jBtnStartNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnStartNewGameActionPerformed(evt);
            }
        });

        jBtnLeft.setText("LEFT");
        jBtnLeft.setFocusable(false);
        jBtnLeft.setMaximumSize(new java.awt.Dimension(40, 28));
        jBtnLeft.setMinimumSize(new java.awt.Dimension(40, 28));
        jBtnLeft.setPreferredSize(new java.awt.Dimension(40, 28));
        jBtnLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLeftActionPerformed(evt);
            }
        });

        jBtnRight.setText("RIGHT");
        jBtnRight.setFocusable(false);
        jBtnRight.setMaximumSize(new java.awt.Dimension(40, 28));
        jBtnRight.setMinimumSize(new java.awt.Dimension(40, 28));
        jBtnRight.setPreferredSize(new java.awt.Dimension(40, 28));
        jBtnRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRightActionPerformed(evt);
            }
        });

        jBtnDown.setText("DOWN");
        jBtnDown.setFocusable(false);
        jBtnDown.setMaximumSize(new java.awt.Dimension(40, 28));
        jBtnDown.setMinimumSize(new java.awt.Dimension(40, 28));
        jBtnDown.setPreferredSize(new java.awt.Dimension(40, 28));
        jBtnDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDownActionPerformed(evt);
            }
        });

        jBtnRotate.setText("ROTATE");
        jBtnRotate.setFocusable(false);
        jBtnRotate.setMaximumSize(new java.awt.Dimension(40, 28));
        jBtnRotate.setMinimumSize(new java.awt.Dimension(40, 28));
        jBtnRotate.setPreferredSize(new java.awt.Dimension(40, 28));
        jBtnRotate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRotateActionPerformed(evt);
            }
        });

        jToggleButtonStopPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/stopped.png"))); // NOI18N
        jToggleButtonStopPlay.setBorderPainted(false);
        jToggleButtonStopPlay.setContentAreaFilled(false);
        jToggleButtonStopPlay.setFocusPainted(false);
        jToggleButtonStopPlay.setFocusable(false);
        jToggleButtonStopPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonStopPlayActionPerformed(evt);
            }
        });

        jLblTimer.setFont(new java.awt.Font("Nimbus Sans L", 1, 18)); // NOI18N
        jLblTimer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLblTimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/timer.png"))); // NOI18N
        jLblTimer.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBtnLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBtnRotate, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBtnDown, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jBtnRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPnlGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLblLines, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBtnStartNewGame, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLblNumLines, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLblTimer, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74)
                                .addComponent(jToggleButtonStopPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jBtnStartNewGame, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jToggleButtonStopPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jLblTimer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLblNumLines, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLblLines, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPnlGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnRotate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jBtnLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jBtnRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDownActionPerformed
        if (game != null) {
            game.movePieceDown();
        }
    }//GEN-LAST:event_jBtnDownActionPerformed

    private void jBtnLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLeftActionPerformed
        if (game != null) {
            game.movePieceLeft();
        }
    }//GEN-LAST:event_jBtnLeftActionPerformed

    private void jBtnRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRightActionPerformed
        if (game != null) {
            game.movePieceRight();
        }
    }//GEN-LAST:event_jBtnRightActionPerformed

    private void jBtnRotateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRotateActionPerformed
        if (game != null) {
            game.rotatePiece();
        }
    }//GEN-LAST:event_jBtnRotateActionPerformed

    private void jToggleButtonStopPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonStopPlayActionPerformed
        if (game != null) {
            game.setPaused(jToggleButtonStopPlay.isSelected());
            if (timer.isRunning()) {
                clip.stop();
                timer.stop();
            } else {
                clip.start();
                timer.start();
            }
        }
    }//GEN-LAST:event_jToggleButtonStopPlayActionPerformed

    private void jBtnStartNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnStartNewGameActionPerformed
        startGame();
        timer.restart();
        timer.start();
        jToggleButtonStopPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/played.png")));
    }//GEN-LAST:event_jBtnStartNewGameActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        requestFocus();
    }//GEN-LAST:event_formKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindowMainh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindowMainh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindowMainh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindowMainh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindowMainh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnDown;
    private javax.swing.JButton jBtnLeft;
    private javax.swing.JButton jBtnRight;
    private javax.swing.JButton jBtnRotate;
    private javax.swing.JButton jBtnStartNewGame;
    private javax.swing.JLabel jLblLines;
    private javax.swing.JLabel jLblNumLines;
    private javax.swing.JLabel jLblTimer;
    private javax.swing.JLabel jLblTitle;
    private javax.swing.JPanel jPnlGame;
    private javax.swing.JToggleButton jToggleButtonStopPlay;
    // End of variables declaration//GEN-END:variables
}
