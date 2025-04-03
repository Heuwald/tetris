package br.com.heuwald.tetris.game.graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Date;

import javax.swing.ImageIcon;

import br.com.heuwald.tetris.game.engine.Block;
import br.com.heuwald.tetris.game.engine.Engine;

public class Pintura {

	Blocos blocos = new Blocos();

	Engine en;
	ImageObserver io;
	Image fundo;
	int sizeX = 400, sizeY = 700, blockSize, xBloco, yBloco;

	public Pintura(Engine en, ImageObserver io) {

		this.en = en;

		this.io = io;
		blockSize = 20;

		BufferedImage bi = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_RGB);
		Graphics g;
		g = bi.getGraphics();
		g.setColor(Color.BLACK);
		Image imFundo = new ImageIcon(getClass().getResource("/br/com/heuwald/resources/images/fundo.jpg")).getImage();
		g.drawImage(imFundo, 0, 0, sizeX, sizeY, io);

		int gradeAltura = en.grade.getHeight();
		int gradeLargura = en.grade.getWidth();

		Graphics2D gd = (Graphics2D) g;

		for (int i = 0; i < gradeLargura; i++) {
			for (int j = 0; j < gradeAltura; j++) {

				xBloco = (i * blockSize + 11);
				yBloco = (j * blockSize + 11);

				gd.setColor(Color.GRAY);
				gd.drawImage(blocos.VAZIO, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
				//		gd.drawRect(xBloco + 1, yBloco + 1, blockSize - 2, blockSize - 2);
				//		gd.drawRect(xBloco + 3, yBloco + 3, blockSize - 6, blockSize - 6);
				//		gd.drawRect(xBloco + 2, yBloco + 2, blockSize - 5, blockSize - 5);
			}
		}

		fundo = bi.getScaledInstance(sizeX, sizeY, Image.SCALE_REPLICATE);
	}

	long ini, fim;

	public synchronized Image pintaTela() {

		//	try {
		//	    Thread.sleep(30);
		//	} catch (InterruptedException e) {
		//	    e.printStackTrace();
		//	}
		ini = new Date().getTime();
		BufferedImage bi = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_RGB);
		Graphics g;
		g = bi.getGraphics();

		g.drawImage(fundo, 0, 0, io);

