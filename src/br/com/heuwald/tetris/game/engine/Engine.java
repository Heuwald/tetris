package br.com.heuwald.tetris.game.engine;
import java.awt.Color;
import java.util.ArrayList;

//import javax.xml.ws.handler.MessageContext.Scope;

public class Engine implements Runnable {

	public Grid grade;
	public ArrayList<Tetromino> pecas = new ArrayList<Tetromino>();
	private Tetromino pecaFoco = null;
	private Tetromino proxPeca = null;
	private int time = 500;
	private boolean gameOver;
	private boolean pauseGame;
	public static final int MOVE_NORTE = 1;
	public static final int MOVE_SUL = 1;
	public static final int MOVE_LESTE = 1;
	public static final int MOVE_OESTE = 1;
	private long pontos = 0;
	private int linhas = 0;
	public ArrayList<EngineListener> engineListener = new ArrayList<EngineListener>();

	public Engine() {
		inicializa();
	}

	private Tetromino criaPecaPadrao() {

		Tetromino peca = new Tetromino(getNovoId());

		int x = (int) (Math.random() * 7);
		switch (x) {
		case 0:
			peca.newBlock(0, 1, Color.blue);
			peca.newBlock(1, 1, Color.blue);
			peca.newBlock(2, 1, Color.blue);
			peca.newBlock(3, 1, Color.blue);//
			break;
		case 1:
			peca.newBlock(0, 0, Color.YELLOW);
			peca.newBlock(0, 1, Color.YELLOW);
			peca.newBlock(1, 0, Color.YELLOW);
			peca.newBlock(1, 1, Color.YELLOW);//
			break;
		case 2:
			peca.newBlock(1, 0, Color.RED);
			peca.newBlock(1, 1, Color.RED);
			peca.newBlock(1, 2, Color.RED);
			peca.newBlock(2, 2, Color.RED);//
			break;
		case 3:
			peca.newBlock(1, 0, Color.GREEN);
			peca.newBlock(1, 1, Color.GREEN);
			peca.newBlock(1, 2, Color.GREEN);
			peca.newBlock(0, 2, Color.GREEN);//
			break;
		case 4:
			peca.newBlock(1, 1, Color.ORANGE);
			peca.newBlock(0, 2, Color.ORANGE);
			peca.newBlock(1, 2, Color.ORANGE);
			peca.newBlock(2, 2, Color.ORANGE);//
			break;
		case 5:
			peca.newBlock(0, 0, Color.PINK);
			peca.newBlock(0, 1, Color.PINK);
			peca.newBlock(1, 1, Color.PINK);
			peca.newBlock(1, 2, Color.PINK);//
			break;
		case 6:
			peca.newBlock(1, 0, Color.BLACK);
			peca.newBlock(1, 1, Color.BLACK);
			peca.newBlock(0, 1, Color.BLACK);
			peca.newBlock(0, 2, Color.BLACK);
			break;
		case 7:
			peca.newBlock(1, 0, Color.WHITE);
			peca.newBlock(1, 1, Color.WHITE);
			peca.newBlock(0, 1, Color.WHITE);
			peca.newBlock(0, 2, Color.WHITE);
			break;

		default:
			peca.newBlock(0, 0, Color.CYAN);
			peca.newBlock(0, 1, Color.ORANGE);
			peca.newBlock(0, 2, Color.YELLOW);
			peca.newBlock(1, 0, Color.BLACK);
			peca.newBlock(1, 2, Color.RED);
			peca.newBlock(2, 0, Color.PINK);
			peca.newBlock(2, 1, Color.MAGENTA);
			peca.newBlock(2, 2, Color.GRAY);
			break;
		}

		return peca;
	}

	private int getNovoId() {
		int nId = 0;
		while (true) {
			nId = (int) (Math.random() * 100000);
			for (Tetromino peca : pecas) {
				if (peca.getIdTetromino() == nId)
					break;
			}
			return nId;
		}
	}

	public synchronized void play() {
		gameOver = false;
		new Thread(this).start();
	}

	public void printConsole() {
		String teste = "";
		for (int i = 0; i < grade.getHeight(); i++) {
			for (int j = 0; j < grade.getWidth(); j++) {
				if (grade.getBlock(j, i) != null && grade.getBlock(j, i).isExiste())
					teste += "@ ";
				else
					teste += ". ";
			}
			teste += "\n";
		}
		System.out.println(teste);
	}

