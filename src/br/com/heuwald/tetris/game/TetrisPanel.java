package br.com.heuwald.tetris.game;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import br.com.heuwald.tetris.game.engine.Engine;
import br.com.heuwald.tetris.game.engine.EngineListener;
import br.com.heuwald.tetris.game.graphics.Pintura;

public class TetrisPanel extends JPanel implements EngineListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Engine en;
	int blockSize = 2;

	Pintura p;

	public TetrisPanel(Engine en) {
		this.en = en;
		this.en.engineListener.add(this);
		p = new Pintura(en, this);
		//	 criaListener();
	}

	public KeyListener getKeyListener() {
		return new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("T2");
				if (!en.isPauseGame()) {
					if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
						en.pressRight();
						atualiza = true;
						repaint();
					}
					if (e.getKeyCode() == KeyEvent.VK_LEFT) {
						en.pressLeft();
						atualiza = true;
						repaint();
					}

					if (e.getKeyCode() == KeyEvent.VK_UP) {
						en.pressUp();
						atualiza = true;
						repaint();
					}

					if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						en.pressDown();
						atualiza = true;
						repaint();
					}

					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						en.restart();
						atualiza = true;
						repaint();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_P) {
					en.setPauseGame(!en.isPauseGame());
					atualiza = true;
					repaint();
				}

			}
		};
	}

	boolean atualiza = true;
	Image im;
	@Override
	public void paint(Graphics g) {
		if(atualiza){
			im = p.pintaTela();
			atualiza = false;
		}
		g.drawImage(im, 0, 0, this);

	}

	@Override
	public void descePeca() {
		atualiza = true;
		repaint();
	}

	@Override
	public void gameOver() {
		if(p.pintaFim()) {
			atualiza = true;
			repaint();
		}
	}
}
