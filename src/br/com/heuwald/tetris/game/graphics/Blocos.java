package br.com.heuwald.tetris.game.graphics;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Blocos {
	public final Image VERMELHO = new ImageIcon(getClass().getResource("/br/com/heuwald/resources/images/b1.png")).getImage();
	public final Image AZUL = new ImageIcon(getClass().getResource("/br/com/heuwald/resources/images/b2.png")).getImage();
	public final Image VERDE = new ImageIcon(getClass().getResource("/br/com/heuwald/resources/images/b3.png")).getImage();
	public final Image AMARELO = new ImageIcon(getClass().getResource("/br/com/heuwald/resources/images/b4.png")).getImage();
	public final Image LARANJA = new ImageIcon(getClass().getResource("/br/com/heuwald/resources/images/b6.png")).getImage();
	public final Image ROSA = new ImageIcon(getClass().getResource("/br/com/heuwald/resources/images/b5.png")).getImage();
	public final Image PRETO = new ImageIcon(getClass().getResource("/br/com/heuwald/resources/images/b8.png")).getImage();
	public final Image BRANCO = new ImageIcon(getClass().getResource("/br/com/heuwald/resources/images/b7.png")).getImage();
	public final Image VAZIO = new ImageIcon(getClass().getResource("/br/com/heuwald/resources/images/b9.png")).getImage();
	
	public Image getImageByColor(Color color) {
		if (color.getRGB() == Color.RED.getRGB()) {
			return VERMELHO;
		} else if (color.getRGB() == Color.BLUE.getRGB()) {
			return AZUL;
		} else if (color.getRGB() == Color.GREEN.getRGB()) {
			return VERDE;
		} else if (color.getRGB() == Color.YELLOW.getRGB()) {
			return AMARELO;
		} else if (color.getRGB() == Color.ORANGE.getRGB()) {
			return LARANJA;
		} else if (color.getRGB() == Color.PINK.getRGB()) {
			return ROSA;
		} else if (color.getRGB() == Color.BLACK.getRGB()) {
			return PRETO;
		} else if (color.getRGB() == Color.WHITE.getRGB()) {
			return BRANCO;
		} else {
			return AZUL;
		}
	}
}