	@Override
	public synchronized void run() {

		while (true) {

			try {
				if(gameOver){
					for (EngineListener enl : engineListener) {
						enl.gameOver();
					}
					wait(100);
					continue;
				}
				if (pauseGame) {
					wait(100);
					continue;
				}
				if (movePecaBaixo()) {
					for (EngineListener enl : engineListener) {
						enl.descePeca();
					}
					wait(time);
				} 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public boolean movePecaBaixo() {

		if (pecaFoco != null) {
			pecaFoco.setLocationY(pecaFoco.getLocationY() + 1);
		}

		if (pecaFoco == null || !grade.insertTetromino(pecaFoco, pecaFoco.getLocationX(), pecaFoco.getLocationY())) {
			if (pecaFoco != null && pecaFoco.getLocationY() < 0) {
				gameOver = true;
				return false;
			}
			Tetromino peca = criaPecaPadrao();

			verificaPontos();
			peca.trimTetromino();
			addPeca(peca);
			return false;
		} else {
			return true;
		}
	}

	public void movePecaLeft() {

		if (pecaFoco.getLocationX() - 1 < 0) {
			return;
		}
		pecaFoco.setLocationX(pecaFoco.getLocationX() - 1);
		if (grade.canMove(pecaFoco, pecaFoco.getLocationX(), pecaFoco.getLocationY()))
			grade.insertTetromino(pecaFoco, pecaFoco.getLocationX(), pecaFoco.getLocationY());
		else
			pecaFoco.setLocationX(pecaFoco.getLocationX() + 1);
	}

	public void movePecaRight() {

		if (pecaFoco.getLocationX() + pecaFoco.getHeight() + 1 > grade.getWidth()) {
			return;
		}
		pecaFoco.setLocationX(pecaFoco.getLocationX() + 1);
		if (grade.canMove(pecaFoco, pecaFoco.getLocationX(), pecaFoco.getLocationY()))
			grade.insertTetromino(pecaFoco, pecaFoco.getLocationX(), pecaFoco.getLocationY());
		else
			pecaFoco.setLocationX(pecaFoco.getLocationX() - 1);
	}

	public void giraPeca() {

		Tetromino pecaAux = pecaFoco.copyTetromino();

		pecaAux.rotateTetromino();

		if (grade.canMove(pecaAux, pecaFoco.getLocationX(), pecaFoco.getLocationY())){
			pecaFoco.rotateTetromino();
			grade.insertTetromino(pecaFoco, pecaFoco.getLocationX(), pecaFoco.getLocationY());
		}

	}

	private void verificaPontos() {
		boolean removeFila;
		int desceBlocos = 0;

		for (int i = grade.getHeight() - 1; i >= 0; i--) {
			removeFila = true;
			for (int j = 0; j < grade.getWidth(); j++) {

				if (grade.getBlock(j, i) == null || !grade.getBlock(j, i).isExiste()) {
					removeFila = false;
				}

				if (grade.getBlock(j, i) != null) {
					if (desceBlocos > 0) {
						grade.setBlock(grade.getBlock(j, i), j, i + desceBlocos);
						grade.setBlock(null, j, i);
					}
				}

			}
			if (removeFila) {
				for (int j = 0; j < grade.getWidth(); j++) {
					if (grade.getBlock(j, i + desceBlocos) != null) {
						//			if(!grade.getBloco(j, i + desceBlocos).eventoExplodir()){
						//			    removeFila = false;
						//			}else{
						grade.setBlock(null, j, i + desceBlocos);
						//			}
					}
				}
				//		if(!removeFila)
				//		    continue;
				desceBlocos++;
				pontos += 192 * (desceBlocos * desceBlocos);
				int p = (int) (pontos / 10000);
				time = 500 - (p * 10);
			}
		}
		linhas += desceBlocos;
	}

	public void addPeca(Tetromino peca) {
		if (proxPeca != null) {
			setPecaFoco(proxPeca.copyTetromino());
		}

		proxPeca = peca;

		if (pecaFoco != null) {
			pecaFoco.trimTetromino();
			pecaFoco.setLocationX((grade.getWidth() / 2) - (pecaFoco.getHeight() / 2));
			pecaFoco.setLocationY(-1 * pecaFoco.getWidth());

			grade.insertTetromino(pecaFoco, pecaFoco.getLocationX(), pecaFoco.getLocationY());
		}
	}

	public void movePeca(int direcao) {

	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getTime() {
		return time;
	}

	public void setPecaFoco(Tetromino pecaFoco) {
		this.pecaFoco = pecaFoco;
	}

	public Tetromino getPecaFoco() {
		return pecaFoco;
	}

	public long getPontos() {
		return pontos;
	}

	public void setPontos(long pontos) {
		this.pontos = pontos;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public Tetromino getProxPeca() {
		return proxPeca;
	}

	public void setProxPeca(Tetromino proxPeca) {
		this.proxPeca = proxPeca;
	}

	public void setPauseGame(boolean pauseGame) {
		this.pauseGame = pauseGame;
	}

	public boolean isPauseGame() {
		return pauseGame;
	}

	public void setLinhas(int linhas) {
		this.linhas = linhas;
	}

	public int getLinhas() {
		return linhas;
	}

	public static void main(String[] args) {
		Engine e = new Engine();
		e.play();
	}

	public void pressRight(){
		if(isPauseGame()){

		}else if(isGameOver()){

		}else{
			movePecaRight();
		}
	}

	public void pressLeft(){
		if(isPauseGame()){

		}else if(isGameOver()){

		}else{
			movePecaLeft();
		}
	}

	public void pressUp(){
		if(isPauseGame()){

		}else if(isGameOver()){

		}else{
			giraPeca();
		}
	}

	public void pressDown(){
		if(isPauseGame()){

		}else if(isGameOver()){

		}else{
			movePecaBaixo();
		}
	}

	public void restart(){
		inicializa();
	}

	private void inicializa(){
		pecaFoco = null;
		proxPeca = null;
		pontos = 0;
		linhas = 0;
		grade = new Grid(10, 30);
		setPauseGame(false);
		setGameOver(false);
	}
}
