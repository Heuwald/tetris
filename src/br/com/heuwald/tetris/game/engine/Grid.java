package br.com.heuwald.tetris.game.engine;

public class Grid {

	private Block grid[][];

	private int width, height;

	public Grid(int x, int y) {
		grid = new Block[x][y];
		this.setWidth(x);
		this.setHeight(y);
	}

	public void removeTetromino(int id) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (grid[i][j] != null) {
					if (grid[i][j].getIdTetromino() == id) {
						grid[i][j] = null;
					}
				}
			}
		}
	}

	public boolean insertTetromino(Tetromino peca, int x, int y) {
		if (!canMove(peca, x, y)) {
			return false;
		}
		removeTetromino(peca.getIdTetromino());
		for (int i = x; i < (x + peca.getHeight()); i++) {
			for (int j = y; j < (y + peca.getWidth()); j++) {
				if (j < 0)
					continue;
				if (peca.getBlock(i - x, j - y) != null) {
					if (peca.getBlock(i - x, j - y).isExiste() && (getBlock(i, j) == null || peca.getBlock(i - x, j - y).getIdTetromino() == getBlock(i, j).getIdTetromino())) {
						setBlock(peca.getBlock(i - x, j - y), i, j);
					}
				}
			}
		}
		return true;
	}

	public boolean canMove(Tetromino peca, int x, int y) {
		
		if (x + peca.getHeight() > width) { // Verifica se a nova posição não excete os limites da largura da grid
			return false;
		}
		if (y + peca.getWidth() > height) { // Verifica se a nova posição não excete os limites da altura da grid
			return false;
		}
		for (int i = x; i < (x + peca.getHeight()); i++) {
			for (int j = y; j < (y + peca.getWidth()); j++) {
				if (j < 0) // Se o bloco da peça ainda não entrou no grid, então ignora a verificação e vai para o próximo bloco da peça 
					continue;
				if (getBlock(i, j) != null) { // Se a posição verificada no grid não for nula
					if (getBlock(i, j).isExiste()) { // Se já existe um bloco na posição do grid 
						if (peca.getBlock(i - x, j - y) != null) { // A peça é um grid 4x4, então verifica se a posição correspondente na peça é diferente de nulo
							if (peca.getBlock(i - x, j - y).isExiste() && (getBlock(i, j) != null && peca.getBlock(i - x, j - y).getIdTetromino() != getBlock(i, j).getIdTetromino())) { // Se o bloco da peça estiver sendo movido para uma posição onde já tenha um bloco de outra peça, então retorna false
								return false;
							}
						}
					}
				}
			}
		}
		return true; // Se chegou aqui é por que o espaço estava livre, então retorna true para entrada da peça na posição nova
	}

	public Block getBlock(int x, int y) {
		return grid[x][y];
	}

	public Block setBlock(Block b, int x, int y) {
		return grid[x][y] = b;
	}

	public void setWidth(int l) {
		this.width = l;
	}

	public int getWidth() {
		return width;
	}

	public void setHeight(int a) {
		this.height = a;
	}

	public int getHeight() {
		return height;
	}

	public Block[][] getGrid() {
		return grid;
	}
}
