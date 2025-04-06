package br.com.heuwald.tetris.game.engine;
import java.awt.Color;

import lombok.Data;

@Data
public class Tetromino {

	private Block tetromino[][];
	private int idTetromino;

	private int locationX, locationY;
	private int width = 4, height = 4;

	public Tetromino(int id) {
		tetromino = new Block[height][width];
		idTetromino = id;
	}

	public void newBlock(int x, int y) {
		newBlock(x, y, Color.BLACK);
	}

	public void newBlock(int x, int y, Color cor) {
		Block b = new Block();
		b.setExiste(true);
		b.setIdTetromino(this.idTetromino);
		b.setCor(cor);

		tetromino[x][y] = b;
	}

//	public Block newBlock(Color cor) {
//		Block b = new Block();
//		b.setExiste(true);
//		b.setIdTetromino(this.idTetromino);
//		return b;
//	}

	public void removeBlock(int x, int y) {
		tetromino[x][y] = null;
	}

	public void trimTetromino() {
		int x = 0, y = 0;
		int norte = 0, leste = 0, oeste = 0, sul = 0;

		boolean flag = false;

		for (int i = 0; i < tetromino.length; i++) {
			for (int j = 0; j < tetromino.length; j++) {
				if (tetromino[i][j] != null && tetromino[i][j].isExiste()) {
					oeste = i;
					flag = true;
					break;
				}
			}
			if (flag)
				break;
		}

		flag = false;
		for (int i = 0; i < tetromino.length; i++) {
			for (int j = 0; j < tetromino.length; j++) {
				if (tetromino[j][i] != null && tetromino[j][i].isExiste()) {
					norte = i;
					flag = true;
					break;
				}
			}
			if (flag)
				break;
		}

		flag = false;
		for (int i = tetromino.length - 1; i >= 0; i--) {
			for (int j = 0; j < tetromino.length; j++) {
				if (tetromino[i][j] != null && tetromino[i][j].isExiste()) {
					x = i;
					leste = i;
					flag = true;
					break;
				}
			}
			if (flag)
				break;
		}

		flag = false;
		for (int i = tetromino.length - 1; i >= 0; i--) {
			for (int j = 0; j < tetromino.length; j++) {
				if (tetromino[j][i] != null && tetromino[j][i].isExiste()) {
					y = i;
					sul = i;
					flag = true;
					break;
				}
			}
			if (flag)
				break;
		}

		int lado = ((x > y) ? x : y) + 1;

		Block p[][] = new Block[lado][lado];
		for (int i = 0; i <= sul; i++) {
			for (int j = 0; j <= leste; j++) {
				if (tetromino[j][i] != null && tetromino[j][i].isExiste()) {
					p[j - oeste][i - norte] = tetromino[j][i];
				}
			}
		}

		setHeight((leste - oeste) + 1);
		setWidth((sul - norte) + 1);

		tetromino = p;
	}

	public void rotateTetromino() {
		Block p[][] = new Block[tetromino.length][tetromino.length];
		for (int i = 0; i < tetromino.length; i++) {
			for (int j = 0; j < tetromino.length; j++) {
				p[tetromino.length - 1 - j][i] = tetromino[i][j];

			}
		}
		tetromino = p;
		trimTetromino();
	}

//	public int getTamanhoGradePeca() {
//		return tetromino.length;
//	}

	public Block getBlock(int x, int y) {
		return tetromino[x][y];
	}

	public Tetromino copyTetromino() {
		Tetromino block = new Tetromino(idTetromino);
		Block b;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (this.tetromino[i][j] != null && this.tetromino[i][j].isExiste()) {

					b = new Block();
					b.setExiste(this.tetromino[i][j].isExiste());
					b.setCor(new Color(this.tetromino[i][j].getCor().getRGB()));
					b.setIdTetromino(this.tetromino[i][j].getIdTetromino());

					block.tetromino[i][j] = b;
				}
			}
		}
		return block;
	}
}
