package br.com.heuwald.tetris.game.app.applet;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;

import br.com.heuwald.tetris.game.TetrisPanel;
import br.com.heuwald.tetris.game.engine.Engine;


public class Applet extends java.applet.Applet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Engine en;
	//    BorderLayout layoutPrincipal = new BorderLayout();
	TetrisPanel tp;

	@Override
	public void init(){
		en = new Engine();

		tp = new TetrisPanel(en);
		tp.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		super.setSize(330, 665);
		//	super.setLayout(layoutPrincipal);
		//	super.add(tp, BorderLayout.CENTER);
		super.add(tp);
		super.setMaximumSize(new Dimension(800, 1200));

		super.setVisible(true);
		super.addKeyListener(tp.getKeyListener());
		en.setPauseGame(true);
		en.play();
	}

}
