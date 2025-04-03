package br.com.heuwald.tetris.game.app;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

import br.com.heuwald.tetris.game.TetrisPanel;
import br.com.heuwald.tetris.game.engine.Engine;

public class App extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Engine en;
	JLabel lblPoints = new JLabel();
	BorderLayout layoutPrincipal = new BorderLayout();
	TetrisPanel tp;
	//    JLabel lblLinhas = new JLabel();

	public App() {

		en = new Engine();

		tp = new TetrisPanel(en);
		tp.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		super.setSize(330, 665);
		super.setResizable(false);
		super.setLocation(200, 200);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setTitle("Visual Tetris");
		super.setLayout(layoutPrincipal);
		super.add(tp, BorderLayout.CENTER);
		super.add(lblPoints, BorderLayout.NORTH);
		super.setMaximumSize(new Dimension(800, 1200));
		super.setIconImage(new ImageIcon(getClass().getResource("/br/com/heuwald/resources/images/icone.png")).getImage());

		super.setVisible(true);

		en.setPauseGame(true);
		en.play();
		criaListener();
	}

	private void criaListener() {
		super.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("T1");
				tp.getKeyListener().keyPressed(e);
				//		if (!en.isPauseGame()) {
				//		    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				//			en.pressRight();
				//		    }
				//		    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				//			en.pressLeft();
				//		    }
				//
				//		    if (e.getKeyCode() == KeyEvent.VK_UP) {
				//			en.pressUp();
				//		    }
				//
				//		    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				//			en.pressDown();
				//		    }
				//
				//		    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				//			en.restart();
				//		    }
				//		}
				//		if (e.getKeyCode() == KeyEvent.VK_P) {
				//		    en.setPauseGame(!en.isPauseGame());
				//		}

			}
		});
	}

	public static void main(String[] args) {
		new App();
	}
}