		if(en.isPauseGame()){
			pintaPause(g);

		}else{

			if(en.isGameOver()){
				pintaFim();
			}

			pintaGridPrincipal(g);
			pintaProxPeca(g);
			pintaInfo(g);
		}
		System.out.println((new Date().getTime() - ini));
		return bi.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
	}

	public void pintaPause(Graphics g){

		int gradeAltura = en.grade.getHeight();
		int gradeLargura = en.grade.getWidth();

		for (int i = 0; i < gradeLargura; i++) {
			for (int j = 0; j < gradeAltura; j++) {
				Block bloco = en.grade.getBlock(i, j);
				//
				xBloco = (i * blockSize + 11);
				yBloco = (j * blockSize + 11);


				g.drawImage(blocos.PRETO, xBloco, yBloco, blockSize - 1, blockSize - 1, io);

			}
		}
	}

	public void pintaGridPrincipal(Graphics g) {

		int gradeAltura = en.grade.getHeight();
		int gradeLargura = en.grade.getWidth();

		Graphics2D gd = (Graphics2D) g;

		for (int i = 0; i < gradeLargura; i++) {
			for (int j = 0; j < gradeAltura; j++) {
				Block bloco = en.grade.getBlock(i, j);
				//
				xBloco = (i * blockSize + 11);
				yBloco = (j * blockSize + 11);

				if (bloco != null && bloco.isExiste()) {

					if (bloco.getCor().getRGB() == Color.RED.getRGB()) {
						//			gd.drawImage(blocos.VERMELHO, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
						gd.setColor(Color.RED);
						gd.fillRect(xBloco, yBloco, blockSize - 1, blockSize - 1);
					} else if (bloco.getCor().getRGB() == Color.BLUE.getRGB()) {
						//			gd.drawImage(blocos.AZUL, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
						gd.setColor(Color.BLUE);
						gd.fillRect(xBloco, yBloco, blockSize - 1, blockSize - 1);
					} else if (bloco.getCor().getRGB() == Color.GREEN.getRGB()) {
						//gd.drawImage(blocos.VERDE, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
						gd.setColor(Color.GREEN);
						gd.fillRect(xBloco, yBloco, blockSize - 1, blockSize - 1);
					} else if (bloco.getCor().getRGB() == Color.YELLOW.getRGB()) {
						//			gd.drawImage(blocos.AMARELO, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
						gd.setColor(Color.YELLOW);
						gd.fillRect(xBloco, yBloco, blockSize - 1, blockSize - 1);
					} else if (bloco.getCor().getRGB() == Color.ORANGE.getRGB()) {
						//			gd.drawImage(blocos.LARANJA, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
						gd.setColor(Color.ORANGE);
						gd.fillRect(xBloco, yBloco, blockSize - 1, blockSize - 1);
					} else if (bloco.getCor().getRGB() == Color.PINK.getRGB()) {
						//			gd.drawImage(blocos.ROSA, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
						gd.setColor(Color.PINK);
						gd.fillRect(xBloco, yBloco, blockSize - 1, blockSize - 1);
					} else if (bloco.getCor().getRGB() == Color.BLACK.getRGB()) {
						//			gd.drawImage(blocos.PRETO, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
						gd.setColor(Color.BLACK);
						gd.fillRect(xBloco, yBloco, blockSize - 1, blockSize - 1);
					} else if (bloco.getCor().getRGB() == Color.WHITE.getRGB()) {
						//			gd.drawImage(blocos.BRANCO, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
						gd.setColor(Color.WHITE);
						gd.fillRect(xBloco, yBloco, blockSize - 1, blockSize - 1);
					} else {
						//			gd.drawImage(blocos.AZUL, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
						gd.setColor(Color.BLUE);
						gd.fillRect(xBloco, yBloco, blockSize - 1, blockSize - 1);
					}
				}
			}
		}

	}

	public void pintaProxPeca(Graphics g) {
		Graphics2D gd = (Graphics2D) g;

		int x = 230, y = 10, xBloco, yBloco;

		gd.setColor(Color.GRAY);
		gd.drawImage(blocos.VAZIO, x, y, 4 * blockSize, 4 * blockSize, io);

		if (en.getProxPeca() == null)
			return;

		for (int i = 0; i < en.getProxPeca().getHeight(); i++) {
			for (int j = 0; j < en.getProxPeca().getWidth(); j++) {
				Block bloco = en.getProxPeca().getBlock(i, j);
				if (bloco != null && bloco.isExiste()) {

					int xf = (4 * blockSize) / 2 - (en.getProxPeca().getHeight() * blockSize) / 2;
					int yf = (4 * blockSize) / 2 - (en.getProxPeca().getWidth() * blockSize) / 2;
					;
					xBloco = (i * blockSize + 1) + x + xf;
					yBloco = (j * blockSize + 1) + y + yf;

					if (bloco.getCor().getRGB() == Color.RED.getRGB()) {
						//			gd.drawImage(blocos.VERMELHO, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
						gd.setColor(Color.RED);
						gd.fillRect(xBloco, yBloco, blockSize - 1, blockSize - 1);
					} else if (bloco.getCor().getRGB() == Color.BLUE.getRGB()) {
						//			gd.drawImage(blocos.AZUL, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
						gd.setColor(Color.BLUE);
						gd.fillRect(xBloco, yBloco, blockSize - 1, blockSize - 1);
					} else if (bloco.getCor().getRGB() == Color.GREEN.getRGB()) {
						//			gd.drawImage(blocos.VERDE, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
						gd.setColor(Color.GREEN);
						gd.fillRect(xBloco, yBloco, blockSize - 1, blockSize - 1);
					} else if (bloco.getCor().getRGB() == Color.YELLOW.getRGB()) {
						//gd.drawImage(blocos.AMARELO, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
						gd.setColor(Color.YELLOW);
						gd.fillRect(xBloco, yBloco, blockSize - 1, blockSize - 1);
					} else if (bloco.getCor().getRGB() == Color.ORANGE.getRGB()) {
						//			gd.drawImage(blocos.LARANJA, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
						gd.setColor(Color.ORANGE);
						gd.fillRect(xBloco, yBloco, blockSize - 1, blockSize - 1);
					} else if (bloco.getCor().getRGB() == Color.PINK.getRGB()) {
						//			gd.drawImage(blocos.ROSA, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
						gd.setColor(Color.PINK);
						gd.fillRect(xBloco, yBloco, blockSize - 1, blockSize - 1);
					} else if (bloco.getCor().getRGB() == Color.BLACK.getRGB()) {
						//			gd.drawImage(blocos.PRETO, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
						gd.setColor(Color.BLACK);
						gd.fillRect(xBloco, yBloco, blockSize - 1, blockSize - 1);
					} else if (bloco.getCor().getRGB() == Color.WHITE.getRGB()) {
						//gd.drawImage(blocos.BRANCO, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
						gd.setColor(Color.WHITE);
						gd.fillRect(xBloco, yBloco, blockSize - 1, blockSize - 1);
					} else {
						//gd.drawImage(blocos.AZUL, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
						gd.setColor(Color.BLUE);
						gd.fillRect(xBloco, yBloco, blockSize - 1, blockSize - 1);
					}
				}
			}
		}

	}

	public void pintaInfo(Graphics g) {

		Graphics2D gd = (Graphics2D) g;

		gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		gd.setFont(new Font("Arial", Font.BOLD, 12));
		gd.setColor(Color.WHITE);
		gd.drawString("Score: " + en.getPontos(), 230, 120);
		gd.drawString("Lines: " + en.getLinhas(), 230, 140);
	}

	public void pintaFim(){
		Block b = new Block();
		b.setIdTetromino(1000000);
		b.setCor(Color.BLACK);
		b.setExiste(true);
		for (int i = 0; i < en.grade.getHeight(); i++) {
			for (int j = 0; j < en.grade.getWidth(); j++) {
				if(en.grade.getBlock(j, i) == null || en.grade.getBlock(j, i).getIdTetromino() != 1000000){
					en.grade.setBlock(b, j, i);
					return;
				}    
			}    
		}
	}
}
